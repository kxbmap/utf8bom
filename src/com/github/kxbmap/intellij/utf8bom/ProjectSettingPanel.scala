package com.github.kxbmap.intellij.utf8bom

import java.awt.event.ItemEvent
import java.{util => ju}
import scala.collection.JavaConversions._

class ProjectSettingPanel private extends ProjectSettingPanelForm with ProjectSetting {

  def this(current: ProjectSetting) {
    this()
    loadFrom(current)

    getEnableOnSave.addItemListener { e: ItemEvent =>
      getContents.setEnabledAll(e.getStateChange == ItemEvent.SELECTED)
    }
  }

  def isEnableOnSave: Boolean = getEnableOnSave.isSelected

  def setEnableOnSave(b: Boolean) {
    getEnableOnSave.setSelected(b)
    getContents.setEnabledAll(b)
  }

  def getFilePatterns: ju.List[String] =
    getFilePatternsText.getText.split(";+").filterNot(_.trim.isEmpty).to[List]

  def setFilePatterns(ps: ju.List[String]) {
    getFilePatternsText.setText(ps.mkString(";"))
  }
}