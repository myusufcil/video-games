package com.base.component.ui.detailgames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
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
on 1/13/21
*/

class DetailGamesViewHolder(var view: View) : ViewHolder<DetailGamesDTO>(view) {
    private val ivPhotoPath = view.findViewById<ImageView>(R.id.iv_detail_games)
    private val ibtnFav = view.findViewById<ImageButton>(R.id.btn_fav)
    private val tvName = view.findViewById<TextView>(R.id.tv_name)
    private val tvMetacritic = view.findViewById<TextView>(R.id.tv_metacritic_rate)
    private val tvDesc = view.findViewById<TextView>(R.id.tv_description)
    private val tvReleased =
        view.findViewById<TextView>(R.id.tv_relased_date)

    override fun bind(item: DetailGamesDTO) {
        item.let {
            ivPhotoPath.loadImage(it.imagePath ?: "")
            tvName.text = it.nameOfGame
            tvMetacritic.text = it.metacriticRate
            tvDesc.text = it.description
            tvReleased.text = it.releasedDate
        }
        ibtnFav.setOnClickListener {
            itemClickListener?.invoke(item, adapterPosition)
        }
    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            DetailGamesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_games_detail,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as DetailGamesViewHolder).bind(item as DetailGamesDTO)
        }
    }
}