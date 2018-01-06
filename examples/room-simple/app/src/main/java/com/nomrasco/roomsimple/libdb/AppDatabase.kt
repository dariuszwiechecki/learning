package com.nomrasco.roomsimple.libdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.nomrasco.roomsimple.libdb.converters.JodaTimeConverter
import com.nomrasco.roomsimple.libdb.daos.PetDao
import com.nomrasco.roomsimple.libdb.daos.UserDao
import com.nomrasco.roomsimple.libdb.entities.Pet
import com.nomrasco.roomsimple.libdb.entities.User

@Database(
        entities = [
            (User::class),
            (Pet::class)
        ],
        version = 3,
        exportSchema = false
)
@TypeConverters(
        JodaTimeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun petDao(): PetDao

    companion object {
        const val DATABASE_NAME = "database"
    }
}