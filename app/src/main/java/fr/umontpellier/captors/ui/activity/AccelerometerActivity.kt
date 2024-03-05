package fr.umontpellier.captors.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.umontpellier.captors.ui.component.MainMenu
import fr.umontpellier.captors.ui.theme.Theme

class AccelerometerActivity : ComponentActivity(), SensorEventListener {

    private var backgroundColor by mutableStateOf(Color.White)

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
                    modifier = Modifier.fillMaxSize().background(color = backgroundColor),
                    color = backgroundColor
                ) {
                    Column {
                        TopAppBar(title = { Text(text = "Accéléromètre") })
                        MainMenu()
                    }
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val value = event?.values?.map { it * it }?.sum() ?: 0F
        backgroundColor = getColorForAccelerometerValue(value)
    }


    override fun onStop() {
        super.onStop()

        val sensorManager = getSystemService(SensorManager::class.java)
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    fun getColorForAccelerometerValue(value: Float): Color {
        val mid = 250.0f
        val high = 1000.0f

        return when {
            value in 0f..mid -> Color.Green
            value in mid..high -> Color.Black
            else -> Color.Red
        }
    }


}