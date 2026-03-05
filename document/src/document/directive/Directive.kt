package document.directive

import java.time.LocalDate

data class Directive(
  val fileName: String,
  val title: String = "",
  val date: LocalDate?,
  val directiveNumber: String = "",
  val description: String = "",
//  val items: List<String> = ArrayList(),
//  val executor: String = ""
)
