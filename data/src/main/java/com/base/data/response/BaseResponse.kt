package com.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/*
Created by Muhammed Yusuf Çil
on 11/23/20
*/


@Parcelize
data class BaseResponse(
    var id: String?
) : Parcelable

