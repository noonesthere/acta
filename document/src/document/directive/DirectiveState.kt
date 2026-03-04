package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement

abstract class DirectiveState {
  fun handleInput(ctx: DirectiveContext, bodyElement: IBodyElement) {
    changeState(ctx, bodyElement)
  }

  protected abstract fun changeState(
    ctx: DirectiveContext,
    bodyElement: IBodyElement
  )


}
