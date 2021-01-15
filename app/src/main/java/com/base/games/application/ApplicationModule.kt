@file:Suppress("unused")

package com.base.games.application

import android.app.Application
import android.content.Context
import com.base.component.ioc.module.RecyclerAdapterModule
import com.base.core.ioc.modules.*
import com.base.core.ioc.qualifiers.ApplicationContext
import com.base.games.ioc.builder.ActivityBuilderModule
import com.base.games.ioc.modules.games.GamesDataBaseModule
import com.base.games.ioc.modules.games.GamesServiceModule
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        ImageModule::class,
        SystemServiceModule::class,
        TimberModule::class,
        UtilsModule::class,
        AndroidSupportInjectionModule::class,
        RecyclerAdapterModule::class,
        ActivityBuilderModule::class,
        GamesDataBaseModule::class
    ]
)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: com.base.games.application.Application): Application

    @Binds
    @Singleton
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context

}