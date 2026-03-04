package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement

class FinishedDirectiveState : DirectiveState() {
  override fun changeState(
    ctx: DirectiveContext,
    bodyElement: IBodyElement
  ) {
    return;
  }

}
