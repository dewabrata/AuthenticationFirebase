package com.adl.authenticationfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_promotion_page.*

class PromotionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_page)

        val title = intent?.extras?.get("title") as String?
        val url = intent?.extras?.get("url") as String?

        webView.settings.apply {
            javaScriptEnabled = true
            builtInZoomControls = true
        }

        val webViewClient = WebViewClient()
        webView.webViewClient = webViewClient

        if (url != null) {
            webView.loadUrl(url)
        }
    }
}