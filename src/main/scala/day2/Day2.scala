package day2

import util.Utils.using

import scala.io.Source

class Day2 {
  def runPart1(): Unit = {
    var horizontal = 0
    var depth = 0

    using(Source.fromFile("day2/input.txt")) { file =>
      for (line <- file.getLines()) {
        val parts = line.split(' ')
        val (direction: String, amount: Int) = (parts(0), parts(1).toInt)
        direction match {
          case "forward" => horizontal += amount
          case "down" => depth += amount
          case "up" => depth -= amount
        }
      }
    }

    val result = horizontal * depth
    println(result)
  }


  def runPart2(): Unit = {
    var horizontal = 0
    var depth = 0
    var aim = 0

    using(Source.fromFile("day2/input.txt")) { file =>
      for (line <- file.getLines()) {
        val parts = line.split(' ')
        val (direction: String, amount: Int) = (parts(0), parts(1).toInt)
        direction match {
          case "forward" => horizontal += amount; depth += aim * amount;
          case "down" => aim += amount
          case "up" => aim -= amount
        }
      }
    }

    val result = horizontal * depth
    println(result)
  }

}