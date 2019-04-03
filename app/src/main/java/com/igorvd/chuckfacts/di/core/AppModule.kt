package com.igorvd.chuckfacts.di.core

import android.content.Context
import com.igorvd.chuckfacts.MyApplication
import com.igorvd.chuckfacts.di.data.LocalModule
import com.igorvd.chuckfacts.di.data.RemoteModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Módulo que define/inclui todos objetos que devem ser injetados como [Singleton]. Ou seja,
 * você deve declarar ou incluir módulos que provem objetos a terem uma única instância compartilhada
 * por toda a aplicação
 *
 * @author Igor Vilela
 * @since 28/12/17
 */
@Module(includes = [
    LocalModule::class,
    RemoteModule::class
])
class AppModule {

    @Singleton
    @Provides
    @Named("application")
    fun providesApplicationContext(application: MyApplication): Context {
        return application.applicationContext
    }
}