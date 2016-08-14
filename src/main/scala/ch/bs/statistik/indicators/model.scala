package ch.bs.statistik.indicators

object model {

  type ChartId = Int

  case class Chart(id: ChartId, title: String, keywords: Set[String] = Set())

  case class AppModel(charts: List[Chart] = List())

}
