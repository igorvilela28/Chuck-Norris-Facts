package com.igorvd.chuckfacts.di.core

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@kotlin.annotation.MustBeDocumented
@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
