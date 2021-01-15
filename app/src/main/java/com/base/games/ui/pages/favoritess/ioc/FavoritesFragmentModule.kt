package com.base.games.ui.pages.favoritess.ioc

import androidx.fragment.app.Fragment
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.database.dao.FavoritesDao
import com.base.data.request.GamesApiInterface
import com.base.games.ioc.keys.FragmentViewModelKey
import com.base.games.ui.base.fragment.BaseViewModelFragmentModule
import com.base.games.ui.base.viewmodel.BaseFragmentViewModel
import com.base.games.ui.pages.detailgames.repository.DetailGamesFragmentLocalData
import com.base.games.ui.pages.favoritess.FavoritesFragment
import com.base.games.ui.pages.favoritess.repository.FavoritesFragmentLocaleData
import com.base.games.ui.pages.favoritess.repository.FavoritesFragmentRemoteData
import com.base.games.ui.pages.favoritess.repository.FavoritesFragmentRepository
import com.base.games.ui.pages.favoritess.viewmodel.FavoritesFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

/*
Created by Muhammed Yusuf Çil
on 11/28/20
*/


@Module(includes = [BaseViewModelFragmentModule::class])
abstract class FavoritesFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: FavoritesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(FavoritesFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: FavoritesFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideGamesLocalData(favDao: FavoritesDao) =
            FavoritesFragmentLocaleData(favDao)

        @Provides
        @FragmentScope
        @JvmStatic
        fun bottomHomeRemoteData(apiInterface: GamesApiInterface) =
            FavoritesFragmentRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun bottomSearchRepository(
            remote: FavoritesFragmentRemoteData,
            locale: FavoritesFragmentLocaleData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = FavoritesFragmentRepository(remote,locale, scheduler, compositeDisposable)
    }
}