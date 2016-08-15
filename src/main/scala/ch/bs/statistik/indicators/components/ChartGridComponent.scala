package ch.bs.statistik.indicators.components

import ch.bs.statistik.indicators.model.Chart
import diode.react.ModelProxy
import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.prefix_<^._

object ChartGridComponent {

  case class Props(proxy: ModelProxy[List[Chart]])

  private val component = ReactComponentB[Props](ChartGridComponent.getClass.getSimpleName)
    .render_P(props => {
      val charts = props.proxy.value
      <.div(charts.map(chart => <.div(s"id: ${chart.id}, title: ${chart.title}, keywords: ${chart.keywords}")))
    })
    .build

  def apply(modelProxy: ModelProxy[List[Chart]]) = component(Props(modelProxy))

}
