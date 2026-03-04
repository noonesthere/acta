package document

import java.nio.file.Paths


class Document {
  fun main() {
    val documentParser = DocumentParser()

    documentParser.parse(Paths.get(""))

  }
}
