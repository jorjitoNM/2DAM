package com.example.myapplication.ui.common

import android.content.Context
import androidx.annotation.StringRes
import com.example.myapplication.ui.detailsScreen.DetailsFragment

class StringProvider(private val context: Context) {
    companion object {
        fun instance(context: DetailsFragment): StringProvider = StringProvider(context)
    }

    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

}