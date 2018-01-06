package com.nomrasco.roomsimple.libdb.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.nomrasco.roomsimple.libdb.entities.Pet

@Dao
interface PetDao : BaseDao<Pet>
{
    @Query("SELECT * FROM ${Pet.TABLE_NAME}")
    fun getAll(): List<Pet>

    //@Query("SELECT * FROM ${Pet.TABLE_NAME} WHERE ${Pet.COL_OWNER_NAME} = :parg0")
    //fun getAllPetsOf(owner: Int): List<Pet>
}