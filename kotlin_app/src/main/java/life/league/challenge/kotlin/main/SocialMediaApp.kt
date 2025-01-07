package life.league.challenge.kotlin.main

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class SocialMediaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialMediaApp)
            modules(appModule)
        }
    }
}