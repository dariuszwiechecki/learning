package com.nomrasco.roomsimple.di

import com.nomrasco.roomsimple.features.InitialActivity
import com.nomrasco.roomsimple.libdb.DatabaseModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    DebugModule::class,
    DatabaseModule::class
])
interface AppComponent
{
    fun injectInto(target: InitialActivity)
}