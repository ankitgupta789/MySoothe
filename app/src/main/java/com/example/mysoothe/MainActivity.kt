package com.example.mysoothe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysoothe.ui.theme.MySootheTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySootheTheme {
                Scaffold { innerPadding ->
                    Home(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Home(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(8.dp))
        MySearchBar()
        Spacer(Modifier.height(16.dp))
        AlignYourBodySection()
        Spacer(Modifier.height(24.dp))
        FavoriteCollectionsSection()
        Spacer(Modifier.weight(1f))
        BottomNav()
    }
}

/* ---------------- SEARCH BAR ---------------- */

@Composable
fun MySearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        placeholder = { Text("Search") },
        modifier = Modifier.fillMaxWidth()
    )
}

/* ---------------- ALIGN YOUR BODY ---------------- */

@Composable
fun AlignYourBodySection() {
    Column {
        Text(
            text = "Align your body",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(alignBodyData) { item ->
                AlignYourBodyItem(item)
            }
        }
    }
}

@Composable
fun AlignYourBodyItem(item: AlignBody) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.height(8.dp))
        Text(item.title)
    }
}

/* ---------------- FAVORITE COLLECTIONS ---------------- */

@Composable
fun FavoriteCollectionsSection() {
    Column {
        Text(
            text = "Favorite collections",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(200.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoriteData) { item ->
                FavoriteCard(item)
            }
        }
    }
}

@Composable
fun FavoriteCard(item: Favorite) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(item.image),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(item.title)
        }
    }
}

/* ---------------- BOTTOM NAV ---------------- */

@Composable
fun BottomNav() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Profile") }
        )
    }
}

/* ---------------- DATA ---------------- */

data class AlignBody(
    val title: String,
    val image: Int
)

data class Favorite(
    val title: String,
    val image: Int
)

val alignBodyData = listOf(
    AlignBody("Inversions", R.drawable.image1),
    AlignBody("Quick yoga", R.drawable.image2),
    AlignBody("Stretching", R.drawable.image3),
    AlignBody("Tabata", R.drawable.image4)
)

val favoriteData = listOf(
    Favorite("Short mantras", R.drawable.image11),
    Favorite("Nature meditations", R.drawable.image21),
    Favorite("Stress relief", R.drawable.image31),
    Favorite("Self massage", R.drawable.image41)
)
