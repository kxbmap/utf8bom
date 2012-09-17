package com.github.kxbmap.intellij.utf8bom

import com.intellij.openapi.components.{StorageScheme, Storage, State, PersistentStateComponent}
import org.jetbrains.annotations.Nullable

@State(
  name = "UTF8BOMProjectSetting",
  storages = Array(
    new Storage(file = "$WORKSPACE_FILE$"),
    new Storage(file = "$PROJECT_CONFIG_DIR$/utf8bom.xml", scheme = StorageScheme.DIRECTORY_BASED)
  )
)
class ProjectSettingComponent extends PersistentStateComponent[ProjectSettingState] {

  private[this] val state = new ProjectSettingState()

  @Nullable
  def getState: ProjectSettingState = state

  def loadState(state: ProjectSettingState) {
    this.state.loadFrom(state)
  }
}