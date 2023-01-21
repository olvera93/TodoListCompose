
package com.example.todolistcompose.screens.tasks

import androidx.compose.runtime.mutableStateOf
import com.example.todolistcompose.model.Task
import com.example.todolistcompose.model.service.ConfigurationService
import com.example.todolistcompose.model.service.LogService
import com.example.todolistcompose.model.service.StorageService
import com.example.todolistcompose.navigation.EDIT_TASK_SCREEN
import com.example.todolistcompose.navigation.SETTINGS_SCREEN
import com.example.todolistcompose.navigation.TASK_ID
import com.example.todolistcompose.screens.TodoListViewModel
import com.example.todolistcompose.screens.tasks.TaskActionOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
  logService: LogService,
  private val storageService: StorageService,
  private val configurationService: ConfigurationService
) : TodoListViewModel(logService) {
  val options = mutableStateOf<List<String>>(listOf())

  val tasks = emptyFlow<List<Task>>()

  fun loadTaskOptions() {

  }

  fun onTaskCheckChange(task: Task) {
    launchCatching { storageService.update(task.copy(completed = !task.completed)) }
  }

  fun onAddClick(openScreen: (String) -> Unit) = openScreen(EDIT_TASK_SCREEN)

  fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

  fun onTaskActionClick(openScreen: (String) -> Unit, task: Task, action: String) {
    when (TaskActionOption.getByTitle(action)) {
      TaskActionOption.EditTask -> openScreen("$EDIT_TASK_SCREEN?$TASK_ID={${task.id}}")
      TaskActionOption.ToggleFlag -> onFlagTaskClick(task)
      TaskActionOption.DeleteTask -> onDeleteTaskClick(task)
    }
  }

  private fun onFlagTaskClick(task: Task) {
    launchCatching { storageService.update(task.copy(flag = !task.flag)) }
  }

  private fun onDeleteTaskClick(task: Task) {
    launchCatching { storageService.delete(task.id) }
  }
}
