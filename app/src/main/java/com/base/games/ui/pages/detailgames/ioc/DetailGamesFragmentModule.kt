package com.base.games.ui.pages.detailgames.ioc

import androidx.fragment.app.Fragment
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.database.dao.FavoritesDao
import com.base.data.request.GamesApiInterface
import com.base.games.ioc.keys.FragmentViewModelKey
import com.base.games.ioc.modules.games.GamesDataBaseModule
import com.base.games.ui.base.fragment.BaseViewModelFragmentModule
import com.base.games.ui.base.viewmodel.BaseFragmentViewModel
import com.base.games.ui.pages.detailgames.DetailGamesFragment
import com.base.games.ui.pages.detailgames.repository.DetailGamesFragmentLocalData
import com.base.games.ui.pages.detailgames.repository.DetailGamesFragmentRemoteData
import com.base.games.ui.pages.detailgames.repository.DetailGamesFragmentRepository
import com.base.games.ui.pages.detailgames.viewmodel.DetailGamesFragmentViewModel
import com.base.games.ui.pages.favoritess.FavoritesFragment
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
abstract class DetailGamesFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: DetailGamesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(DetailGamesFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: DetailGamesFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun provideGamesLocalData(favDao: FavoritesDao) =
            DetailGamesFragmentLocalData(favDao)

        @Provides
        @FragmentScope
        @JvmStatic
        fun detailGamesRemoteData(apiInterface: GamesApiInterface) =
            DetailGamesFragmentRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun detailGamesRepository(
            remote: DetailGamesFragmentRemoteData,
            locale: DetailGamesFragmentLocalData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = DetailGamesFragmentRepository(remote, locale, scheduler, compositeDisposable)
    }
}