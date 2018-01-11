package com.mn.githubcrawler.ui.repos.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.repos.ReposAdapter
import com.mn.githubcrawler.ui.repos.model.ReposUiModel
import kotlinx.android.synthetic.main.view_repos.view.*

class ReposView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val reposAdapter = ReposAdapter()

    init {
        View.inflate(context, R.layout.view_repos, this)
        reposRecyclerView.adapter = reposAdapter
        reposRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun bind(reposUiModel: ReposUiModel?) {
        reposUiModel?.let {
            reposAdapter.bind(reposUiModel.items, reposUiModel.append)
            reposAdapter.onLoadMoreListener = object : ReposAdapter.Companion.OnLoadMoreListener {
                override fun loadMore() {
                    reposUiModel.loadMore()
                }
            }
        }
    }
}