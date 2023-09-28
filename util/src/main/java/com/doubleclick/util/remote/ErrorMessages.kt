
package com.doubleclick.util.remote

/**
 * Error messages to display in event of exception with api.
 */
object ErrorMessages {
    /**
     * Message to show when the connection fails.
     */
    const val CONNECT_EXCEPTION =
        "Could not connect to the server. Please check your internet connection."

    /**
     * Message to show when the connection times out fails.
     */
    const val SOCKET_TIME_OUT_EXCEPTION =
        "Request timed out while trying to connect. Please check your connection."

    /**
     * Message to show when an unforeseen error occurs.
     */
    const val UNKNOWN_NETWORK_EXCEPTION =
        "An unexpected error has occurred. Please check your connection."

    /**
     * Message to show when the domain host can't be found.
     */
    const val UNKNOWN_HOST_EXCEPTION =
        "Couldn't connect to the server at the moment."

    /**
     * Message to show when the server refuses access.
     */
    const val FORBIDDEN_EXCEPTION =
        "You're not authorised to connect at this moment. Please contact support."

    /**
     * Message to show when the the client doesn't have access to the server.
     */
    const val UNAUTHORIZED_EXCEPTION =
        "Your token has expired. Please login again with an authorized account."

    /**
     * Message to show when the protocol fails to process.
     */
    const val PROTOCOL_EXCEPTION =
        "An error occurred while connecting to the server. Please try again."

    /**
     * Message to show when the server fails.
     */
    const val SERVER_EXCEPTION =
        "An unexpected server error has occurred. Please contact support."
}
