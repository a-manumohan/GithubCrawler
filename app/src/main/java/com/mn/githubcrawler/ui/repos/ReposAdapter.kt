package com.mn.githubcrawler.ui.repos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.repos.model.ItemUiModel
import com.mn.githubcrawler.ui.repos.model.LoadingUiModel
import com.mn.githubcrawler.ui.repos.model.RepoUiModel

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {
    companion object {
        const val TYPE_REPO = 0
        const val TYPE_LOADING = 1

        interface OnLoadMoreListener {
            fun loadMore()
        }
    }

    private val items: MutableList<ItemUiModel> = mutableListOf()

    var onLoadMoreListener: OnLoadMoreListener? = null
    var loadMoreTriggered = false

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is RepoUiModel -> TYPE_REPO
            is LoadingUiModel -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_REPO -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_item_repo,
                        parent, false)
                ViewHolder.RepoViewHolder(view)
            }
            TYPE_LOADING -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_item_loading,
                        parent, false)
                ViewHolder.LoadingViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type " + viewType)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder.RepoViewHolder -> {
                val repoUiModel: RepoUiModel = items[position] as RepoUiModel
                holder.bind(repoUiModel)
                triggerLoadMoreIfValid(position)
            }
            is ViewHolder.LoadingViewHolder -> {
                val loadingUiModel: LoadingUiModel = items[position] as LoadingUiModel
                holder.bind(loadingUiModel)
            }
        }
    }

    override fun getItemCount() = items.size

    private fun triggerLoadMoreIfValid(position: Int) {
        if (itemCount <= 3) return
        if (position == itemCount - 4 && !loadMoreTriggered) {
            onLoadMoreListener?.loadMore()
            loadMoreTriggered = true
        }
    }

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class RepoViewHolder(itemView: View) : ViewHolder(itemView) {
            fun bind(repoUiModel: RepoUiModel) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        class LoadingViewHolder(itemView: View) : ViewHolder(itemView) {
            fun bind(loadingUiModel: LoadingUiModel) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    fun bind(items: List<ItemUiModel>, append: Boolean) {
        loadMoreTriggered = false
        if (append) {
            appendItems(items)
        } else {
            replaceItems(items)
        }
    }

    private fun replaceItems(items: List<ItemUiModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun appendItems(items: List<ItemUiModel>) {
        val startIndex = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(startIndex, items.size)
    }
}
