package com.base.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.base.core.ui.recyclerview.DisplayItem
import kotlinx.android.parcel.Parcelize

/*
Created by Muhammed Yusuf Çil
on 1/13/21
*/

@Entity(tableName = "FavoriteGamesDTO")
data class FavGamesDTO(
    @PrimaryKey(autoGenerate = true)val id:Int=0,
    val releasedDate:String?,
    val gameName:String?,
    val gameId:String?,
    val description:String?,
    val metacriticRate:String?,
    val photoPath:String?
)