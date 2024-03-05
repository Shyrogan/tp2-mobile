package fr.umontpellier.captors.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
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

class ProximityActivity : ComponentActivity(), SensorEventListener {

    private var message by mutableStateOf("")

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sensorManager = getSystemService(SensorManager::class.java)
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
            SensorManager.SENSOR_DELAY_GAME
        )

        setContent {
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
                ) {
                    Column {
                        TopAppBar(title = { Text(text = "Proximité") })
                        MainMenu()

                        Text(message)
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
        val acceleration = event?.values?.get(0) ?: 10.0f
        Log.i("a", "$acceleration")

        message = when (acceleration) {
            5.0f -> "Rapprochez-vous !"
            0.0f -> "vous êtes vraiment très très près 🥵"
            else -> ""
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}