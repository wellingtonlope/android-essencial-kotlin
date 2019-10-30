package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.TipoCarro
import com.google.android.material.navigation.NavigationView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setupToolbar(R.id.toolbar)
        setupNavDrawer()
    }

    // Configura o Navigation Drawer
    private fun setupNavDrawer() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.classicos)
            }
            R.id.nav_item_carros_esportivos -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.esportivos)
            }
            R.id.nav_item_carros_luxo -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.luxo)
            }
            R.id.nav_item_site_livro -> {
                toast("Clicou em site do livro")
            }
            R.id.nav_item_settings -> {
                toast("Clicou em configurações")
            }
        }
        // Fecha o menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
