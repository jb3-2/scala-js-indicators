package ch.bs.statistik.indicators

import ch.bs.statistik.indicators.Controller.{Circuit, LoadChartsAction}
import ch.bs.statistik.indicators.model.AppModel
import diode.react.ModelProxy
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{ReactComponentB, ReactDOM, _}
import org.scalajs.dom._

import scala.scalajs.js

object MainApp extends js.JSApp {

  val PageComponent = ReactComponentB[ModelProxy[AppModel]]("PageComponent")
    .render_P(props => {
      val charts = props.value.charts
      <.ul(charts.map(chart => <.li(s"Chart ${chart.id}, ${chart.title}")))
    })
    .componentDidMount($ => {
      $.props.dispatch(LoadChartsAction())
    })
    .build

  def main() = {

    // Get a connected model from the circuit
    val modelConnection = Circuit.connect(m => m)

    // Create a connected component
    val component = modelConnection(modelProxy => PageComponent(modelProxy))

    // Looks up the dom element into which we will render our react components
    val parent = document.getElementById("root")

    // Actually render the react component
    ReactDOM.render(component, parent)
  }

}
