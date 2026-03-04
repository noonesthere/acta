package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement

class DirectiveContext(var state: DirectiveState = StartDirectiveState()) {

  var title: String = ""
  var date: String = ""
  var protocolNumber: String = ""
  var description: String = ""
  var items: List<String> = ArrayList()
  var executor: String = ""

  fun handleInput(bodyElement: IBodyElement) {
    state.handleInput(this, bodyElement)
  }

  fun toDirective(fileName: String) = Directive(
    fileName = fileName,
//    title = this.title,
    date = this.date,
//    protocolNumber = this.protocolNumber,
//    description = this.description,
//    items = this.items,
//    executor = this.executor,
  )
}

