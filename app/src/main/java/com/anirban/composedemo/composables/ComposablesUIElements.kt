package com.anirban.composedemo.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anirban.composedemo.R
import com.anirban.composedemo.models.DataProvider
import com.anirban.composedemo.models.Message

// This Shows the Preview of the APP composable function Conversation
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Conversation(DataProvider.conversationSample)
}

// This function is the Lazy column which calls a function which populates the UI of the Screen
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(items = messages) { message ->
            NewUI(message = message)
        }
    }
}

// This is the per item UI of the LazyColumn
@Composable
fun NewUI(message: Message) {

    // This variable is to check if the Text is currently Expanded or not
    var isExpanded by remember { mutableStateOf(false) }

    // This Variable gives the color to the current Text based on if its Extended or not
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )

    // This Arranges the UI in a horizontal way
    Row(
        modifier = Modifier.padding(all = 8.dp)
    ) {

        // This makes a image (over here it is more like a profile pic)
        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.picture),
            contentDescription = "My Image"
        )

        // Spacer is used to give spaces between various UI elements
        Spacer(modifier = Modifier.width(8.dp))

        // This arranges the UI in a vertical way
        Column(
            modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }
        ) {
            Text(
                text = message.author,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondaryVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp ,
                color = surfaceColor ,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1 ,
                    text = message.body,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}