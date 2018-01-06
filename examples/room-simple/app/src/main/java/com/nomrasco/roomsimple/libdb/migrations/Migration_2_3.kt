package com.nomrasco.roomsimple.libdb.migrations

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import com.nomrasco.roomsimple.libdb.entities.Pet
import com.nomrasco.roomsimple.libdb.entities.User

/*
Added "added" fields to User and Pet entities
 */
class Migration_2_3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
                "ALTER TABLE ${User.TABLE_NAME} ADD COLUMN ${User.COL_ADDED_DATE_NAME} INTEGER"
        )
        database.execSQL(
                "ALTER TABLE ${Pet.TABLE_NAME} ADD COLUMN ${Pet.COL_ADDED_DATE_NAME} INTEGER"
        )

        // remember that in result "added" should be marked as a nullable (Long?) type
    }
}