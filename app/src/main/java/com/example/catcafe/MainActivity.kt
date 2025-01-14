package com.example.catcafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.catcafe.ui.CatCafeApp
import com.example.catcafe.ui.theme.CatCafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatCafeTheme {
                CatCafeApp()
            }
        }
    }
}