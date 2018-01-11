package com.mn.githubcrawler.ui.repos.model


data class ReposUiModel(val items: List<ItemUiModel>,
                        val append: Boolean,
                        val loadMore: () -> Unit)