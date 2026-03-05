package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

private const val MAIN_KEYWORD = "НАКАЗ"

class StartDirectiveState : DirectiveState() {
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
    bodyElement: XWPFTable
  ) {

//    println("Found table on start: ${bodyElement.text}")
  }

  private fun handleParagraph(
    ctx: DirectiveContext,
    paragraph: XWPFParagraph
  ) {
    if (paragraph.text.contains(MAIN_KEYWORD)) {
      ctx.state = NumberAndDateDirectiveState()
    }

  }
}
