package fr.umontpellier.captors.ui.activity

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import fr.umontpellier.captors.ui.component.MainMenu
import fr.umontpellier.captors.ui.theme.Theme

class UnavailableSensorsActivity : ComponentActivity() {

    val sensorTypeMap = mapOf(
        Sensor.TYPE_ACCELEROMETER to "android.sensor.accelerometer",
        Sensor.TYPE_MAGNETIC_FIELD to "android.sensor.magnetic_field",
        Sensor.TYPE_ORIENTATION to "android.sensor.orientation", // Deprecated
        Sensor.TYPE_GYROSCOPE to "android.sensor.gyroscope",
        Sensor.TYPE_LIGHT to "android.sensor.light",
        Sensor.TYPE_PRESSURE to "android.sensor.pressure",
        Sensor.TYPE_TEMPERATURE to "android.sensor.temperature", // Deprecated
        Sensor.TYPE_PROXIMITY to "android.sensor.proximity",
        Sensor.TYPE_GRAVITY to "android.sensor.gravity",
        Sensor.TYPE_LINEAR_ACCELERATION to "android.sensor.linear_acceleration",
        Sensor.TYPE_ROTATION_VECTOR to "android.sensor.rotation_vector",
        Sensor.TYPE_RELATIVE_HUMIDITY to "android.sensor.relative_humidity",
        Sensor.TYPE_AMBIENT_TEMPERATURE to "android.sensor.ambient_temperature",
        Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED to "android.sensor.magnetic_field_uncalibrated",
        Sensor.TYPE_GAME_ROTATION_VECTOR to "android.sensor.game_rotation_vector",
        Sensor.TYPE_GYROSCOPE_UNCALIBRATED to "android.sensor.gyroscope_uncalibrated",
        Sensor.TYPE_SIGNIFICANT_MOTION to "android.sensor.significant_motion",
        Sensor.TYPE_STEP_DETECTOR to "android.sensor.step_detector",
        Sensor.TYPE_STEP_COUNTER to "android.sensor.step_counter",
        Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR to "android.sensor.geomagnetic_rotation_vector",
        Sensor.TYPE_HEART_RATE to "android.sensor.heart_rate",
        Sensor.TYPE_POSE_6DOF to "android.sensor.pose_6dof",
        Sensor.TYPE_STATIONARY_DETECT to "android.sensor.stationary_detect",
        Sensor.TYPE_MOTION_DETECT to "android.sensor.motion_detect",
        Sensor.TYPE_HEART_BEAT to "android.sensor.heart_beat",
        Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT to "android.sensor.low_latency_offbody_detect",
        Sensor.TYPE_ACCELEROMETER_UNCALIBRATED to "android.sensor.accelerometer_uncalibrated",
        Sensor.TYPE_HINGE_ANGLE to "android.sensor.hinge_angle",
        Sensor.TYPE_HEAD_TRACKER to "android.sensor.head_tracker",
        Sensor.TYPE_ACCELEROMETER_LIMITED_AXES to "android.sensor.accelerometer_limited_axes",
        Sensor.TYPE_GYROSCOPE_LIMITED_AXES to "android.sensor.gyroscope_limited_axes",
        Sensor.TYPE_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED to "android.sensor.accelerometer_limited_axes_uncalibrated",
        Sensor.TYPE_GYROSCOPE_LIMITED_AXES_UNCALIBRATED to "android.sensor.gyroscope_limited_axes_uncalibrated",
        Sensor.TYPE_HEADING to "android.sensor.heading"
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sensorManager = applicationContext.getSystemService(SensorManager::class.java)
        val sensors = sensorTypeMap
            .filter { (key, _) -> sensorManager.getSensorList(key).isEmpty() }
            .map { it.value }


        setContent {
            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        TopAppBar(title = { Text(text = "Capteurs non-disponible") })
                        MainMenu()
                        sensors.forEach {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .background(MaterialTheme.colorScheme.errorContainer)
                            ) {
                                Row {
                                    Text(
                                        "Type: ",
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = TextUnit(-0.01f, TextUnitType.Em)
                                    )
                                    Text(it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}