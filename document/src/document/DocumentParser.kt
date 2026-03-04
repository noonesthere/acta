package document

import document.directive.Directive
import document.directive.DirectiveContext
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.nio.file.Path

class DocumentParser {
  fun parse(file: Path): Result<Directive> =
    runCatching {
      file.toFile().inputStream().use { input ->
        XWPFDocument(input).use { document ->
          val context = DirectiveContext().apply {
            document.bodyElements.forEach(::handleInput)
          }
          context.toDirective(file.fileName.toString())
        }
      }
    }
}
