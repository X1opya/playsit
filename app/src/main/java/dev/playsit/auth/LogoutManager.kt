package dev.playsit.auth

class LogoutManager(vararg cleanable: LogoutListener) {
    private val list = cleanable
    fun logout() {
        list.forEach { it.logout() }
    }
}
