package com.nomrasco.roomsimple.di

import dagger.Module
import dagger.Provides
import org.jetbrains.anko.AnkoLogger

@Module
class DebugModule
{
    @Provides
    fun provideLogger(): AnkoLogger = AnkoLogger("App")
}