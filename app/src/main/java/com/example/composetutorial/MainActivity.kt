package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Conversation(SampleData.conversationSample)
                // Surface(modifier = Modifier.fillMaxSize()) {
                   // MessageCard(Message("Android","Jetpack Compose is a future"))
                }
            }
        }
    }

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(message: Message){
    //Membuat Padding untuk menampung pesan
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact Profile Picture",
            modifier = Modifier
                //Set ukuran gambar ke 40 dp
                .size(40.dp)
                //mengubah bentuk gambar menjadi lingkaran
                .clip(CircleShape)
                //Memberikan warna Border pada foto menggunakan material 3 (Coloring)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        //Menambahkan space horizontal antara image dan colume
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember{  mutableStateOf(false)  }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = message.author,
                // Memberikan warna pada text author menggunakan material 3 (Coloring)
                color = MaterialTheme.colorScheme.secondary,
                // Memebrikan gaya tulisan pada author ( Typhografi )
                style = MaterialTheme.typography.titleSmall
            )
            //Menambahkan space vertikal antara author dan body
            Spacer(modifier = Modifier.height(4.dp))
            //Membuat shape pada body/pesan dengan membungkusnya menggunakan Surface (Shaping)
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    //Memberikan gara tulisan pada body ( Typografi )
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn{
        items(messages){message ->
            MessageCard(message)
        }
    }
}
@Preview
@Composable
fun PreviewConversation(){
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}


//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewMessageCard() {
//    ComposeTutorialTheme {
//        Surface {
//            MessageCard(Message("Android", "Jetpack Compose"))
//        }
//    }
//}