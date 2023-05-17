package net.nicbell.resourcechecker

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

object DensityChecker {
    private val densities = mapOf(
        DisplayMetrics.DENSITY_LOW to "DENSITY_LOW",
        DisplayMetrics.DENSITY_MEDIUM to "DENSITY_MEDIUM",
        DisplayMetrics.DENSITY_HIGH to "DENSITY_HIGH",
        DisplayMetrics.DENSITY_XHIGH to "DENSITY_XHIGH",
        DisplayMetrics.DENSITY_XXHIGH to "DENSITY_XXHIGH",
        DisplayMetrics.DENSITY_XXXHIGH to "DENSITY_XXXHIGH"
    )

    /**
     * Checks if the device density is valid for this app
     */
    fun hasResourcesForTargetDensity(context: Context): Boolean {
        return try {
            val checkerDensity = context.getString(R.string.checker_density)
            getTargetDensity(context) == checkerDensity
        } catch (throwable: Throwable) {
            false
        }
    }

    /**
     * Lists all densities available in this app
     */
    fun getAvailableResourceDensities(context: Context): Collection<String> {
        return densities.filter {
            val typedValue = TypedValue()
            context.resources.getValueForDensity(
                R.string.checker_density,
                it.key,
                typedValue,
                false
            )
            typedValue.string == it.value
        }.values
    }

    /**
     * Return resource target density
     */
    fun getTargetDensity(context: Context): String? {
        val dpi = context.resources.displayMetrics.densityDpi

        return when {
            densities[dpi] != null -> densities[dpi]
            dpi == DisplayMetrics.DENSITY_TV -> densities[DisplayMetrics.DENSITY_HIGH]
            dpi < DisplayMetrics.DENSITY_LOW -> densities[DisplayMetrics.DENSITY_LOW]
            dpi < DisplayMetrics.DENSITY_MEDIUM -> densities[DisplayMetrics.DENSITY_MEDIUM]
            dpi < DisplayMetrics.DENSITY_HIGH -> densities[DisplayMetrics.DENSITY_HIGH]
            dpi < DisplayMetrics.DENSITY_XHIGH -> densities[DisplayMetrics.DENSITY_XHIGH]
            dpi < DisplayMetrics.DENSITY_XXHIGH -> densities[DisplayMetrics.DENSITY_XXHIGH]
            dpi < DisplayMetrics.DENSITY_XXXHIGH -> densities[DisplayMetrics.DENSITY_XXXHIGH]
            else -> null
        }
    }
}