package com.base.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.base.data.database.model.Color
import io.reactivex.Flowable

@Dao
interface ColorsDao : BaseDao<Color> {

    @Query("SELECT * FROM color")
    fun getColors(): Flowable<List<Color>>
}
