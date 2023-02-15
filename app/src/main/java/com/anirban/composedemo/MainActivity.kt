package com.anirban.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.anirban.composedemo.composables.Conversation
import com.anirban.composedemo.models.DataProvider
import com.anirban.composedemo.ui.theme.JetpackComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This setContent sets the UI for the Activity
        setContent {

            // This is the defined theme for this app and it is defined in ui.theme package
            JetpackComposeDemoTheme {

                // Making a surface Container for the App which will contain the whole UI
                Surface(modifier = Modifier.fillMaxSize()) {

                    // Calling the Composable function which contains the parts of the UI
                    Conversation(messages = DataProvider.conversationSample)
                }
            }
        }
    }
}