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
        // координаты москвы
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(55.7558,37.6173), 14f), 2500, null)
        googleMap.uiSettings.isZoomControlsEnabled = true

//        получаем список координат и размещаем их на карте в виде маркеров
            //присваиваем данные внутри коллбэка, чтобы тут же ими воспользоваться
        //при этом варианте данные будут присвоены placesResponse прямо в коллбэке
        //это значит, что код в коллбэке использующий placesResponse завершится успешно!
        viewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, Observer {
            this.placesResponse = it as PlacesResponse
            updateUIMap(googleMap)
        })

        onMarkerClick(googleMap)
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

    private fun updateUIMap(googleMap: GoogleMap) {
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
    }

    private fun onMarkerClick(googleMap: GoogleMap) {
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
            //при этом варианте данные будут присвоены placesResponse, после коллбэка
        //это значит, что код в коллбэке использующий placesResponse выбросит NPE
//        viewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, Observer {
//            this.placesResponse = it as PlacesResponse
//        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[PlacesViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeObservers()
    }
}