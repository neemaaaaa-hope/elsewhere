package com.example.elsewhere.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object MapUtils {
    fun openInGoogleMaps(context: Context, placeName: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(placeName)}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
        } else {
            // Fallback to browser if Maps app is not installed
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(placeName)}"))
            context.startActivity(browserIntent)
        }
    }
}
