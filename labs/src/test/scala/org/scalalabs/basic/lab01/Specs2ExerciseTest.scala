package org.scalalabs.basic.lab01

import java.lang.{ IllegalArgumentException => IAE }
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification

/**
 * In this Lab you will implement a Specs2 testcase.
 *
 * Instructions:
 * 1. Implement the divide method in Euro that has the following signature:  def /(divider:Int) = ???
 * - If the divider is <=0 throw an IllegalArgumentException
 *
 * 2. Write a Specs2 specification to test:
 * - Happy flow (divider is > 0)
 * - Alternative flow (divider is <= 0)
 */

@RunWith(classOf[JUnitRunner])
class Specs2ExerciseTest extends Specification {

  "Euro" should {
    "divides positive number" in {
      val e: Euro = new Euro(1, 5)
      val result: Euro = e / 3
      result.euro ==== 0
      result.cents ==== 35
    }
    "throws an IllegalArgumentException" in {
      val e = new Euro(2)
      (e / 0) must throwA[IAE]
    }
  }
}
