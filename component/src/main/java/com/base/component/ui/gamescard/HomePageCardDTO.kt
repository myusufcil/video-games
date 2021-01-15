package com.base.component.ui.gamescard

import android.os.Parcelable
import com.base.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_HOME_PAGE_GAMES_CARDS
import com.base.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

/*
Created by Muhammed Yusuf Çil
on 1/12/21
*/
@Parcelize
data class HomePageCardDTO(
    var photoPath:String?,
    var gamesName:String?,
    var gamesRate:String?,
    var gamesReleased:String?,
    var id:Int
):Parcelable,DisplayItem(TYPE_HOME_PAGE_GAMES_CARDS)