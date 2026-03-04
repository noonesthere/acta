package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFTable

class TitleDirectiveState : DirectiveState() {

  private var title: String = ""

  override fun changeState(
    ctx: DirectiveContext,
    bodyElement: IBodyElement //paragraph: XWPFParagraph
  ) {
    when (bodyElement) {
      is XWPFParagraph -> handleParagraph(ctx, bodyElement)
      is XWPFTable -> handleTable(ctx, bodyElement)
      else -> println("Unknown type ${bodyElement.javaClass}")
    }
  }

  private fun handleTable(
    ctx: DirectiveContext,
    bodyElement: XWPFTable
  ) {
    println("table found in title: ${bodyElement.text}")
  }

  private fun handleParagraph(
    ctx: DirectiveContext,
    paragraph: XWPFParagraph
  ) {
    if (title.isBlank() && paragraph.text.isNullOrBlank()) {
      return
    }

    if (!title.isBlank() && paragraph.text.isNullOrBlank()) {
      ctx.title = title
      ctx.state = DescriptionDirectiveState()
    }

    title = "$title ${paragraph.text.trim()}"
  }
}
