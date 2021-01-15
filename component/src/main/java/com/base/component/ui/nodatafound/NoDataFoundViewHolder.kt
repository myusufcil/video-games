package com.base.component.ui.nodatafound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.base.component.R
import com.base.core.ui.recyclerview.DisplayItem
import com.base.core.ui.recyclerview.ViewHolder
import com.base.core.ui.recyclerview.ViewHolderBinder
import com.base.core.ui.recyclerview.ViewHolderFactory
import javax.inject.Inject

/*
Created by Muhammed Yusuf Çil
on 1/14/21
*/
class NoDataFoundViewHolder(var view: View) : ViewHolder<NoDataFoundDTO>(view) {
    private var tvNotFound = view.findViewById<TextView>(R.id.tv_not_found)
    override fun bind(item: NoDataFoundDTO) {
        item.title.let {
            tvNotFound.text = it
        }
    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            NoDataFoundViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_no_data_found,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as NoDataFoundViewHolder).bind(item as NoDataFoundDTO)
        }
    }

}