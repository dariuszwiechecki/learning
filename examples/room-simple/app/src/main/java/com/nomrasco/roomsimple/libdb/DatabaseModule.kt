package com.nomrasco.roomsimple.libdb

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.nomrasco.roomsimple.libdb.daos.PetDao
import com.nomrasco.roomsimple.libdb.daos.UserDao
import com.nomrasco.roomsimple.libdb.migrations.Migration_1_2
import com.nomrasco.roomsimple.libdb.migrations.Migration_2_3
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context, log: AnkoLogger): AppDatabase {
        // add .allowMainThreadQueries() before build() to make it possible to run from main loop
        return Room
                .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                // .fallbackToDestructiveMigration() - destruct old DB if there is some issue
                // .allowMainThreadQueries() - do not require to run from background thread
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        log.info("Database created!")

                        doAsync {
                            // we can pre populate DB here!

                            // run Application a notification that data is ready to be used
                        }
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        log.info("Database Opened!")
                    }
                })
                .addMigrations(
                        Migration_1_2(),
                        Migration_2_3()
                )
                .build()
    }

    @Provides
    fun providePetDao(db: AppDatabase): PetDao = db.petDao()

    @Provides
    fun providesUserDao(db: AppDatabase): UserDao = db.userDao()
}
