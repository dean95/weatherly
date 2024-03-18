package weatherly.weather.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import weatherly.core.di.APPLICATION_SCOPE
import weatherly.weather.data.network.api.WeatherApi
import weatherly.weather.data.network.api.WeatherApiImpl
import weatherly.weather.domain.repository.WeatherRepository
import weatherly.weather.data.repository.WeatherRepositoryImpl
import weatherly.weather.domain.useCase.FetchFiveDayForecast
import weatherly.weather.domain.useCase.FetchLocationsForQuery

val weatherModule = module {

    single<WeatherApi> { WeatherApiImpl(client = get()) }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get(),
            applicationScope = get(named(APPLICATION_SCOPE))
        )
    }

    single { FetchLocationsForQuery(weatherRepository = get()) }

    single { FetchFiveDayForecast(weatherRepository = get()) }
}
