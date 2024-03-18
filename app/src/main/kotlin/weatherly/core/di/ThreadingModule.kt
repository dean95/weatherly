package weatherly.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BACKGROUND_DISPATCHER = "BACKGROUND_DISPATCHER"
const val MAIN_DISPATCHER = "MAIN_DISPATCHER"
const val APPLICATION_SCOPE = "APPLICATION_SCOPE"

val threadingModule = module {

    single(named(BACKGROUND_DISPATCHER)) { Dispatchers.IO }

    single<CoroutineDispatcher>(named(MAIN_DISPATCHER)) { Dispatchers.Main }

    single(named(APPLICATION_SCOPE)) {
        CoroutineScope(
            SupervisorJob() + get<CoroutineDispatcher>(
                named(BACKGROUND_DISPATCHER)
            )
        )
    }
}
