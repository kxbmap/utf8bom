package com.github.kxbmap.intellij.utf8bom

import java.{util => ju}
import scala.collection.JavaConversions._
import scala.util.matching.Regex

class ProjectSettingState extends ProjectSetting {

  private[this] var enableOnSave = false

  private[this] var filePatterns: List[String] = Nil

  private[this] var regexps: List[Regex] = Nil


  def isEnableOnSave: Boolean = enableOnSave

  def setEnableOnSave(b: Boolean) {
    enableOnSave = b
  }

  def getFilePatterns: ju.List[String] = new ju.ArrayList[String](filePatterns)

  def setFilePatterns(ps: ju.List[String]) {
    filePatterns = ps.to[List]
    regexps = ps.map(_.blob).to[List]
  }

  def nameMatches(name: String): Boolean =
    regexps exists { _.matches(name) }
}