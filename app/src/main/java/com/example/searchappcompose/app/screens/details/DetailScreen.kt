package com.example.searchappcompose.app.screens.details

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailsScreen(detailViewModel: DetailViewModel, webUrl: String?) {

    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()

            webUrl?.let {
                loadUrl(it)
            }
        }
    })
}
