package fr.umontpellier.captors.ui.component

import android.hardware.Sensor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun SensorCard(sensor: Sensor, background: Color = MaterialTheme.colorScheme.primaryContainer) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(background)
    ) {
        Row {
            Text("Nom: ", fontWeight = FontWeight.Bold, letterSpacing = TextUnit(-0.01f, TextUnitType.Em))
            Text(sensor.name)
        }
        Row {
            Text("Vendor: ", fontWeight = FontWeight.Bold, letterSpacing = TextUnit(-0.01f, TextUnitType.Em))
            Text(sensor.vendor)
        }
        Row {
            Text("(${sensor.id})", fontStyle = FontStyle.Italic, letterSpacing = TextUnit(-0.01f, TextUnitType.Em))
        }
    }
}
