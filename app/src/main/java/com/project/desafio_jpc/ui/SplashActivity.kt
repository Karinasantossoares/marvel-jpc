package com.project.desafio_jpc.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.project.desafio_jpc.R
import com.project.desafio_jpc.designsystem.theme.AppTheme
import com.project.desafio_jpc.list.presentation.CharacterActivity
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
internal class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                InitialScreen {
                    startActivity(Intent(this, CharacterActivity::class.java))
                    finish()
                }
            }
        }
    }
}

@Composable
fun InitialScreen(onStartTransaction: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onStartTransaction()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.splash_text),
            color = Color.White,
            fontSize = 32.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InitialScreen {}
}