package com.igorvd.chuckfacts.di.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

/**
 * Workaround in Java due to Dagger/Kotlin not playing well together as of now
 * https://github.com/google/dagger/issues/1478
 * https://youtrack.jetbrains.com/issue/KT-30979
 */
@MapKey
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}