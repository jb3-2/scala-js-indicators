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

      val keywordSelector = {
        // zoom in to a union of all keywords across all charts
        val proxy = props.proxy.zoom(m => m.charts.flatMap(_.keywords).toSet)
        KeywordSelectorComponent(proxy)
      }

      val chartGrid = {
        // zoom in to a List of charts
        val proxy = props.proxy.zoom(m => m.charts)
        ChartGridComponent(proxy)
      }

      <.div(keywordSelector, chartGrid)
    })
    .componentDidMount($ => {
      $.props.proxy.dispatch(LoadChartsAction())
    })
    .build

  def apply(modelProxy: ModelProxy[AppModel]) = component(Props(modelProxy))

}
