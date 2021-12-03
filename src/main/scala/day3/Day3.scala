package day3

import util.Utils.using

import scala.collection.mutable.ListBuffer
import scala.io.Source

class Day3 {
  def runPart1(): Unit = {
    def processLine(bitOccurrences: Array[Int])(line: String): Unit = {
      bitOccurrences(0) = bitOccurrences(0) + 1
      for ((ch, index) <- line.toList.zipWithIndex) {
        if (ch == '1') {
          bitOccurrences(index + 1) = bitOccurrences(index + 1) + 1
        }
      }
    }

    using(Source.fromFile("day3/input.txt")) { file =>
      val lines = file.getLines()
      val firstLine = lines.next()

      val bitCount = firstLine.length
      val bitOccurrences = Array.fill[Int](bitCount + 1)(0)

      processLine(bitOccurrences)(firstLine)
      lines.foreach(processLine(bitOccurrences))

      val lineCount = bitOccurrences(0)
      println(bitOccurrences.mkString(","))

      val gammaBuilder = new StringBuilder
      for (bit <- bitOccurrences.drop(1)) {
        gammaBuilder.append(if (bit * 2 > lineCount) '1' else '0')
      }
      val gamma = Integer.parseInt(gammaBuilder.toString(), 2)
      val epsilon = ~gamma & ((1 << bitCount) - 1)
      println(s"Gamma rate: $gamma")
      println(s"Epsilon rate: $epsilon")

      val result = gamma * epsilon
      println(s"Result: $result")
    }


  }


  def runPart2(): Unit = {
    def reduce(numbers: List[Int], selector: (List[Int], List[Int]) => List[Int], bitMask: Int): Int = {
      val (set, unset) = numbers.partition(number => (number & bitMask) > 0)
      val result = selector(set, unset)
      if (result.length == 1) result(0) else reduce(result, selector, bitMask >> 1)
    }

    val (input, bitCount) = readBinaryNumbers
    val oxygenGeneratorRating = reduce(input, (set, unset) => if (set.length >= unset.length) set else unset, 1 << (bitCount - 1))
    val co2ScrubberRating = reduce(input, (set, unset) => if (set.length < unset.length) set else unset, 1 << (bitCount - 1))
    println(oxygenGeneratorRating)
    println(co2ScrubberRating)
    val lifeSupportRating = oxygenGeneratorRating * co2ScrubberRating
    println(lifeSupportRating)
  }

  private def readBinaryNumbers: (List[Int], Int) = {
    using(Source.fromFile("day3/input.txt")) { file =>
      val input = new ListBuffer[Int]()
      var bitCount = 0
      for (line <- file.getLines()) {
        var number = 0;
        for (ch <- line) {
          number = (number << 1) | (if (ch == '1') 1 else 0)
        }
        input += number
        bitCount = line.length
      }
      (input.toList, bitCount)
    }
  }
}