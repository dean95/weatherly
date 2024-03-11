package weatherly.weather.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import weatherly.core.di.BACKGROUND_DISPATCHER
import weatherly.weather.data.network.api.WeatherApi
import weatherly.weather.data.network.api.WeatherApiImpl
import weatherly.weather.data.repository.WeatherRepository
import weatherly.weather.data.repository.WeatherRepositoryImpl
import weatherly.weather.domain.useCase.FetchLocationsForQuery

val weatherModule = module {

    single<WeatherApi> { WeatherApiImpl(client = get()) }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get(),
            backgroundDispatcher = get(named(BACKGROUND_DISPATCHER))
        )
    }

    single { FetchLocationsForQuery(weatherRepository = get()) }
}
