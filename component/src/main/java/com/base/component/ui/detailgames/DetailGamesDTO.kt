package com.base.component.ui.detailgames

import android.os.Parcelable
import com.base.component.constant.RecyclerviewAdapterConstant
import com.base.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

/*
Created by Muhammed Yusuf Çil
on 1/13/21
*/
@Parcelize
data class DetailGamesDTO(
    var imagePath:String?,
    var nameOfGame:String?,
    var releasedDate:String?,
    var metacriticRate:String?,
    var description:String?,
    var gameId:String?
):Parcelable, DisplayItem(RecyclerviewAdapterConstant.TYPES.TYPE_DETAIL_GAMES)