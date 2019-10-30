package br.com.livroandroid.carros

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.livroandroid.carros.activity.BaseActivity
import br.com.livroandroid.carros.activity.dialogs.AboutDialog
import br.com.livroandroid.carros.extensions.setupToolbar

class SiteLivroActivity : BaseActivity() {
    private val URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm"
    var webview: WebView? = null
    var progress: ProgressBar? = null
    var swipeToRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)
        // Toolbar
        val actionBar = setupToolbar(R.id.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        // Views
        webview = findViewById(R.id.webview)
        progress = findViewById(R.id.progress)
        // Carrega pagina
        setWebViewClient(webview)
        webview?.loadUrl(URL_SOBRE)

        //Swipe to Refresh
        swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swiperToRefresh)
        swipeToRefresh?.setOnRefreshListener {
            webview?.reload()
        }
        // Cores da animação
        swipeToRefresh?.setColorSchemeResources(
            R.color.refresh_progress_1,
            R.color.refresh_progress_2,
            R.color.refresh_progress_3
        )
    }

    // Controla os eventos do WebView
    private fun setWebViewClient(webView: WebView?) {
        webView?.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // Liga o progress
                progress?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Desliga o oprogress
                progress?.visibility = View.INVISIBLE
                // Termia a animação do Swipe to Refresh
                swipeToRefresh?.isRefreshing = false
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                if (url.endsWith("sobre.htm")) {
                    // Mostra o dialog
                    AboutDialog.showAbout(supportFragmentManager)
                    // retorna true para informar que interceptamos o evento
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }
}
