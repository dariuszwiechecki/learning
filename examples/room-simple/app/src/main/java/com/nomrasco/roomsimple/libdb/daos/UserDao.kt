package com.nomrasco.roomsimple.libdb.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.nomrasco.roomsimple.libdb.entities.User

@Dao
interface UserDao : BaseDao<User>
{
    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun getAll(): List<User>

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun getAllObservable(): LiveData<List<User>>
}