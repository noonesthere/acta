package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

class ExecutorDirectiveState : DirectiveState() {
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
    println("Found table when executor person expectation: ${bodyElement.text}")
  }

  private fun handleParagraph(
    ctx: DirectiveContext,
    paragraph: XWPFParagraph
  ) {
    if (paragraph.text.lowercase().contains("вик.:")) {
      ctx.executor = paragraph.text.trim()
      ctx.state = FinishedDirectiveState()
    }
  }

}
