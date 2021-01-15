package com.base.games.ioc.modules.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.base.data.database.dao.ColorsDao
import com.base.data.database.dao.FavoritesDao
import com.base.data.database.model.Color
import com.base.data.database.model.FavGamesDTO

@Database(entities = [Color::class, FavGamesDTO::class], version = 1, exportSchema = false)
//*[ArrayConverter::class] dto içerisinde farklı bir dto tanımlarsan bunu kullanman gerekli
abstract class AppDatabase : RoomDatabase() {
	abstract fun colorsDao(): ColorsDao
	abstract fun favoritesDao(): FavoritesDao
}
