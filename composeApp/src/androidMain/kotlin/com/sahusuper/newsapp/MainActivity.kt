package com.sahusuper.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = 0x00000000,
                darkScrim = 0x00000000

            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = 0x00000000,
                darkScrim = 0x00000000
            )

        )
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

