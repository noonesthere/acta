package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable
import java.time.LocalDate

private const val KEYWORD = "№"
private const val YEAR_KEYWORD = "м"

class NumberAndDateDirectiveState : DirectiveState() {
  override fun changeState(
    ctx: DirectiveContext,
    bodyElement: IBodyElement
  ) {
    when (bodyElement) {
      is XWPFParagraph -> handleParagraph(ctx, bodyElement)
      is XWPFTable -> handleTable(ctx, bodyElement)
    }
  }

  private fun handleTable(
    ctx: DirectiveContext,
    table: XWPFTable
  ) {
    if (table.rows.size == 1) {
      handle(ctx, table.text)
    } else {
      println("Found table during date and number expectation")
    }
  }

  private fun handleParagraph(
    ctx: DirectiveContext,
    paragraph: XWPFParagraph
  ) {
    handle(ctx, paragraph.text)
  }


  private fun handle(ctx: DirectiveContext, text: String) {
    if (text.isBlank()) return

    if (text.contains(KEYWORD)) {
      //TODO: @noonesthere handle if no year keyword
      val trimmedText = text.trimIndent().replace("\t", "")
      ctx.date = extractDate(trimmedText)
      ctx.directiveNumber = text.substringAfter(KEYWORD).trim().replace("\\s+".toRegex(), "")
      ctx.state = TitleDirectiveState()
      return
    }
  }

  // draft version
  fun extractDate(text: String): LocalDate? {
    val regex = Regex("""\d{1,2}\.\d{1,2}\.\d{2,4}""")

    val match = regex.find(text)?.value ?: return null

    val (day, month, yearPart) = match.split(".")
    val dayInt = day.toInt()
    val monthInt = month.toInt()

    var year = yearPart.toInt()
    if (year < 100) year += 2000

    return LocalDate.of(year, monthInt, dayInt)
  }
}

