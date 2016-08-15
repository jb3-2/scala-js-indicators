package ch.bs.statistik.indicators

import ch.bs.statistik.indicators.model.{AppModel, Chart}
import diode._
import diode.react.ReactConnector

object Controller {

  case class LoadChartsAction() extends Action

  class LoadChartsHandler[M](modelRW: ModelRW[M, List[Chart]]) extends ActionHandler(modelRW) {

    override def handle = {
      case a @ LoadChartsAction() =>
        println(s"Handling action $a")
        // TODO: Load from JSON
        val charts = List(
          Chart(1, "one", Set("Banana", "Apple", "Pear")),
          Chart(2, "two", Set("Lemon", "Banana", "Pear", "Raspberry")),
          Chart(3, "three", Set("Coconut", "Bike", "Apple"))
        )
        updated(charts)
    }

  }

  case class FilterByKeywordAction(keywords: Set[String]) extends Action

  class FilterChartsHandler[M](modelRW: ModelRW[M, AppModel]) extends ActionHandler(modelRW) {

    override def handle = {
      case FilterByKeywordAction(keywords) =>

        val oldAppModel = value
        val filteredCharts = oldAppModel.charts.filter { chart =>
          keywords.intersect(chart.keywords).size == keywords.size
        }

        val newAppModel = oldAppModel.copy(selectedKeywords = keywords, filteredCharts = filteredCharts)

        updated(newAppModel)
    }

  }

  object Circuit extends Circuit[AppModel] with ReactConnector[AppModel] {

    override protected def initialModel = AppModel()

    private val loadChartsHandler = {
      val readModel = (originalModel: AppModel) => originalModel.charts
      val writeModel = (originalModel: AppModel, updatedCharts: List[Chart]) =>
        originalModel.copy(charts = updatedCharts, filteredCharts = updatedCharts)
      val modelRW = zoomRW(readModel)(writeModel)
      new LoadChartsHandler[AppModel](modelRW)
    }

    private val filterChartsHandler = {
      new FilterChartsHandler[AppModel](zoomRW(m => m)((m, v) => v))
    }

    override protected def actionHandler = composeHandlers(loadChartsHandler, filterChartsHandler)
  }

}
