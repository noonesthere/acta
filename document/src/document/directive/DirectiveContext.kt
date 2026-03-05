package document.directive

import org.apache.poi.xwpf.usermodel.IBodyElement
import java.time.LocalDate

class DirectiveContext(var state: DirectiveState = StartDirectiveState()) {

  var title: String = ""
  var date: LocalDate? = null
  var directiveNumber: String = ""
  var description: String = ""
  var items: List<String> = ArrayList()
  var executor: String = ""

  fun handleInput(bodyElement: IBodyElement) {
    state.handleInput(this, bodyElement)
  }

  fun toDirective(fileName: String) = Directive(
    fileName = fileName,
    date = this.date,
    directiveNumber = this.directiveNumber,
    title = this.title,
    description = this.description
//    items = this.items,
//    executor = this.executor,
  )
}

