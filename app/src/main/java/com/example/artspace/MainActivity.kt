package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Sanat Eseri Veri Modeli
data class Artwork(
    val imageResId: Int,
    val titleResId: Int,
    val artistResId: Int
)

// Sanat Eseri Listesi
val artworks = listOf(
    Artwork(R.drawable.painting1, R.string.artwork_title_1, R.string.artist_name_1),
    Artwork(R.drawable.painting2, R.string.artwork_title_2, R.string.artist_name_2),
    Artwork(R.drawable.painting3, R.string.artwork_title_3, R.string.artist_name_3)
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier){
    var currentIndex by remember { mutableStateOf(0) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment=Alignment.CenterHorizontally
    ){
        //Bölüm A: Sanat eseri görseli
        Image(
            painter = painterResource(artworks[currentIndex].imageResId),
            contentDescription = stringResource(artworks[currentIndex].titleResId) ,
            modifier = modifier
                .weight(1f) // Ekranın büyük kısmını kaplaması
                .fillMaxWidth() // Genişlik tam ekran
                .clip(RoundedCornerShape(16.dp))
        )
        // // Bölüm B: Sanat Eseri Bilgileri
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer, // Material Theme arka plan rengi
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =stringResource(artworks[currentIndex].titleResId), // Sanat eseri adı
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(artworks[currentIndex].artistResId), // Sanatçı adı ve yıl bilgisi
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        // Bölüm C: Kontrol Düğmeleri
        Row(modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0
            ) {
                Text(stringResource(R.string.previous_button))
            }
            Button(
                onClick = { if (currentIndex < artworks.size - 1) currentIndex++ },
                enabled = currentIndex < artworks.size - 1
            ) {
                Text(stringResource(R.string.next_button))
            }
        }
    }
}