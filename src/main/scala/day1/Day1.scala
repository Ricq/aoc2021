package day1

import util.Utils.using

import scala.io.Source

class Day1 {
  def runPart1(): Unit = {
    using(Source.fromFile("day1/input.txt")) { file =>
      val tuples = file.getLines().map(Integer.parseInt).sliding(2, 1)
      val increasedCount = tuples.map(pair => pair(1) > pair.head).count(x => x)
      println(increasedCount);
    }
  }

  def runPart2(): Unit = {
    using(Source.fromFile("day1/input.txt")) { file =>
      val sums = file.getLines().map(Integer.parseInt).sliding(3, 1).map(seq => seq.sum)
      val result = sums.sliding(2, 1).map(pair => pair(1) > pair.head).count(x => x)
      println(result);
    }
  }
}
