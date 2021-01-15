package com.base.component.constant

import com.base.component.ui.detailgames.DetailGamesViewHolder
import com.base.component.ui.gamescard.HomePageCardViewHolder
import com.base.component.ui.homepageslider.HomePageSliderViewHolder
import com.base.component.ui.nodatafound.NoDataFoundViewHolder


class RecyclerviewAdapterConstant {

    object TYPES {
        const val TYPE_NONE = 0
        const val TYPE_HOME_PAGE_SLIDER = 1
        const val TYPE_SLIDER_ITEM = 2
        const val TYPE_HOME_PAGE_GAMES_CARDS = 3
        const val TYPE_DETAIL_GAMES = 4
        const val TYPE_NO_DATA_FOUND = 5
    }

    var binderFactoryMap = mutableMapOf(
        TYPES.TYPE_HOME_PAGE_SLIDER to HomePageSliderViewHolder.BinderFactory(),
        TYPES.TYPE_HOME_PAGE_GAMES_CARDS to HomePageCardViewHolder.BinderFactory(),
        TYPES.TYPE_DETAIL_GAMES to DetailGamesViewHolder.BinderFactory(),
        TYPES.TYPE_NO_DATA_FOUND to NoDataFoundViewHolder.BinderFactory()
    )

    var holderFactoryMap = mutableMapOf(
        TYPES.TYPE_HOME_PAGE_SLIDER to HomePageSliderViewHolder.HolderFactory(),
        TYPES.TYPE_HOME_PAGE_GAMES_CARDS to HomePageCardViewHolder.HolderFactory(),
        TYPES.TYPE_DETAIL_GAMES to DetailGamesViewHolder.HolderFactory(),
        TYPES.TYPE_NO_DATA_FOUND to NoDataFoundViewHolder.HolderFactory()
    )
}
