package com.base.games.ioc.modules.games

import com.base.core.ioc.scopes.ActivityScope
import com.base.data.request.GamesApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class GamesServiceModule {

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideGamesInterface(retrofit: Retrofit): GamesApiInterface =
            retrofit.create(GamesApiInterface::class.java)

    }
}
