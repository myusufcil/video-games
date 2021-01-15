package com.base.games.ui.common.navigation

import android.app.Activity
import android.os.Bundle
import com.base.component.ui.gamescard.HomePageCardDTO
import com.base.component.ui.homepageslider.SliderItem
import com.base.core.extensions.openActivity
import com.base.core.ui.recyclerview.DisplayItem
import com.base.games.ui.common.enums.IntentBundleKeyEnum
import com.base.games.ui.pages.detail.DetailActivity


class NavigationHelper(var activity: Activity) {

    fun navigate(item: DisplayItem) {
        val bundle = Bundle()
        when (item) {
            is SliderItem -> {
                bundle.putString(
                    IntentBundleKeyEnum.DETAIL_KEY.toString(),
                    IntentBundleKeyEnum.DETAIL_GAMES.toString()
                )
                item.id?.let { bundle.putInt("idGameDetail", it) }
            }

            is HomePageCardDTO -> {
                bundle.putString(
                    IntentBundleKeyEnum.DETAIL_KEY.toString(),
                    IntentBundleKeyEnum.DETAIL_GAMES.toString()
                )
                bundle.putInt("idGameDetail", item.id)
            }

        }
        activity.openActivity(DetailActivity::class.java, bundle)
    }
}