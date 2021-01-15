package com.base.component.ui.homepageslider

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.base.component.R
import com.base.core.extensions.loadImage
import com.squareup.picasso.Picasso


class HomePageSliderViewPagerAdapter(
        context: Context,
        itemList: ArrayList<SliderItem>,
        isInfinite: Boolean,
        private val sliderClickListener: ISliderClickListener
) : LoopingPagerAdapter<SliderItem>(context, itemList, isInfinite) {

    override fun inflateView(viewType: Int, container: ViewGroup?, position: Int): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_slider, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(view: View?, position: Int, viewType: Int) {
        val item = itemList[position]
        val sliderPhoto = view?.findViewById<ImageView>(R.id.iv_home_page_slider)
        val rootViewSlider = view?.findViewById<CardView>(R.id.rootView)
        item?.photoPath.let {
            sliderPhoto?.loadImage(item.photoPath ?: "")
        }

        rootViewSlider?.setOnClickListener {
            sliderClickListener.onClickListener(position, item)
        }
    }
}
