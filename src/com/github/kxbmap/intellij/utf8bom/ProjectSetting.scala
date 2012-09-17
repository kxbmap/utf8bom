package com.github.kxbmap.intellij.utf8bom

import java.{util => ju}

trait ProjectSetting {
  def isEnableOnSave: Boolean

  def setEnableOnSave(b: Boolean)

  def getFilePatterns: ju.List[String]

  def setFilePatterns(ps: ju.List[String])

  final def isModified(from: ProjectSetting): Boolean =
    isEnableOnSave != from.isEnableOnSave || getFilePatterns != from.getFilePatterns

  final def saveTo(setting: ProjectSetting) {
    setting.loadFrom(this)
  }

  final def loadFrom(setting: ProjectSetting) {
    setEnableOnSave(setting.isEnableOnSave)
    setFilePatterns(setting.getFilePatterns)
  }
}