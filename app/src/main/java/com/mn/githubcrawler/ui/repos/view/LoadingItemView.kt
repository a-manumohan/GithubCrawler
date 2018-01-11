package com.mn.githubcrawler.ui.repos.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.repos.model.LoadingUiModel
import kotlinx.android.synthetic.main.view_item_loading.view.*

class LoadingItemView @JvmOverloads constructor(
        context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.view_item_loading, this)
    }

    fun bind(loadingUiModel: LoadingUiModel) {
        if (loadingUiModel.loading) {
            loadingView.isIndeterminate = true
            loadingView.visibility = View.VISIBLE
        } else {
            loadingView.visibility = View.GONE
        }
    }
}