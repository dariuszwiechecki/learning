package com.nomrasco.roomsimple.libdb.converters

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime

class JodaTimeConverter
{
    @TypeConverter
    fun fromJoda(value: DateTime?): Long? = value?.millis ?: 0L

    @TypeConverter
    fun toJoda(value: Long?): DateTime? = if (value != null) DateTime(value) else null
}