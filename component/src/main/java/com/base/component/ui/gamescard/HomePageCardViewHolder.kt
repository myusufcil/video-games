package com.base.component.ui.gamescard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.base.component.R
import com.base.core.extensions.loadImage
import com.base.core.ui.recyclerview.DisplayItem
import com.base.core.ui.recyclerview.ViewHolder
import com.base.core.ui.recyclerview.ViewHolderBinder
import com.base.core.ui.recyclerview.ViewHolderFactory
import javax.inject.Inject

/*
Created by Muhammed Yusuf Çil
on 1/12/21
*/
class HomePageCardViewHolder(var view: View) : ViewHolder<HomePageCardDTO>(view) {
    private val ivPhotoPath = view.findViewById<ImageView>(R.id.iv_home_page_game_card)
    private val tvName = view.findViewById<TextView>(R.id.tv_home_page_game_card_name)
    private val tvRatingAndReleased =
        view.findViewById<TextView>(R.id.tv_home_page_game_card_rating_and_released)
    private val rootView = view.findViewById<LinearLayout>(R.id.rootView)

    override fun bind(item: HomePageCardDTO) {
        item.let { _item ->
            ivPhotoPath.loadImage(_item.photoPath ?: "")
            tvName.text = _item.gamesName ?: ""
            tvRatingAndReleased.text = "${_item.gamesRate} - ${_item.gamesReleased}"
        }
        rootView.setOnClickListener {
            itemClickListener?.invoke(item, adapterPosition)
        }

    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            HomePageCardViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_home_page_card,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as HomePageCardViewHolder).bind(item as HomePageCardDTO)
        }
    }
}