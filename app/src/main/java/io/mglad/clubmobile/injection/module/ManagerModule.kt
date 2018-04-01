package io.mglad.clubmobile.injection.module

import dagger.Module
import dagger.Provides
import io.mglad.clubmobile.UserManager
import javax.inject.Singleton

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object ManagerModule {
    /**
     * Provides the UserManager
     * @return the UserManager
     */
    @Provides
    @Singleton
    @JvmStatic
    internal fun provideUserManager(): UserManager {
        return UserManager()
    }
}