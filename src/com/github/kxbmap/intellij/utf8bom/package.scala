package com.github.kxbmap.intellij

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.{ProjectUtil, Project}
import com.intellij.openapi.vfs.VirtualFile
import java.awt.Container
import java.awt.event.{ItemListener, ItemEvent}
import scala.reflect.{classTag, ClassTag}
import scala.util.matching.Regex

package object utf8bom {

  private[utf8bom] implicit final class StringOps(val __string: String) extends AnyVal {
    def blob: Regex = __string.flatMap {
      case '?' => "."
      case '*' => ".*"
      case '.' => "\\."
      case '+' => "\\+"
      case c => c.toString
    }.r
  }

  private[utf8bom] implicit final class RegexOps(val __regex: Regex) extends AnyVal {
    def matches(s: String): Boolean = __regex.pattern.matcher(s).matches()
  }

  private[utf8bom] implicit final class ClassOps[T](val __class: Class[T]) extends AnyVal {
    def as[U]: Class[U] = __class.asInstanceOf[Class[U]]
  }


  private[utf8bom] implicit final class ProjectOps(val __project: Project) extends AnyVal {
    def service[T : ClassTag]: Option[T] =
      Option(ServiceManager.getService(__project, classTag[T].runtimeClass.as[T]))
  }

  private[utf8bom] implicit final class DocumentOps(val __document: Document) extends AnyVal {
    def file: Option[VirtualFile] = Option(FileDocumentManager.getInstance.getFile(__document))
  }

  private[utf8bom] implicit final class VirtualFileOps(val __file: VirtualFile) extends AnyVal {
    def guessProject: Option[Project] = Option(ProjectUtil.guessProjectForFile(__file))
  }


  private[utf8bom] implicit final class ContainerOps(val __container: Container) extends AnyVal {
    def setEnabledAll(b: Boolean) {
      __container.setEnabled(b)
      __container.getComponents foreach {
        case cnt: Container => cnt.setEnabledAll(b)
        case cmp => cmp.setEnabled(b)
      }
    }
  }


  import scala.language.implicitConversions

  private[utf8bom] implicit def toItemListener(body: ItemEvent => Unit): ItemListener =
    new ItemListener {
      def itemStateChanged(e: ItemEvent) { body(e) }
    }

}
