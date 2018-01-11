package com.mn.githubcrawler.ui.provider

import android.content.Context

class StringProviderImpl(private val context: Context) : StringProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}