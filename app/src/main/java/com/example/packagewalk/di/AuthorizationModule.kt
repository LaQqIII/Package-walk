package com.example.packagewalk.di

import com.example.packagewalk.data.authorization.Authorization
import com.example.packagewalk.data.authorization.impl.FirebaseAuthorizationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthorizationModule {

    @Binds
    abstract fun providesAuthorizationRepository(impl: FirebaseAuthorizationImpl): Authorization
}