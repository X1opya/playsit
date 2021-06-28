package dev.playsit.dto

interface VideoDto {
    val duration: String
    val videoIdentifier: String?
    val cover: String?
    val formattedAddedDate: String
    val name: String
}
