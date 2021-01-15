package com.base.component.ui.nodatafound

import android.os.Parcelable
import com.base.component.constant.RecyclerviewAdapterConstant.TYPES.TYPE_NO_DATA_FOUND
import com.base.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

/*
Created by Muhammed Yusuf Çil
on 1/14/21
*/

@Parcelize
data class NoDataFoundDTO(
    var title:String?
):Parcelable,DisplayItem(TYPE_NO_DATA_FOUND)