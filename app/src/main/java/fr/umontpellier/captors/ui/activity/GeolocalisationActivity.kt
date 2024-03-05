package fr.umontpellier.captors.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import fr.umontpellier.captors.ui.component.MainMenu
import fr.umontpellier.captors.ui.theme.Theme

class GeolocalisationActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                10000
            )
        }

        val locationManager = getSystemService(LocationManager::class.java)
        val providers = locationManager.getProviders(true)
        val positions = providers.mapNotNull {
            locationManager.getLastKnownLocation(it)
        }

        setContent {
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
                ) {
                    Column {
                        TopAppBar(title = { Text(text = "Position(s) GPS") })
                        MainMenu()

                        positions.forEach {
                            Text("Latitude: ${it.latitude} longitude: ${it.longitude} altitude: ${it.altitude} accuracy: ${it.accuracy}")
                        }
                    }
                }
            }
        }
    }


}