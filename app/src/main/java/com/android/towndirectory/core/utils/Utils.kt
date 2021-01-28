package com.android.towndirectory.core.utils

import android.content.Context
import android.net.ConnectivityManager

fun isNetworkWorking(context: Context): Boolean {
    val netInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}