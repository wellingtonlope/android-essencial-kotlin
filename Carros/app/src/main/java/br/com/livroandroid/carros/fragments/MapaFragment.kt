package br.com.livroandroid.carros.fragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.utils.PermissionUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass.
 */
class MapaFragment : BaseFragment(), OnMapReadyCallback {
    // Objeto que controla o Goolge maps
    private var map: GoogleMap? = null
    val carro by lazy { arguments?.getParcelable<Carro>("carro") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mapa, container, false)
        // Inicia o mapa
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapaFragment)
                as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    // O método onMapReady(map) é chamado quando a inicialização do mapa estiver ok
    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        carro?.apply {
            if (latitude.toDouble() == 0.0) {
                // Ativa o botão para mostrar minha localização

                val ok = activity?.let {
                    PermissionUtils.validade(
                        it,
                        1,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                } ?: false
                if (ok) {
                    // Somente usa o GPS se a permissão estiver ok
                    map?.isMyLocationEnabled = true
                }
            } else {
                // Cria o objeto lat/lng com a coordenada da fábrinca
                val location = LatLng(latitude.toDouble(), longitude.toDouble())
                // Posiciona o mapa na coordenada da fábrica (zoom = 13)
                val update = CameraUpdateFactory.newLatLngZoom(location, 13f)
                map?.moveCamera(update)
                // Marcador no local da fábrica
                map?.addMarker(
                    MarkerOptions()
                        .title(nome)
                        .snippet(desc)
                        .position(location)
                )
            }
        }
        // Tipo do mapa: normal, satétlite, terreno ou hibrido
        map?.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_GRANTED) {
                // Permissão OK, podemos usar o GPS.
                map?.isMyLocationEnabled = true
                return
            }
        }
    }


}
