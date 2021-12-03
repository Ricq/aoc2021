package day3

import util.Utils.using

import scala.io.Source

class Day3 {
  def runPart1(): Unit = {

    using(Source.fromFile("day3/test1.txt")) { file =>
      for (line <- file.getLines()) {
      }
    }
  }


  def runPart2(): Unit = {
    using(Source.fromFile("day2/test1.txt")) { file =>
      for (line <- file.getLines()) {
      }
    }
  }
}