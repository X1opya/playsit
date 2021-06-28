package dev.playsit.core.network.configurations.result

interface ApiResponse {
    val statusCode: Int
    val statusMessage: String?
}
