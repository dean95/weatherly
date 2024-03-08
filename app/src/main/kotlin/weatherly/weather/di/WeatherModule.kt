package weatherly.weather.di

import org.koin.dsl.module
import weatherly.weather.data.network.api.WeatherApi
import weatherly.weather.data.network.api.WeatherApiImpl

val weatherModule = module {

    single<WeatherApi> { WeatherApiImpl(client = get()) }
}
