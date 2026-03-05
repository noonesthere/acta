package document

import document.directive.Directive
import document.directive.DirectiveContext
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.nio.file.Files
import java.nio.file.Path

class DocumentParser {

  fun parse(path: Path): Result<Directive> =
    runCatching {
      Files.newInputStream(path).use { input ->
        XWPFDocument(input).use { document ->
          val context = DirectiveContext().apply {
            document.bodyElements.forEach(::handleInput)
          }
          context.toDirective(path.fileName.toString())
        }
      }
    }
}
