package org.scalalabs.basic.lab01

import org.junit.runner.RunWith
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner
import java.lang.IllegalArgumentException

/**
 * In this Lab you will implement a ScalaTest testcase.
 *
 * Instructions:
 * 1. Implement the divide method in Euro that has the following signature:  def /(divider:Int) = ???
 * - If the divider is <=0 throw an IllegalArgumentException
 *
 * 2. Write a ScalaTest using a Spec of your choice to test:
 * - Happy flow (divider is > 0)
 * - Alternative flow (divider is <= 0)
 */

@RunWith(classOf[JUnitRunner])
class ScalaTestExerciseTest extends AnyFunSpecLike with Matchers {

  describe("Euro divide method") {
    it("should divide positive number") {
      val e: Euro = new Euro(1, 5)
      val result: Euro = e / 3
      assert(result.euro === 0)
      assert(result.cents === 35)
    }

    it("throw an IllegalArgumentException") {
      val e: Euro = new Euro(1, 5)
      intercept[IllegalArgumentException] {
        e / 0
      }
    }
  }
}
