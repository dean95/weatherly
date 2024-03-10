package weatherly.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import weatherly.network.di.networkModule

class WeatherlyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@WeatherlyApplication)
            modules(getModules())
        }
    }

    private fun getModules() = listOf(
        networkModule
    )
}
