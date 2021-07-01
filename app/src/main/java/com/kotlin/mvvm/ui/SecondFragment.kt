package com.kotlin.mvvm.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.FragmentSecondBinding
import com.kotlin.mvvm.repository.model.SharedViewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var location: LatLng
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var fleetType: String
    private var marker: BitmapDescriptor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        return binding.root

    }

    fun getShareViewModel(): SharedViewModel {
        activity?.let {
            sharedViewModel = ViewModelProvider(it).get(SharedViewModel::class.java)
        }
        return sharedViewModel
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getShareViewModel().data.observe(viewLifecycleOwner, {
            val bundle = it as Bundle
            location = LatLng(bundle.getDouble("lat", 0.0), bundle.getDouble("long", 0.0))
            fleetType = bundle.getString("type", "")

            mapFragment.getMapAsync(this)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onMapReady(gMap: GoogleMap?) {
        mMap = gMap!!
        marker = if (fleetType == "TAXI") {
            bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_marker_taxi)
        } else {
            bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_marker_pool)
        }

        mMap.addMarker(
            MarkerOptions()
                .icon(marker)
                .title(fleetType)
                .position(location)

        )
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(8.0f)
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))


    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }
}