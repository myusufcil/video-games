package com.base.games.ioc.modules.games

import android.content.Context
import androidx.room.Room
import com.base.data.database.dao.FavoritesDao
import com.base.games.application.Application
import com.base.games.ioc.modules.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/*
Created by Muhammed Yusuf Çil
on 1/14/21
*/
@Module
internal class GamesDataBaseModule {
    @Singleton
    @Provides
    fun provideDb(app: Context): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java, "games.db"
        ).build()
    }
    @Singleton
    @Provides
    fun provideNewsCardDao(db: AppDatabase): FavoritesDao {
        return db.favoritesDao()
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}