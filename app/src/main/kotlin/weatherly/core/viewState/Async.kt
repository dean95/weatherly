package weatherly.core.viewState

sealed class Async<out T> {

    data object Uninitialized : Async<Nothing>()

    data object Loading : Async<Nothing>()

    data class Success<out T>(val value: T) : Async<T>()

    data class Fail<out T>(val error: Throwable) : Async<T>()
}
