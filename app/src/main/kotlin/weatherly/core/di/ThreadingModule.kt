package weatherly.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BACKGROUND_DISPATCHER = "BACKGROUND_DISPATCHER"
const val MAIN_DISPATCHER = "MAIN_DISPATCHER"

val threadingModule = module {

    single(named(BACKGROUND_DISPATCHER)) { Dispatchers.IO }

    single<CoroutineDispatcher>(named(MAIN_DISPATCHER)) { Dispatchers.Main }
}
