package com.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
Created by Muhammed Yusuf Çil
on 1/13/21
*/

@Parcelize
data class GamesDetailResponse(
     val id:String?,
     val name:String?,
     val description:String?,
     val released:String?,
     val metacritic:String?,
     val background_image:String?
):Parcelable