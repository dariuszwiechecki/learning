package com.nomrasco.roomsimple.libdb.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.nomrasco.roomsimple.libdb.entities.User
import com.nomrasco.roomsimple.libdb.helpers.getDistinct

@Dao
abstract class UserDao : BaseDao<User>
{
    @Query("SELECT * FROM ${User.TABLE_NAME}")
    abstract fun getAll(): List<User>

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    abstract fun getAllObservable(): LiveData<List<User>>

    fun getAllDistinctObservable() = getAllObservable().getDistinct()

    @Query("SELECT ${User.COL_FIRST_NAME_NAME}, ${User.COL_SURNAME_NAME} FROM ${User.TABLE_NAME}")
    abstract fun getAllQuickly(): List<User.Mini>

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE id = :id")
    abstract fun getUserWithAllPets(id: Long): User.WithAllPets?
}