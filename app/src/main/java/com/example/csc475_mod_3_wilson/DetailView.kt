package com.example.csc475_mod_3_wilson

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.csc475_mod_3_wilson.data.ToDo

@Composable
fun DetailView(toDoId: Int, viewModel: ToDoListViewModel, navController: NavController ) {
    var confirmDelete by remember { mutableStateOf(false) }
    val toDoItem = viewModel.getToDoItem(toDoId).collectAsState(initial = ToDo(0,"",""))
    val context = LocalContext.current
    Scaffold(topBar = {HeaderView(title = if (toDoId != 0) "Update Task" else
                "Add Task") {navController.navigateUp()}
    },  floatingActionButton = {

        FloatingActionButton (
            onClick = {
                if (toDoId != 0)
                {confirmDelete = true} else {
                    viewModel.toDoTaskState = ""
                    viewModel.toDoNotesState = ""
                }

            },
            modifier = Modifier.padding(all = 25.dp),
            contentColor = Color.Yellow,
            backgroundColor = Color.Black) {
            Icon(
                imageVector = Icons.Default.Delete, contentDescription = null

           )
        }
        if(confirmDelete){
            AlertDialog(onDismissRequest = {confirmDelete = false},
                confirmButton = {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Are you sure you wish to delete?")
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Button(onClick = {
                                viewModel.deleteToDoItem(toDoItem.value)
                                confirmDelete = false
                                navController.navigateUp()}) {
                                Text(text = "Yes")
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(onClick = {
                                confirmDelete = false
                                navController.navigateUp()
                               }) {
                                Text(text = "No")
                            }
                        }


                    }
                })

    }}
        ) {
        Column(modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(12.dp))
            ToDoTextField(label = "To Do", value = viewModel.toDoTaskState,whenChanged = {viewModel.onTaskChanged(it)})
            Spacer(modifier = Modifier.height(12.dp))
            ToDoTextField(label = "Notes", value = viewModel.toDoNotesState , whenChanged = {viewModel.onNotesChanged(it)})
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                if(viewModel.toDoTaskState.isNotEmpty() && viewModel.toDoNotesState.isNotEmpty()) {
                    if(toDoId == 0) {
                        viewModel.addToDoItem(
                            ToDo(
                                toDoTask = viewModel.toDoTaskState.trim(),
                                toDoNotes = viewModel.toDoNotesState.trim()
                            )
                        )
                        viewModel.toDoTaskState = ""
                        viewModel.toDoNotesState = ""
                        navController.navigateUp()
                    } else {
                        viewModel.updateToDoItem(
                            ToDo(
                                toDoId = toDoId,
                                toDoTask = viewModel.toDoTaskState.trim(),
                                toDoNotes = viewModel.toDoNotesState.trim()))
                        viewModel.toDoTaskState = ""
                        viewModel.toDoNotesState = ""
                        navController.navigateUp()
                    }


                } else {
                    Toast.makeText(context,"Please enter a task and notes.", Toast.LENGTH_LONG).show()
                }

            }) {
                Text(text = if (toDoId == 0) "Add To-Do" else "Update To-Do")
            }
        }}



    }

@Composable
fun ToDoTextField(
    label: String,
    value: String,
    whenChanged: (String) -> Unit
) {
    OutlinedTextField(value = value, onValueChange = whenChanged,
    label = { Text(text = label, color = Color.Blue)},
    modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White),
    colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.Black
    )    )
}