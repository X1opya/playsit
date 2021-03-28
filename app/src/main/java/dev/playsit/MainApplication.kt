package dev.playsit

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dev.playsit.core.network.ApiService
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
}