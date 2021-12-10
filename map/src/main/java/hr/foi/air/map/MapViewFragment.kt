package hr.foi.air.map

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hr.foi.air.core.data.DataPresenter
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store

class MapViewFragment : Fragment(), DataPresenter {

    private var dataReadyFlag: Boolean = false
    private var viewReadyFlag: Boolean = false
    private var discounts: List<Discount>? = null
    private var stores: List<Store>? = null
    private lateinit var map : GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(OnMapReadyCallback { googleMap ->
            map = googleMap
            viewReadyFlag = true
            tryToDisplayData()
        })
    }

    override fun getIcon(context: Context): Drawable {
        return AppCompatResources.getDrawable(context, android.R.drawable.ic_menu_mylocation)!!
    }

    override fun getName(context: Context): String {
        return context.getString(R.string.map_view_presenter)
    }

    override fun getFragment(): Fragment {
        return this
    }

    override fun setData(stores: List<Store>?, discounts: List<Discount>?) {
        this.stores = stores
        this.discounts = discounts
        dataReadyFlag = true
        tryToDisplayData()
    }

    private fun tryToDisplayData() {
        if (dataReadyFlag && viewReadyFlag) {
            if (discounts != null && stores != null) {
                var cameraPositioned = false
                for (d in discounts!!) {
                    val s = stores!!.find { x -> x.id == d.storeId }
                    if (s != null) {
                        val position: LatLng =
                            LatLng(s.latitude / 1000000.0, s.longitude / 1000000.0)
                        map.addMarker(
                            MarkerOptions()
                                .position(position)
                                .title(s.name + " - " + d.name)
                                .snippet(d.description)
                        )?.tag = d

                        if (!cameraPositioned) {
                            val camPosition = CameraPosition.builder()
                                .target(position)
                                .zoom(12f)
                                .build()
                            map.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition))
                            cameraPositioned = true
                        }
                    }
                }
            }
        }
    }
}