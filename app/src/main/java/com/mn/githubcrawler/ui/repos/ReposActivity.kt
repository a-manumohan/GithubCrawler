package com.mn.githubcrawler.ui.repos

import android.os.Bundle
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.base.BaseActivity
import javax.inject.Inject

class ReposActivity : BaseActivity() {
    @Inject
    lateinit var reposViewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

    }
}
