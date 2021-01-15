package com.base.games.ioc.builder

import com.base.core.ioc.scopes.FragmentScope
import com.base.games.ui.pages.detailgames.DetailGamesFragment
import com.base.games.ui.pages.detailgames.ioc.DetailGamesFragmentModule
import com.base.games.ui.pages.home.HomeFragment
import com.base.games.ui.pages.home.ioc.HomeFragmentModule
import com.base.games.ui.pages.favoritess.FavoritesFragment
import com.base.games.ui.pages.favoritess.ioc.FavoritesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    abstract fun contributeProfileFragment(): FavoritesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailGamesFragmentModule::class])
    abstract fun contributeDetailGamesFragment(): DetailGamesFragment

}