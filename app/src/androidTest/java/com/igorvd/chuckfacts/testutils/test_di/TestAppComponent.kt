//package com.igorvd.chuckfacts.testutils.test_di
//
//import com.igorvd.chuckfacts.testutils.app.TestApplication
//import javax.inject.Singleton
//
///**
// * @author Igor Vilela
// * @since 27/12/17
// */
//@Singleton
//@Component(modules = arrayOf(
//        AndroidSupportInjectionModule::class,
//        TestAppModule::class,
//        BuilderModule::class)
//)
//interface TestAppComponent {
//
//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: TestApplication): Builder
//
//        fun build(): TestAppComponent
//    }
//
//    fun inject(app: TestApplication)
//
//}