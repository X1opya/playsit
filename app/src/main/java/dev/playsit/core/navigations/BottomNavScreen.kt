package dev.playsit.core.navigations

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.playsit.R

sealed class BottomNavScreen(val route: String, @StringRes val name: Int, @DrawableRes val icon: Int) {
    object Discover : BottomNavScreen("Discover", R.string.discoverTitle, R.drawable.ic_nav_discover)
    object News : BottomNavScreen("News", R.string.navNews, R.drawable.ic_nav_news)
    object Search : BottomNavScreen("Search", R.string.navSearch, R.drawable.ic_nav_search)
    object Notifications : BottomNavScreen("Notifications", R.string.navNotifications, R.drawable.ic_nav_nitifications)
    object Profile : BottomNavScreen("Profile", R.string.navProfile, R.drawable.ic_nav_profile)
    companion object {
        val screens = listOf(
            Discover,
            News,
            Search,
            Notifications,
            Profile
        )
    }
}
