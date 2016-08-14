package ch.bs.statistik.indicators

import ch.bs.statistik.indicators.Controller.Circuit
import ch.bs.statistik.indicators.components.PageComponent
import japgolly.scalajs.react.ReactDOM
import org.scalajs.dom._

import scala.scalajs.js

object MainApp extends js.JSApp {

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
