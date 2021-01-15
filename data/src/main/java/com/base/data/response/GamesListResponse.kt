package com.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
Created by Muhammed Yusuf Çil
on 1/13/21
*/
@Parcelize
data class GamesListResponse(
    val count: Int?,
    val next:String?,
    val previous:String?,
    val results:List<Results?>,
    val seo_title:String?,
    val seo_description:String?,
    val seo_h1:String?,
    val noindex:Boolean?,
    val nofollow:Boolean?,
    val description:String?,
    val filters:Filters
    //val nofollow_collections:List<FollowCollections>
):Parcelable

@Parcelize
data class Results(
        val id:Int?,
        val slug:String?,
        val name:String,
        val released:String?,
        val tba:Boolean?,
        val background_image:String?,
        val rating:Double?,
        val rating_top:Double?,
        val ratings:List<Ratings>,
        val ratings_count:Int?,
        val reviews_text_count:String?,
        val added:Int?,
        val added_by_status:AddByStatus,
        val metacritic:Int?,
        val playtime:Int?,
        val suggestions_count:Int?,
        val updated:String?,
        //val user_game:String?,
        val reviews_count:String?,
        val saturated_color:String?,
        val dominant_color:String?
):Parcelable

@Parcelize
data class AddByStatus(
        val yet:Int?,
        val owned:Int?,
        val beaten:Int?,
        val toplay:Int?,
        val dropped:Int?,
        val playing:Int?
):Parcelable

@Parcelize
data class Ratings(
       val id:Int?,
       val title:String?,
       val count: Int?,
       val percent:Double
):Parcelable


@Parcelize
data class Filters(
        val years:List<Years>
):Parcelable

@Parcelize
data class Years(
       val from:Int?,
       val to:Int?,
       val filter:String?,
       val decade:Int?,
       val years:List<YearsChild>,
       val nofollow: Boolean?,
       val count: Int?
):Parcelable

@Parcelize
data class YearsChild(
        val year:Int?,
        val count:Int?,
        val nofollow: Boolean?
):Parcelable