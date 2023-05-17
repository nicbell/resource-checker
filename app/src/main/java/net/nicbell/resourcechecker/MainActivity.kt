package net.nicbell.resourcechecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resourceDensities =
            DensityChecker.getAvailableResourceDensities(this).joinToString("\n")

        val targetDensity = "Device target density: ${DensityChecker.getTargetDensity(this)}"
        val availableDensities = "Available app density resources: \n$resourceDensities"

        findViewById<MaterialTextView>(R.id.txt_device).text =
            getString(R.string.device_density, resources.displayMetrics.densityDpi)


        findViewById<MaterialTextView>(R.id.txt_target).text = targetDensity

        findViewById<MaterialTextView>(R.id.txt_density_valid).text =
            "Are resources available for device density: ${
                DensityChecker.hasResourcesForTargetDensity(this)
            }"

        findViewById<MaterialTextView>(R.id.txt_available).text = availableDensities
    }
}