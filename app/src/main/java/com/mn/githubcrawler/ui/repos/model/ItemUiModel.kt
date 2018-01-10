package com.mn.githubcrawler.ui.repos.model

sealed class ItemUiModel

data class RepoUiModel(val name: String,
                       val url: String) : ItemUiModel()

data class LoadingUiModel(val loading: Boolean) : ItemUiModel()