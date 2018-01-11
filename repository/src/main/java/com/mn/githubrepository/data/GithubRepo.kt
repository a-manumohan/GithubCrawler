package com.mn.githubrepository.data

import com.google.gson.annotations.SerializedName

data class GithubRepo(@SerializedName("name") val name: String,
                      @SerializedName("full_name") val fullName: String,
                      @SerializedName("url") val url: String)