package com.example.packagewalk.di

import com.example.packagewalk.data.authorization.Authorization
import com.example.packagewalk.data.authorization.impl.FirebaseAuthorizationImpl
import com.example.packagewalk.data.firebase.SendVerificationCode
import com.example.packagewalk.data.firebase.impl.SendVerificationCodeFirebase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthorizationModule {
    @Binds
    abstract fun providesAuthorizationRepository(impl: FirebaseAuthorizationImpl): Authorization

    @Binds
    abstract fun providesSendVerificationCode(impl: SendVerificationCodeFirebase): SendVerificationCode
}