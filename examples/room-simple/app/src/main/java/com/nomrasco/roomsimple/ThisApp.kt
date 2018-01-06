package com.nomrasco.roomsimple

import android.app.Application
import com.facebook.stetho.Stetho
import com.nomrasco.roomsimple.di.AppComponent
import com.nomrasco.roomsimple.di.AppModule
import com.nomrasco.roomsimple.di.DaggerAppComponent
import com.nomrasco.roomsimple.libdb.DatabaseModule

/**
 * Created by nomrasco on 06/01/2018.
 */
class ThisApp : Application() {
    companion object {
        lateinit var Injector: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Injector = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .databaseModule(DatabaseModule())
                .build()
        Stetho.initializeWithDefaults(this)
    }


}