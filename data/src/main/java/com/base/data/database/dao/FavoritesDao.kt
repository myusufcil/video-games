package com.base.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.base.data.database.model.FavGamesDTO
import io.reactivex.Single

/*
Created by Muhammed Yusuf Çil
on 1/13/21
*/

@Dao
interface FavoritesDao : BaseDao<FavGamesDTO> {

    @Query("DELETE FROM FavoriteGamesDTO WHERE gameId = :gameId")
    fun deleteById(gameId: Int?)

    @Query("SELECT * FROM FavoriteGamesDTO")
    fun getAllGamesCard(): Single<List<FavGamesDTO>>

    @Query("DELETE FROM FavoriteGamesDTO")
    fun deleteAll()

}