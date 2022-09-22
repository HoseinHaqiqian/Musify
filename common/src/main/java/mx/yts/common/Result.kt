package mx.yts.common

sealed class Result<out T>() {

    data class Success<R>(var data: R) : Result<R>()
    data class Failure(var message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()

}