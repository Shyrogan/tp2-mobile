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
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import fr.umontpellier.captors.ui.component.MainMenu
import fr.umontpellier.captors.ui.theme.Theme

class DirectionActivity : ComponentActivity(), SensorEventListener {

    var direction by mutableStateOf("Neutre")

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sensorManager = getSystemService(SensorManager::class.java)
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
            SensorManager.SENSOR_DELAY_UI
        )

        setContent {
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
                ) {
                    Column {
                        TopAppBar(title = { Text(text = "Direction") })
                        MainMenu()

                        Text(
                            direction,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(8f, TextUnitType.Em),
                            modifier = Modifier.fillMaxSize()
                        )
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
        val x = event?.values?.get(0) ?: 0.0f
        val y = event?.values?.get(1) ?: 0.0f

        val sensibility = 3.0

        val horizontal = if (x < -sensibility) {
            "Droite"
        } else if (x > sensibility) {
            "Gauche"
        } else {
            ""
        }

        val vertical = if (y < -sensibility) {
            "Bas"
        } else if (y > sensibility) {
            "Haut"
        } else {
            ""
        }

        direction = "$horizontal $vertical"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}