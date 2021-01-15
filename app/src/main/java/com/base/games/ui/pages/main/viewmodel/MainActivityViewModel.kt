package com.base.games.ui.pages.main.viewmodel

import com.base.core.ioc.scopes.ActivityScope
import com.base.games.ui.base.viewmodel.BaseActivityViewModel
import com.base.games.ui.pages.main.repository.MainActivityRepository
import javax.inject.Inject


@ActivityScope
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository
) : BaseActivityViewModel() {

}