package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

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
      //TODO: handle if no year keyword
//      ctx.date = paragraph.text.substringBefore(YEAR_KEYWORD)
//        .trim()
//        .replace("р", "")
//        .replace("\\s+".toRegex(), ".")
      ctx.date = text
      ctx.protocolNumber = text.substringAfter(KEYWORD).trim().replace("\\s+".toRegex(), "")
      ctx.state = FinishedDirectiveState()
      return
    }
  }
}

