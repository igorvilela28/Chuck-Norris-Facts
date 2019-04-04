package com.igorvd.chuckfacts.utils.test_di

import com.igorvd.chuckfacts.MyApplication
import com.igorvd.chuckfacts.di.core.BuilderModule
import com.igorvd.chuckfacts.utils.app.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Igor Vilela
 * @since 27/12/17
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        TestAppModule::class,
        BuilderModule::class)
)
interface TestAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: TestApplication): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: TestApplication)

}