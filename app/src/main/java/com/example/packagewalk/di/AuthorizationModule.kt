package com.example.packagewalk.di

import com.example.packagewalk.data.authorization.mobile.RepositoryMobileAuthorization
import com.example.packagewalk.data.authorization.mobile.impl.FirebaseRepositoryMobileAuthorization
import com.example.packagewalk.data.usecases.CheckVerificationCode
import com.example.packagewalk.data.usecases.SendVerificationCode
import com.example.packagewalk.data.usecases.SignInWithPhone
import com.example.packagewalk.data.usecases.impl.FirebaseCheckVerificationCode
import com.example.packagewalk.data.usecases.impl.FirebaseSendVerificationCode
import com.example.packagewalk.data.usecases.impl.FirebaseSignInWithPhone
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthorizationModule {

    @Binds
    abstract fun providesSendVerificationCode(impl: FirebaseSendVerificationCode): SendVerificationCode

    @Binds
    abstract fun providesCheckVerificationCode(impl: FirebaseCheckVerificationCode): CheckVerificationCode

    @Binds
    abstract fun providesSignInWithPhone(impl: FirebaseSignInWithPhone): SignInWithPhone

    @Binds
    abstract fun providesMobileAuthorizationRepository(impl: FirebaseRepositoryMobileAuthorization): RepositoryMobileAuthorization
}