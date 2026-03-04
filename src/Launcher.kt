import document.Document
import klite.AssetsHandler
import klite.Server
import mail.Mail
import java.nio.file.Path
import kotlin.io.path.exists

val assetsPath = Path.of("ui/").takeIf { it.exists() } ?: Path.of("ui/public")

fun startServer() = Server().apply {
  assets("/", AssetsHandler(assetsPath, useIndexForUnknownPaths = true))
  start()
}

fun main() {
  Document().stub()
  Mail().stub()
  startServer()
}
