package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement

class DirectiveSectionState : DirectiveState() {

  private var items = ArrayList<String>()

  override fun changeState(
    ctx: DirectiveContext,
    bodyElement: IBodyElement
  ) {
    // TOOD skip for now
    ctx.items = items
    ctx.state = ExecutorDirectiveState()
  }
}
