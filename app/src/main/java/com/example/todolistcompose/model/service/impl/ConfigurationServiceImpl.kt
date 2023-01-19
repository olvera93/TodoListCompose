
package com.example.todolistcompose.model.service.impl

import com.example.todolistcompose.BuildConfig
import com.example.todolistcompose.R.xml as AppConfig
import com.example.todolistcompose.model.service.ConfigurationService
import com.example.todolistcompose.model.service.trace
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class ConfigurationServiceImpl @Inject constructor() : ConfigurationService {
  private val remoteConfig
    get() = Firebase.remoteConfig

  init {
    if (BuildConfig.DEBUG) {
      val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
      remoteConfig.setConfigSettingsAsync(configSettings)
    }

    remoteConfig.setDefaultsAsync(AppConfig.remote_config_defaults)
  }

  override suspend fun fetchConfiguration(): Boolean = true

  override val isShowTaskEditButtonConfig: Boolean
    get() = true

  companion object {
    private const val SHOW_TASK_EDIT_BUTTON_KEY = "show_task_edit_button"
    private const val FETCH_CONFIG_TRACE = "fetchConfig"
  }
}
