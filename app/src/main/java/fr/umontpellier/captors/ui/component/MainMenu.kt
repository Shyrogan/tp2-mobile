package fr.umontpellier.captors.ui.component

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.umontpellier.captors.MainActivity

@Composable
fun MainMenu() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        },
        modifier = Modifier
            .padding(start = 16.dp, bottom = 16.dp)
            .size(48.dp), // Adjust size as needed
    ) {
        Icon(Icons.Filled.Home, "Accueil")
    }
}