package com.igorvd.chuckfacts.di.core

import com.igorvd.chuckfacts.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

/**
 * @author Igor Vilela
 * @since 27/12/17
 */
@FlowPreview
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuilderModule::class)
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)

}