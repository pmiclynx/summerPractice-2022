package com.summer.practice.tvtracker.data.networking

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, error: String) {
    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
}