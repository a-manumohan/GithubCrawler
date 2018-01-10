package com.mn.githubcrawler.ui.repos

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.repos.model.ReposUiModel

class ReposView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val reposAdapter = ReposAdapter()

    init {
        View.inflate(context, R.layout.view_repos, this)
    }

    fun bind(reposUiModel: ReposUiModel?) {
        reposUiModel?.let {
            reposAdapter.bind(reposUiModel.items, reposUiModel.append)
        }
    }
}