package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.adapter.TabsAdapter
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.setupToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar)
        setupNavDrawer()
        setupViewPagerTabs()

        // FAB (variável fab gerada automaticamente pelo kotlin extensions)
        fab.setOnClickListener {
            startActivity<CarroFormActivity>()
        }
    }

    private fun setupViewPagerTabs() {
        // Configura o ViewPager + tabs
        // As variáveis viewPager e TabLayout são geradas automaticamente pelo Kotlin extensions
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        // Cor banca no texto (o fundo azul é definido no layout)
        val cor = ContextCompat.getColor(context, R.color.white)
        tabLayout.setTabTextColors(cor, cor)
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
                startActivity<SiteLivroActivity>()
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
