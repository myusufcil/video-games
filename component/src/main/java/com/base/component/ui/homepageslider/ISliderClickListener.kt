package com.base.component.ui.homepageslider

import com.base.core.ui.recyclerview.DisplayItem

/*
  Created by Muhammed Yusuf ÇİL
  3.07.2020 - 15:25    
  myusufcl7@gmail.com
*/

interface ISliderClickListener {
    fun onClickListener(position: Int?, item: DisplayItem?)
}