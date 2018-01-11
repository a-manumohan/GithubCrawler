package com.mn.githubcrawler.ui.repos.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.repos.model.RepoUiModel
import kotlinx.android.synthetic.main.view_item_repo.view.*

class RepoItemView @JvmOverloads constructor(
        context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.view_item_repo, this)
    }

    fun bind(repoUiModel: RepoUiModel) {
        repoNameTextView.text = repoUiModel.name
        repoUrlTextView.text = repoUiModel.url
    }
}