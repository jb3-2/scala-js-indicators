package ch.bs.statistik.indicators.components

import ch.bs.statistik.indicators.Controller.FilterByKeywordAction
import diode.react.ModelProxy
import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react._
import org.scalajs.dom.raw.HTMLSelectElement

object KeywordSelectorComponent {

  case class Props(proxy: ModelProxy[Set[String]])

  def handleKeywordSelection(proxy: ModelProxy[Set[String]])(e: SyntheticEvent[HTMLSelectElement]) = {
    proxy.dispatch(FilterByKeywordAction(Set(e.target.value)))
  }

  private val component = ReactComponentB[Props](KeywordSelectorComponent.getClass.getSimpleName)
    .render_P(props => {
      val keywords = props.proxy.value
      <.div(
        <.select(
          ^.onChange ==> handleKeywordSelection(props.proxy),
          keywords.map(keyword => <.option(^.value := keyword, ^.key := keyword, keyword))
        )
      )
    })
    .build

  def apply(modelProxy: ModelProxy[Set[String]]) = component(Props(modelProxy))

}
