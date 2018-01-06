package com.nomrasco.roomsimple.libdb.entities

import android.arch.persistence.room.*
import android.graphics.Bitmap
import org.joda.time.DateTime

@Entity(
        tableName = Pet.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = User::class,
                    parentColumns = [User.COL_ID_NAME],
                    childColumns = [Pet.COL_OWNER_NAME],
                    onDelete = ForeignKey.CASCADE
            )
        ],
        indices = [
            (Index(Pet.COL_OWNER_NAME))
        ]
)
data class Pet(
        @field:PrimaryKey(autoGenerate = true) @field:ColumnInfo(name = COL_ID_NAME) var id: Long = 0,
        @field:ColumnInfo(name = COL_NAME_NAME) var name: String = "",
        @field:ColumnInfo(name = COL_AGE_NAME) var age: Int = 0,
        @field:ColumnInfo(name = COL_OWNER_NAME) var owner: Long = -1,
        @field:ColumnInfo(name = COL_ADDED_DATE_NAME) var added: DateTime? = DateTime.now() // added in DB version 3
) {
    companion object {
        const val TABLE_NAME = "pets"
        const val COL_ID_NAME = "id"
        const val COL_NAME_NAME = "name"
        const val COL_AGE_NAME = "age"
        const val COL_OWNER_NAME = "owner"
        const val COL_ADDED_DATE_NAME = "added"
    }

    @Ignore
    lateinit var bitmap: Bitmap
}