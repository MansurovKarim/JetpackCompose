package com.example.jetpackcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R


@Composable
fun MainProfile(padding: PaddingValues) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        MainProfileImage(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.Top)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically)
        ) {
            MainProfileStats()
            Spacer(modifier = Modifier.height(8.dp))
            MainProfileInfo(
                name = "Имя",
                surname = "Фамилия",
                description = "Описание профиля"
            )
        }
    }
}

@Composable
fun MainProfileImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "Profile Image",
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(CircleShape)

    )
}

@Composable
fun MainProfileStats() {
    Column {
        Row(
            modifier = Modifier
                .padding(vertical = 2.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "0", fontSize = 18.sp, fontWeight = FontWeight.W800)
            Text(text = "0", fontSize = 18.sp, fontWeight = FontWeight.W800)
            Text(text = "0", fontSize = 18.sp, fontWeight = FontWeight.W800)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Post", fontSize = 14.sp)
            Text(text = "Followers", fontSize = 14.sp)
            Text(text = "Followings", fontSize = 14.sp)
        }
    }
}

@Composable
fun MainProfileInfo(name: String, surname: String, description: String) {
    Name(name = name, surname = surname)
    Description(description = description)
}

@Composable
fun Name(name: String, surname: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.W800,
            maxLines = 1
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = surname,
            fontSize = 18.sp,
            fontWeight = FontWeight.W800,
            maxLines = 1
        )
    }
}

@Composable
fun Description(description: String) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EditableTextFieldDialog(onDismiss = { showDialog = false })
    }

    Text(
        modifier = Modifier.clickable {
            showDialog = true
        },
        text = description,
        fontSize = 14.sp,
        fontWeight = FontWeight.W200
    )
}



@Composable
@Preview
fun MainProfilePreview() {
    MainProfile(padding = PaddingValues())
}
@Composable
fun EditableTextFieldDialog(onDismiss: () -> Unit) {
    var text by remember { mutableStateOf("Нажми, чтобы изменить") }
    var showDialog by remember { mutableStateOf(false) }
    var tempText by remember { mutableStateOf(text) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            modifier = Modifier
                .clickable {
                    tempText = text
                    showDialog = true
                }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Редактировать текст")
                },
                text = {
                    TextField(
                        value = tempText,
                        onValueChange = { tempText = it },
                        singleLine = true
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        text = tempText
                        showDialog = false
                    }) {
                        Text("Сохранить")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = {
                        showDialog = false
                    }) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}


