package com.base.games.ui.pages.detail.viewmodel

import com.base.games.ui.base.viewmodel.BaseActivityViewModel
import com.base.games.ui.pages.detail.repository.DetailActivityRepository
import javax.inject.Inject


class DetailActivityViewModel @Inject constructor(
    private val repository: DetailActivityRepository
) : BaseActivityViewModel() {
}