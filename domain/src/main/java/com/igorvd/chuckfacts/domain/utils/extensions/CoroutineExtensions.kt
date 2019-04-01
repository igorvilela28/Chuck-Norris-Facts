package com.igorvd.chuckfacts.domain.utils.extensions

import kotlinx.coroutines.*

/**
 *
 * @author Igor Vilela
 * @since 05/04/2018
 */

val ioDispatcher: CoroutineDispatcher
    get() = try {
        Dispatchers.IO
    } catch (e: Exception) {
        /* IMPORTANTE: Em testes unitários não é possível depender de uma implementação do Framework do
         * Android, e por isso, adicionamos o try-catch e estamos utilizando o [CommonPool] como
          * dispatch para casos em que o executor do AsyncTask não está disponível*/
        Dispatchers.Default
    }

/**
 * Inicia uma nova coroutine utilizando o builder [launch] com o contexto [UI]
 */
fun launchUI(block: suspend CoroutineScope.() -> Unit): Job {
    return GlobalScope.launch(Dispatchers.Main, block = block)
}

/*suspend fun <T> withIOContext (block: suspend () -> T): T {

    return withContext(ioDispatcher, block = block)

}*/

/**
 * Util para podermos cancelar coroutines e pegar exceções genericas, visto que quando uma
 * coroutine é cancelada, é lançada um [CancellationException].
 *
 * [Saiba mais](https://medium.com/@andrea.bresolin/playing-with-kotlin-in-android-coroutines-and-how-to-get-rid-of-the-callback-hell-a96e817c108b)
 */
suspend fun CoroutineScope.tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        handleCancellationExceptionManually: Boolean = false) {
    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException ||
                handleCancellationExceptionManually) {
            catchBlock(e)
        } else {
            throw e
        }
    }
}

