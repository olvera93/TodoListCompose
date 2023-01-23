

package com.example.todolistcompose.screens.tasks

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistcompose.R.drawable as AppIcon
import com.example.todolistcompose.common.composable.DropdownContextMenu
import com.example.todolistcompose.common.ext.contextMenu
import com.example.todolistcompose.common.ext.hasDueDate
import com.example.todolistcompose.common.ext.hasDueTime
import com.example.todolistcompose.model.Task
import com.example.todolistcompose.ui.theme.DarkOrange
import java.lang.StringBuilder

@Composable
@ExperimentalMaterialApi
fun TaskItem(
  task: Task,
  options: List<String>,
  onCheckChange: () -> Unit,
  onActionClick: (String) -> Unit
) {
  Card(
    backgroundColor = MaterialTheme.colors.background,
    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Checkbox(
        checked = task.completed,
        onCheckedChange = { onCheckChange() },
        modifier = Modifier.padding(8.dp, 0.dp)
      )

      Column(modifier = Modifier.weight(1f)) {
        Text(text = task.title, style = MaterialTheme.typography.subtitle2)
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
          Text(text = getDueDateAndTime(task), fontSize = 12.sp)
        }
      }

      if (task.flag) {
        Icon(
          painter = painterResource(AppIcon.ic_flag),
          tint = DarkOrange,
          contentDescription = "Flag"
        )
      }

      DropdownContextMenu(options, Modifier.contextMenu(), onActionClick)
    }
  }
}

private fun getDueDateAndTime(task: Task): String {
  val stringBuilder = StringBuilder("")

  if (task.hasDueDate()) {
    stringBuilder.append(task.dueDate)
    stringBuilder.append(" ")
  }

  if (task.hasDueTime()) {
    stringBuilder.append("at ")
    stringBuilder.append(task.dueTime)
  }

  return stringBuilder.toString()
}
