package mx.yts.domain

interface BaseUseCase<R> {
    suspend fun invoke() : R
}