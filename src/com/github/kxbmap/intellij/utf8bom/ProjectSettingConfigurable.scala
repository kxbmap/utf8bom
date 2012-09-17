package com.github.kxbmap.intellij.utf8bom

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import javax.swing.JComponent
import org.jetbrains.annotations.{Nls, Nullable}

class ProjectSettingConfigurable(project: Project) extends Configurable {
  private[this] def setting = project.service[ProjectSettingComponent].map(_.getState)

  private[this] def settingOrDefault = setting getOrElse new ProjectSettingState

  private[this] var panel = new ProjectSettingPanel(settingOrDefault)

  @Nls def getDisplayName: String = "UTF8 BOM"

  @Nullable def getHelpTopic: String = null

  @Nullable def createComponent: JComponent = panel.getMyPanel

  def isModified: Boolean = panel.isModified(settingOrDefault)

  def apply() {
    setting foreach panel.saveTo
  }

  def reset() {
    panel.loadFrom(settingOrDefault)
  }

  def disposeUIResources() {
    panel = null
  }
}