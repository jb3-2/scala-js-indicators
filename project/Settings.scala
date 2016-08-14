import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.{Def, _}

object Settings {

  val name = "Scala.js Indicators"
  val version = "0.1.0-SNAPSHOT"

  object versions {
    val scala = "2.11.8"

    // libraryDependencies
    val scalaJsReact = "0.11.1"
    val diode = "1.0.0"

    // jsDependencies
    val react = "15.0.1"
    val bootstrap = "3.3.2"
    val jQuery = "1.11.1"
  }

  val libraryDependencies = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalaJsReact,
    "me.chrons" %%% "diode" % versions.diode,
    "me.chrons" %%% "diode-react" % versions.diode
  ))

  val jsDependencies = Def.setting(Seq(
    "org.webjars.bower" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
    "org.webjars.bower" % "react" % versions.react / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
    "org.webjars" % "jquery" % versions.jQuery / "jquery.js" minified "jquery.min.js",
    "org.webjars" % "bootstrap" % versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js"
  ))

}
