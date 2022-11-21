package com.example.searchappcompose.app.screens.details

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.searchappcompose.app.core.ui.loading_overlay.LoadingOverlay

@Composable
fun DetailsScreen(detailViewModel: DetailViewModel = hiltViewModel(), webUrl: String?) {

    val isWebViewLoading = rememberSaveable { mutableStateOf(true) }

    AndroidView(factory = { context ->
        WebView(context).apply {

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    // show dialog
                    isWebViewLoading.value = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // hide dialog
                    isWebViewLoading.value = false
                }
            }

            webUrl?.let {
                loadUrl(it)
            }
        }
    }, update = {
        webUrl?.let { url ->
            it.loadUrl(url)
        }
    })

    // Setting loader
    if (isWebViewLoading.value) {
        LoadingOverlay()
    }
}
