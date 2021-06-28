package dev.playsit.core.utils

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
    fun getString(@StringRes stringId: Int, vararg formatArgs: Any): String
    fun getColor(colorId: Int): Int
    fun getDrawable(drawableId: Int): Drawable?
    fun getDimension(dimenId: Int): Float
    fun getIdentifier(name: String, defType: String, defPackage: String?): Int
}
