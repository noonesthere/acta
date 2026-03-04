package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

class DescriptionDirectiveState : DirectiveState() {
  private var description: String = ""

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
    println("Table found: ${bodyElement.text}")
  }

  private fun handleParagraph(ctx: DirectiveContext, paragraph: XWPFParagraph) {
    if (description.isEmpty() && paragraph.text.isBlank()) {
      return;
    }

    if (description.isNotEmpty() && paragraph.text.isBlank()) {
      ctx.description = description
      ctx.state = DirectiveSectionState()
      return
    }

    this.description += "$description ${paragraph.text.trim()}"
  }
}
