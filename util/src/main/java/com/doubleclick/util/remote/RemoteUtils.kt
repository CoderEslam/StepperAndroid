package com.doubleclick.util.remote

import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import retrofit2.HttpException

/**
 * Utility methods for handling remote functionality.
 */
object RemoteUtils {

    /**
     * Get the error message from an exception
     *
     * @param throwable the exception to fetch the error message from.
     * @param httpErrorCatcher block of code to handle retrofit [HttpException].
     *
     * @return The error message.
     */
    fun extractErrorMessage(
        throwable: Throwable,
        httpErrorCatcher: HttpException.() -> String = { "" }
    ): String {
        return when (throwable) {
            is ConnectException -> ErrorMessages.CONNECT_EXCEPTION
            is UnknownHostException -> ErrorMessages.UNKNOWN_HOST_EXCEPTION
            is SocketTimeoutException -> ErrorMessages.SOCKET_TIME_OUT_EXCEPTION
            is UnknownServiceException -> throwable.message ?: ErrorMessages.PROTOCOL_EXCEPTION
            is ProtocolException -> ErrorMessages.PROTOCOL_EXCEPTION
            is HttpException -> throwable.httpErrorCatcher()
            else -> throwable.message ?: ErrorMessages.UNKNOWN_NETWORK_EXCEPTION
        }
    }
}
