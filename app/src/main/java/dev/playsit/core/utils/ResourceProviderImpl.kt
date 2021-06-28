package dev.playsit.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceProvider(private val context: Context) {
    init {
        parentContext = context
    }

    fun getString(@StringRes stringId: Int): String = context.getString(stringId)
    fun getString(@StringRes stringId: Int, vararg formatArgs: Any): String = context.getString(stringId, *formatArgs)
//    fun getRemoteString(@StringRes stringId: Int?, defaultValue: String? = null, vararg formatArgs: Any): String =
//        stringId?.let { context.getRemoteString(it, *formatArgs) } ?: defaultValue ?: String.empty()
    fun getColor(colorId: Int) = ContextCompat.getColor(context, colorId)
    fun getDrawable(drawableId: Int) = ContextCompat.getDrawable(context, drawableId)
    fun getDimension(dimenId: Int) = context.resources.getDimension(dimenId)
    fun getIdentifier(name: String, defType: String, defPackage: String?): Int =
        context.resources.getIdentifier(name, defType, defPackage)

    companion object {
        private var instance: ResourceProvider? = null
        private lateinit var parentContext: Context

        @Synchronized
        fun instance(): ResourceProvider {
            if (instance == null) instance = ResourceProvider(parentContext)
            return instance as ResourceProvider
        }
    }
}
