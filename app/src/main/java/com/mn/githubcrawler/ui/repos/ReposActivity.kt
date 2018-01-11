package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject

class ReposActivity : BaseActivity() {
    @Inject
    lateinit var reposViewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        reposViewModel.reposLiveData()
                .observe(this, Observer { reposUiModel -> reposView.bind(reposUiModel) })
        reposViewModel.errorsLiveData()
                .observe(this, Observer { errorMessage -> displayErrorMessage(errorMessage) })

        reposViewModel.onLoad()
    }

    private fun displayErrorMessage(errorMessage: String?) {
        errorMessage?.let {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
