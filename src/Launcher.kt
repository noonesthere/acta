import klite.AssetsHandler
import klite.Server
import java.nio.file.Path
import kotlin.io.path.exists

val assetsPath = Path.of("ui/").takeIf { it.exists() } ?: Path.of("ui/public")

fun startServer() = Server().apply {
  assets("/", AssetsHandler(assetsPath, useIndexForUnknownPaths = true))
  start()
}

fun main() {
  startServer()
}
