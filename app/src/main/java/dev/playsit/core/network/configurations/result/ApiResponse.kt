package dev.playsit.core.network.handlers.result

interface ApiResponse {
    val statusCode: Int
    val statusMessage: String?
}
