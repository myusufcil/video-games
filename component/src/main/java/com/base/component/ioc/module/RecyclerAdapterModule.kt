package com.base.component.ioc.module

import com.base.component.GamesRecyclerviewAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerAdapterModule {

	@Provides
	//@Singleton
	fun provideAdapter(): GamesRecyclerviewAdapter {
		return GamesRecyclerviewAdapter()
	}
}
