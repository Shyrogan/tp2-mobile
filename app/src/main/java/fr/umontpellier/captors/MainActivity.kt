package fr.umontpellier.captors

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.umontpellier.captors.ui.activity.*
import fr.umontpellier.captors.ui.theme.Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        TopAppBar(title = { Text(text = "TP 2") })
                        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), onClick = {
                            startActivity(Intent(applicationContext, ListCaptorActivity::class.java))
                        }) {
                            Text("Exercice 1: Liste des capteurs")
                        }
                        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), onClick = {
                            startActivity(Intent(applicationContext, UnavailableSensorsActivity::class.java))
                        }) {
                            Text("Exercice 2: Capteurs non-disponibles")
                        }
                        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), onClick = {
                            startActivity(Intent(applicationContext, AccelerometerActivity::class.java))
                        }) {
                            Text("Exercice 3: Accéléromètre")
                        }
                        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), onClick = {
                            startActivity(Intent(applicationContext, DirectionActivity::class.java))
                        }) {
                            Text("Exercice 4: Direction")
                        }
                        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), onClick = {
                            startActivity(Intent(applicationContext, FlashlightActivity::class.java))
                        }) {
                            Text("Exercice 5: Lampe torche")
                        }
                        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), onClick = {
                            startActivity(Intent(applicationContext, ProximityActivity::class.java))
                        }) {
                            Text("Exercice 6: Proximité")
                        }
                        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), onClick = {
                            startActivity(Intent(applicationContext, GeolocalisationActivity::class.java))
                        }) {
                            Text("Exercice 7: Géolocalisation")
                        }
                    }
                }
            }
        }
    }
}