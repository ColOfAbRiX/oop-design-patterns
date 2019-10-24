package com.colofabrix.scala.designpatterns

import pureconfig._
import pureconfig.generic.auto._
import wvlet.log.LogSupport

// SINGLETON PATTERN  //

/**
 * Global application configuration
 */
class GlobalConfig private () extends LogSupport {
  private val source = ConfigSource.default
    .at("com.colofabrix.scala.designpatterns")

  val loaderType: LoaderType = source
    .at("loader")
    .loadOrThrow[LoaderType]
  debug(s"LoaderType configuration: $loaderType")

  val filterType: FilterType = source
    .at("filter")
    .loadOrThrow[FilterType]
  debug(s"FilterType configuration: $filterType")
}

object GlobalConfig extends LogSupport {
  private var globalConfig: GlobalConfig = null

  def apply(): GlobalConfig = {
    // Creates a new instance only when it's never been created before
    if (this.globalConfig == null) {
      info("Loading configuration")
      this.globalConfig = new GlobalConfig()
    }
    // Return the stored instance
    this.globalConfig
  }
}

//  CONFIGURATION USING ADT  //

/**
 * Possible configurations for the type of loader
 */
sealed trait LoaderType
final case object EmptyLoaderType extends LoaderType
final case class FileLoaderType(filePath: String) extends LoaderType
final case object RandomLoaderType extends LoaderType

/**
 * Possible configurations for the type of filter
 */
sealed trait FilterType
final case object NoopFilterType extends FilterType
final case class DayFilterType(day: Int) extends FilterType
final case class AmountRangeFilterType(min: Double, max: Double) extends FilterType


// This would be the idiomatic Scala way for a Singleton
object ScalaGlobalConfig {
  def loaderType: String = ???
  def filterType: String = ???
}
