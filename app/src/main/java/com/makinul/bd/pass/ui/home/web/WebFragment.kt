package com.makinul.bd.pass.ui.home.web

import android.graphics.Bitmap
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.makinul.bd.pass.R
import com.makinul.bd.pass.databinding.FragmentWebBinding
import com.makinul.bd.pass.ui.home.HomeActivity
import com.makinul.bd.pass.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WebFragment : Fragment() {

    private var title: String? = null
    private var browserUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString(AppConstants.KEY_BROWSER_TITLE, null)
            browserUrl = it.getString(AppConstants.KEY_BROWSER_URL, null)
        }

        if (title.isNullOrEmpty()) {
            title = getString(R.string.national_id)
        }
        if (browserUrl.isNullOrEmpty()) {
            browserUrl = AppConstants.NID_URL
        }
    }

    private var _binding: FragmentWebBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.setInitialScale(1)
        binding.webView.setBackgroundColor(Color.argb(1, 255, 255, 255))
        binding.webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.domStorageEnabled = true

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {

                Log.v(TAG, "onProgressChanged $newProgress")

                if (newProgress <= 90) {
                    showProgressBar(View.VISIBLE)
                } else {
                    showProgressBar(View.INVISIBLE)
                }
                super.onProgressChanged(view, newProgress)
            }
        }

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            @Deprecated("Deprecated in Java")
            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {

                Log.v(TAG, "onReceivedError $description")
                super.onReceivedError(view, errorCode, description, failingUrl)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {

                Log.v(TAG, "WebResourceError $error")
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed() // Ignore SSL certificate errors
            }
        }

        binding.webView.loadUrl(browserUrl!!)

        (activity as HomeActivity?)?.supportActionBar?.title = title
    }

    private fun showProgressBar(visibility: Int = View.VISIBLE) {
//        lifecycleScope.launch(Dispatchers.Main) {
//            binding.progressBar.visibility = visibility
//        }
        if (_binding == null)
            return
        binding.progressBar.visibility = visibility
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "WebFragment"
    }
}