package document

import klite.Config
import java.nio.file.Paths
import java.util.*

fun main() {
  System.setProperty("user.country", "UA")
  System.setProperty("user.language", "uk")
  System.setProperty("user.timezone", "Europe/Kyiv")

  TimeZone.setDefault(TimeZone.getTimeZone(System.getProperty("user.timezone")))
  val newLocale = Locale("uk", "UA")
  Locale.setDefault(newLocale)

  Config.useEnvFile()
  val path = Config.required("SAMPLE")
  val documentParser = DocumentParser()

  val directiveResult = documentParser.parse(Paths.get(path))

  directiveResult.onSuccess { directive ->
    println(directive)
  }
  directiveResult.onFailure { error -> println("Error: $error") }
}
