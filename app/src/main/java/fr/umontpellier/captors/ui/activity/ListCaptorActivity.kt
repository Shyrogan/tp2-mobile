package fr.umontpellier.captors.ui.activity

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.umontpellier.captors.ui.component.MainMenu
import fr.umontpellier.captors.ui.component.SensorCard
import fr.umontpellier.captors.ui.theme.Theme

class ListCaptorActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sensorManager = applicationContext.getSystemService(SensorManager::class.java)
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)

        setContent {
            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        TopAppBar(title = { Text(text = "Liste des capteurs") })
                        MainMenu()
                        sensors.forEach {
                            SensorCard(it)
                        }
                    }
                }
            }
        }
    }
}