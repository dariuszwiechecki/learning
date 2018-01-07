package com.nomrasco.roomsimple.libdb.entities

import android.arch.persistence.room.*
import org.joda.time.DateTime

@Entity(tableName = User.TABLE_NAME)
data class User(
        @field:PrimaryKey(autoGenerate = true) @field:ColumnInfo(name = COL_ID_NAME) var id: Long = 0,
        @field:ColumnInfo(name = COL_FIRST_NAME_NAME) var firstName: String = "",
        @field:ColumnInfo(name = COL_SURNAME_NAME) var surname: String = "",
        @field:ColumnInfo(name = COL_AGE_NAME) var age: Int = 0,
        @field:ColumnInfo(name = COL_ADDED_DATE_NAME) var added: DateTime? = DateTime.now() // added in DB version 3
) {
    companion object {
        const val TABLE_NAME = "users"
        const val COL_ID_NAME = "id"
        const val COL_FIRST_NAME_NAME = "firstName"
        const val COL_SURNAME_NAME = "surname"
        const val COL_AGE_NAME = "age"
        const val COL_ADDED_DATE_NAME = "added"
    }

    data class Mini(
            @field:ColumnInfo(name = COL_FIRST_NAME_NAME) var name: String = "",
            @field:ColumnInfo(name = COL_SURNAME_NAME) var surname: String = ""
    )

    class WithAllPets {
        @Embedded
        var user: User? = null

        @Relation(
                parentColumn = User.COL_ID_NAME,
                entityColumn = Pet.COL_OWNER_NAME
        )
        var pets: List<Pet> = ArrayList()

        override fun toString(): String {
            return user.toString() + " pets:" + pets.toString()
        }
    }
}