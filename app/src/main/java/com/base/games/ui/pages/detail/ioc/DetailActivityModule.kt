package com.base.games.ui.pages.detail.ioc

import androidx.appcompat.app.AppCompatActivity
import com.base.core.ioc.scopes.ActivityScope
import com.base.core.networking.Scheduler
import com.base.data.request.GamesApiInterface
import com.base.games.ioc.builder.FragmentBuilderModule
import com.base.games.ioc.keys.ActivityViewModelKey
import com.base.games.ioc.modules.games.GamesServiceModule
import com.base.games.ui.base.activity.BaseActivityModule
import com.base.games.ui.base.viewmodel.BaseActivityViewModel
import com.base.games.ui.pages.detail.DetailActivity
import com.base.games.ui.pages.detail.repository.DetailActivityRemoteData
import com.base.games.ui.pages.detail.repository.DetailActivityRepository
import com.base.games.ui.pages.detail.viewmodel.DetailActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable


@Module(includes = [BaseActivityModule::class, FragmentBuilderModule::class, GamesServiceModule::class])
abstract class DetailActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: DetailActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(DetailActivityViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(activityViewModel: DetailActivityViewModel): BaseActivityViewModel

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideDetailActivityRemoteData(apiInterface: GamesApiInterface) =
            DetailActivityRemoteData(apiInterface)

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideDetailActivityRepository(
            remote: DetailActivityRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = DetailActivityRepository(remote, scheduler, compositeDisposable)
    }
}