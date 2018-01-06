package com.nomrasco.roomsimple.libdb.daos

import android.arch.persistence.room.*


interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg obj: T) : List<Long>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(obj: T) : Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T) : Int

    @Delete
    fun delete(obj: T) : Int
}