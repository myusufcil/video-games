package com.base.games.ui.pages.detail

import android.content.Intent
import android.os.Bundle
import com.base.games.R
import com.base.games.ui.base.activity.BaseActivity
import com.base.games.ui.base.fragment.BaseFragment
import com.base.games.ui.common.enums.IntentBundleKeyEnum
import com.base.games.ui.pages.detail.viewmodel.DetailActivityViewModel
import com.base.games.ui.pages.detailgames.DetailGamesFragment

class DetailActivity : BaseActivity<DetailActivityViewModel>() {

    override val layoutViewRes = R.layout.activity_detail
    override val viewModelClass = DetailActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openRelatedFragment(intent)
    }

    private fun openRelatedFragment(intent: Intent?) {
        intent?.let {
            when (it.getStringExtra(IntentBundleKeyEnum.DETAIL_KEY.toString())) {
                IntentBundleKeyEnum.DETAIL_GAMES.toString() -> {
                    navigateToFragment(DetailGamesFragment.newInstance(it.extras!!))
                }
            }
        }
    }

    private fun navigateToFragment(fragment: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.detail_container, fragment)
        transaction.commit()
    }
}