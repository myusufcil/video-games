package com.base.games.ui.base.viewmodel

import android.os.Bundle

abstract class BaseFragmentViewModel : BaseViewControllerViewModel() {

    open fun handleArguments(argument: Bundle) {}
}