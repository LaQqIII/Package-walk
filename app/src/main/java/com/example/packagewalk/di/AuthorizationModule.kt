package com.example.packagewalk.di

import com.example.packagewalk.core.data.FirebaseMobileAuthorizationRepository
import com.example.packagewalk.core.data.MobileAuthorization
import com.example.packagewalk.core.data.MobileAuthorizationDataSource
import com.example.packagewalk.framework.mobileAuthorization.FirebaseMobileAuthorization
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthorizationModule {

    @Binds
    abstract fun bindsMobileAuthorization(
        impl: FirebaseMobileAuthorizationRepository
    ): MobileAuthorization

    @Binds
    abstract fun bindsMobileAuthorizationDataSource(
        impl: FirebaseMobileAuthorization
    ): MobileAuthorizationDataSource
}