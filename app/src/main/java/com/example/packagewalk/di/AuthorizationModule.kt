package com.example.packagewalk.di

import com.example.core.interactors.CheckVerificationCode
import com.example.packagewalk.framework.Interactors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//
//import com.example.core.data.FirebaseMobileAuthorizationRepository
//import com.example.core.data.MobileAuthorization
//import com.example.core.data.MobileAuthorizationDataSource
//import com.example.core.interactors.CheckVerificationCode
//import com.example.packagewalk.framework.mobileAuthorization.FirebaseMobileAuthorization
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//
////@Module
////@InstallIn(ViewModelComponent::class)
////class AuthorizationModule {
////
////    //abstract fun provides
////
//////    @Binds
//////    abstract fun providesSendVerificationCode(impl: SendVerificationCode): SendCode
//////
//////    @Binds
//////    abstract fun providesCheckVerificationCode(impl: CheckVerificationCode): CheckCode
//////
//////    @Binds
//////    abstract fun providesSignInWithPhone(impl: SignInWithPhone): SignIn
//////
//////    @Binds
//////    abstract fun providesCheckLoggedInUser(impl: CheckLoggedInUser): CheckLogged
////
//////    @Binds
//////    fun providesMobileAuthorization(impl: FirebaseMobileAuthorization): MobileAuthorization
////}

@Module
@InstallIn(ViewModelComponent::class)
class AuthorizationModule {
    @Provides
    fun providesS(): Interactors = Interactors(2)
}