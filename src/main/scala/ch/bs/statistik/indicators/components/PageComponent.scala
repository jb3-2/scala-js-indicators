package ch.bs.statistik.indicators.components

import ch.bs.statistik.indicators.Controller.LoadChartsAction
import ch.bs.statistik.indicators.model.AppModel
import diode.react.ModelProxy
import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.prefix_<^._

object PageComponent {

  case class Props(proxy: ModelProxy[AppModel])

  private val component = ReactComponentB[Props](PageComponent.getClass.getSimpleName)
    .render_P(props => {
      val charts = props.proxy.value.charts
      // zoom in to a union of all keywords across all charts
      val keywordModelProxy = props.proxy.zoom(m => m.charts.flatMap(_.keywords).toSet)
      <.div(
        KeywordSelectorComponent(keywordModelProxy),
        <.ul(charts.map(chart => <.li(s"Chart ${chart.id}, ${chart.title}, ${chart.keywords.mkString(", ")}")))
      )
    })
    .componentDidMount($ => {
      $.props.proxy.dispatch(LoadChartsAction())
    })
    .build

  def apply(modelProxy: ModelProxy[AppModel]) = component(Props(modelProxy))

}
