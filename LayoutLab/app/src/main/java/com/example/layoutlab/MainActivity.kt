package com.example.layoutlab

import android.R.id.input
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.layoutlab.ui.theme.LayoutLabTheme

data class Task(
    val id:Int,
    val title:String,
    val done:Boolean=false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            LayoutLabTheme {
                TaskScreen()
            }
        }
    }
}

@Preview
@Composable
fun TaskScreen(){
    val tasks=remember {
        mutableStateListOf(
            Task(1,"学习状态管理",false)
        )
    }
    var input by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "课程学习任务",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color=Color(0xFFB71C1C)
        )
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                placeholder = {Text("请输入任务")},
                modifier = Modifier.weight(1f),
                value = input,
                onValueChange = {input=it}
            )
            Button(
                onClick = {
                    if(input.isNotBlank()){
                        tasks.add(Task(tasks.size+1,input))
                        input=""
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB71C1C)
                )
            ) {
                Text("添加")
            }
        }
        Spacer(Modifier.height(10.dp))
        val donecnt=tasks.count{it.done}
        Text("已完成: $donecnt / ${tasks.size}")
        Spacer(Modifier.height(10.dp))
        LazyColumn() {
            items(tasks){
                task->
                TaskItem(
                    task=task,
                    onCheckedChange = {
                        checked->
                        val index=tasks.indexOf(task)
                        tasks[index]=task.copy(done=checked)
                    },
                    onDelete = {
                        tasks.remove(task)
                    }
                )
                Spacer(Modifier.height(5.dp))
            }

        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onCheckedChange:(Boolean)-> Unit,
    onDelete:()-> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.done,
            onCheckedChange=onCheckedChange
        )
        Spacer(Modifier.width(5.dp))
        Text(
            text=task.title,
            modifier= Modifier.weight(1f),
            textDecoration = if(task.done) TextDecoration.LineThrough else TextDecoration.None,
            color = if(task.done) Color.Gray else Color.Black,

            )
        Text(
            text = "删除",
            color = Color.Red,
            modifier=Modifier.clickable{onDelete()}
        )
    }
}
