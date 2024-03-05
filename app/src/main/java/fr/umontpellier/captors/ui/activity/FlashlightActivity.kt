package fr.umontpellier.captors.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fr.umontpellier.captors.ui.component.MainMenu
import fr.umontpellier.captors.ui.theme.Theme

class FlashlightActivity : ComponentActivity(), SensorEventListener {

    var flashlight by mutableStateOf(false)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sensorManager = getSystemService(SensorManager::class.java)
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_GAME
        )

        setContent {
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
                ) {
                    Column {
                        TopAppBar(title = { Text(text = "Flash en secouant") })
                        MainMenu()
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        val sensorManager = getSystemService(SensorManager::class.java)
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val acceleration = event?.values?.map { it * it }?.sum() ?: 0f
        if (acceleration > 1000) {
            toggleFlashLight()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun toggleFlashLight() {
        val cameraManager = getSystemService(CameraManager::class.java)
        val cameraId = cameraManager.cameraIdList.firstOrNull { id ->
            cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
        }
        cameraId?.let {
            flashlight = !flashlight
            cameraManager.setTorchMode(it, flashlight)
        }
    }


}