package com.androidgang.retrofitmoviesandcities.ui.places

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.androidgang.retrofitmoviesandcities.R
import com.androidgang.retrofitmoviesandcities.model.PlacesResponse
import com.androidgang.retrofitmoviesandcities.ui.placesDetails.PlaceDetailsFragmentDirections
import com.androidgang.retrofitmoviesandcities.ui.placesDetails.PlacesViewModel
import com.androidgang.retrofitmoviesandcities.utilits.TAG
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class PlacesFragment : Fragment() {

    private lateinit var viewModel: PlacesViewModel
    private var placesResponse: PlacesResponse? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val moscow = LatLng(55.7558,37.6173)
        googleMap.addMarker(MarkerOptions().position(moscow).title("Marker in moscow"))
//        googleMap.addMarker(
//            MarkerOptions().position(LatLng(53.761220, 87.140152)).title("Marker in main place")
//        )
//        googleMap.addMarker(
//            MarkerOptions().position(LatLng(53.758395, 87.135457)).title("Marker in hotel")
//        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(moscow, 14f), 2500, null)
        googleMap.uiSettings.isZoomControlsEnabled = true
        //получаем список координат и размещаем их на карте в виде маркеров
        if (placesResponse?.results != null) {
            for (result in placesResponse?.results!!) {
                if (result.coords != null) {
                    val marker: Marker = googleMap.addMarker(
                        MarkerOptions()
                            .position(
                                LatLng(
                                    (result.coords!!.lat!!).toDouble(),
                                    (result.coords!!.lon!!).toDouble()
                                )
                            )
                    )
                    marker.tag = result
                }
            }
        }

        googleMap.setOnMarkerClickListener { marker ->
            val result: PlacesResponse.Result = (marker.tag) as PlacesResponse.Result
            val action = PlacesFragmentDirections.actionPlacesFragmentToPlaceDetailsFragment(
                image = result.images?.get(0)?.image,
                title = result.title,
                address = result.address,
                description = result.bodyText
            )
            findNavController().navigate(action)
            return@setOnMarkerClickListener true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        liveDataObservers()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun initialization() {
        initViewModel()
        loadData()
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun liveDataObservers() {
        viewModel.getLiveDataOnError.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "getLiveDataOnError: ${it.message}")
        })

        viewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, Observer {
            this.placesResponse = it as PlacesResponse
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[PlacesViewModel::class.java]
    }

}