package ch.bs.statistik.indicators

import ch.bs.statistik.indicators.model.{AppModel, Chart}
import diode._
import diode.react.ReactConnector

object Controller {

  case class LoadChartsAction() extends Action

  class LoadChartsHandler[AppModel](modelRW: ModelRW[AppModel, List[Chart]]) extends ActionHandler(modelRW) {

    override protected def handle = {
      case a @ LoadChartsAction() =>
        println(s"Handling action $a")
        // TODO: Load from JSON
        val charts = List(Chart(1, "one"), Chart(2, "two"), Chart(3, "three"))
        updated(charts)
    }

  }

  object Circuit extends Circuit[AppModel] with ReactConnector[AppModel] {

    override protected def initialModel = AppModel()

    override protected def actionHandler = {
      val readModel = (originalModel: AppModel) => originalModel.charts
      val writeModel = (originalModel: AppModel, updatedCharts: List[Chart]) => originalModel.copy(charts = updatedCharts)
      val modelRW = zoomRW(readModel)(writeModel)
      new LoadChartsHandler[AppModel](modelRW)
    }

  }

}
