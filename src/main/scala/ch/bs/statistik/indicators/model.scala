package ch.bs.statistik.indicators

object model {

  type ChartId = Int

  case class Chart(id: ChartId, title: String)

  case class AppModel(charts: List[Chart] = List())

}
