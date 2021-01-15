package com.base.component.ui.gamesdetailparagraph

import android.os.Parcelable
import com.base.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_GAMES_DETAIL_PARAGRAPH
import com.base.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

/*
Created by Muhammed Yusuf Çil
on 1/15/21
*/
@Parcelize
data class GamesDetailParagraphDTO(
    var desc: String?
) : Parcelable, DisplayItem(TYPE_GAMES_DETAIL_PARAGRAPH)