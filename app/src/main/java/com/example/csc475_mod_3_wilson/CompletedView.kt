package com.example.csc475_mod_3_wilson

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.csc475_mod_3_wilson.data.ToDo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState


@Composable
fun CompletedView(
    navController: NavController,
    viewModel: ToDoListViewModel
) {
    androidx.compose.material3.Scaffold(
        topBar = {HeaderView(title = "Completed List", { navController.navigateUp()
        })},
        floatingActionButton = {
            Row(){
                FloatingActionButton (
                    onClick = {
                        navController.navigate(ToDoScreen.DetailScreen.route + "/0")
                    },
                    modifier = Modifier.padding(all = 25.dp),
                    contentColor = Color.Yellow,
                    backgroundColor = Color.Black) {
                    Icon(
                        imageVector = Icons.Default.AddCircle, contentDescription = null

                    )
                }
            }
        }
    )
    {
        Spacer(modifier = Modifier.height(8.dp))

        val toDoList = viewModel.getCompletedToDoList.collectAsState(initial = listOf())

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            items(toDoList.value, key={toDo-> toDo.toDoId}) {
                    toDo ->
                CompletedItem(toDo = toDo, {
                    val toDoId = toDo.toDoId
                    navController.navigate(ToDoScreen.DetailScreen.route + "/$toDoId")
                }, viewModel)
            }
        }
    }
}

@Composable
fun CompletedItem(toDo: ToDo, onClick: () -> Unit, viewModel: ToDoListViewModel) {


    val taskText = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 18.sp,
        color = Color.Black
    )
    val notesText = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 12.sp,
        color = Color.Black
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, start = 6.dp, end = 6.dp)
            .clickable { onClick() },
        elevation = 6.dp,
        backgroundColor = Color.White
    ) {

        Row() {
            Column(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.75f)) {
                Text(text = toDo.toDoTask, style = taskText)

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = toDo.toDoNotes, style = notesText, minLines = 2)


            }

            }
        }
    }



