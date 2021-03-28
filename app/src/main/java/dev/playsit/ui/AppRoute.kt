package dev.playsit.ui

sealed class AppRoute {
    object SplashRoute : AppRoute()
    object CollectionsRout : AppRoute()
    object BoardsRout : AppRoute()
}