package com.example.packagewalk.di

import com.example.packagewalk.core.data.PrefsStore
import com.example.packagewalk.framework.PrefsStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class StoreModule {

    @Binds
    abstract fun bindsPrefsStore(impl: PrefsStoreImpl): PrefsStore
}