package ch.bs.statistik.indicators.components

import diode.react.ModelProxy
import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.prefix_<^._

object KeywordSelectorComponent {

  case class Props(proxy: ModelProxy[Set[String]])

  private val component = ReactComponentB[Props](ChartGridComponent.getClass.getSimpleName)
    .render_P(props => {
      val keywords = props.proxy.value
      //<.div(s"${keywords.mkString(", ")}")
      <.div(
        <.select(
          //keywords.map(<.option(^.value:=_, _))
          keywords.map(keyword => <.option(^.value:=keyword, keyword))
        )
      )
    })
    .build

  def apply(modelProxy: ModelProxy[Set[String]]) = component(Props(modelProxy))

}
