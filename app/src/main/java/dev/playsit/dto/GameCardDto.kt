package dev.playsit.dto

interface GameCardDto {
    val id: Int
    val name: String?
    val cover: String?
    val genres: List<String>?
}
