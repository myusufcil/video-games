package com.base.games.ui.pages.favoritess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.base.component.GamesRecyclerviewAdapter
import com.base.component.ui.gamescard.HomePageCardDTO
import com.base.core.extensions.setup
import com.base.core.networking.DataFetchResult
import com.base.games.R
import com.base.games.ui.base.fragment.BaseViewModelFragment
import com.base.games.ui.common.navigation.NavigationHelper
import com.base.games.ui.pages.favoritess.viewmodel.FavoritesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class FavoritesFragment : BaseViewModelFragment<FavoritesFragmentViewModel>() {

    @Inject
    lateinit var adapter: GamesRecyclerviewAdapter

    var myLocalList: MutableList<HomePageCardDTO> = mutableListOf()


    override val viewModelClass: Class<FavoritesFragmentViewModel> =
        FavoritesFragmentViewModel::class.java

    override val layoutViewRes: Int = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcFavPage.setup(adapter = adapter.getAdapter())

        adapter.getAdapter().itemClickListener = { _item, _position ->
            val navigationHelper = NavigationHelper(requireActivity())
            when (_item) {
                is HomePageCardDTO -> navigationHelper.navigate(_item)
            }
        }
        viewModel.getData()

        viewModel.savedNewsDataStatusResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    Log.d("MY LIST REQUEST ERROR", it.e.localizedMessage)
                }
                is DataFetchResult.Progress -> {

                }
                is DataFetchResult.Success -> {
                    it.data?.let { _data ->
                        val data = _data as MutableList<HomePageCardDTO>
                        myLocalList = mutableListOf()
                        data.forEach { _item ->
                            myLocalList.add(_item)
                        }
                        adapter.getAdapter().updateAllItems(myLocalList.toSet().toList())
                    }
                }
            }
        })
    }

}