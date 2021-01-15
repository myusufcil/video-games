package com.base.games.ui.pages.home.ioc

import androidx.fragment.app.Fragment
import com.base.core.ioc.scopes.FragmentScope
import com.base.core.networking.Scheduler
import com.base.data.request.GamesApiInterface
import com.base.games.ioc.keys.FragmentViewModelKey
import com.base.games.ui.base.fragment.BaseViewModelFragmentModule
import com.base.games.ui.base.viewmodel.BaseFragmentViewModel
import com.base.games.ui.pages.home.HomeFragment
import com.base.games.ui.pages.home.repository.HomeFragmentRemoteData
import com.base.games.ui.pages.home.repository.HomeFragmentRepository
import com.base.games.ui.pages.home.viewmodel.HomeFragmentViewModel
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
abstract class HomeFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(HomeFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: HomeFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun bottomHomeRemoteData(apiInterface: GamesApiInterface) =
            HomeFragmentRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun bottomSearchRepository(
            remote: HomeFragmentRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = HomeFragmentRepository(remote, scheduler, compositeDisposable)
    }
}