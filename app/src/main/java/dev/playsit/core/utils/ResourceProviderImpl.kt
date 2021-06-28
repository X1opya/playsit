package dev.playsit.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes stringId: Int): String = context.getString(stringId)
    override fun getString(@StringRes stringId: Int, vararg formatArgs: Any): String =
        context.getString(stringId, *formatArgs)

    //    fun getRemoteString(@StringRes stringId: Int?, defaultValue: String? = null, vararg formatArgs: Any): String =
//        stringId?.let { context.getRemoteString(it, *formatArgs) } ?: defaultValue ?: String.empty()
    override fun getColor(colorId: Int) = ContextCompat.getColor(context, colorId)
    override fun getDrawable(drawableId: Int): Drawable? =
        ContextCompat.getDrawable(context, drawableId)

    override fun getDimension(dimenId: Int) = context.resources.getDimension(dimenId)
    override fun getIdentifier(name: String, defType: String, defPackage: String?): Int =
        context.resources.getIdentifier(name, defType, defPackage)
}
