@file:Suppress("unused")

package com.base.games.ui.pages.main.ioc

import androidx.appcompat.app.AppCompatActivity
import com.base.core.ioc.scopes.ActivityScope
import com.base.core.networking.Scheduler
import com.base.games.ioc.modules.games.GamesServiceModule
import com.base.games.ioc.builder.FragmentBuilderModule
import com.base.games.ioc.keys.ActivityViewModelKey
import com.base.games.ui.pages.main.MainActivity
import com.base.games.ui.pages.main.viewmodel.MainActivityViewModel
import com.base.games.ui.base.activity.BaseActivityModule
import com.base.games.ui.base.viewmodel.BaseActivityViewModel
import com.base.data.request.GamesApiInterface
import com.base.games.ui.pages.main.repository.MainActivityRemoteData
import com.base.games.ui.pages.main.repository.MainActivityRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseActivityModule::class, FragmentBuilderModule::class, GamesServiceModule::class])
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: MainActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(MainActivityViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(activityViewModel: MainActivityViewModel): BaseActivityViewModel

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideHomeActivityRemoteData(apiInterface: GamesApiInterface) =
            MainActivityRemoteData(apiInterface)

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideHomeActivityRepository(
            remote: MainActivityRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = MainActivityRepository(remote, scheduler, compositeDisposable)
    }
}
