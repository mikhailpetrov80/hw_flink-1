package org.example

import org.apache.flink.api.scala._
import scala.util.Try

object MaxMinEpisodes {
  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val text = env.readCsvFile[(String, String, String, String, String, String)]("file:/Users/vodnik/Documents/neoflex/hw_flink-1/tv_shows_data.csv", ignoreFirstLine = true, lenient = true)
      .map(elem => (elem._2, Try(elem._6.toInt).getOrElse(0)))
      .groupBy(0)
      .sum(1)

    text.max(1).print()
    text.min(1).print()

  }
}
