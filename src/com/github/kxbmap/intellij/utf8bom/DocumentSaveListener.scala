package com.github.kxbmap.intellij.utf8bom

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManagerAdapter
import com.intellij.openapi.vfs.{VirtualFile, CharsetToolkit}

class DocumentSaveListener extends FileDocumentManagerAdapter {
  override def beforeDocumentSaving(document: Document) {
    for {
      file <- document.file
      if isTarget(file) && isSettingEnabled(file)
    } file.setBOM(CharsetToolkit.UTF8_BOM)
  }

  private def isTarget(file: VirtualFile): Boolean =
    file.getBOM == null && CharsetToolkit.canHaveBom(file.getCharset, CharsetToolkit.UTF8_BOM)

  private def isSettingEnabled(file: VirtualFile): Boolean =
    file.guessProject
      .flatMap(_.service[ProjectSettingComponent])
      .map(_.getState)
      .filter(_.isEnableOnSave)
      .exists(_.nameMatches(file.getName))
}