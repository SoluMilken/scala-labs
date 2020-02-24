package org.scalalabs.basic.lab01

import scala.language.implicitConversions
import java.lang.IllegalArgumentException

/**
 * The goal of this exercise is to get familiar basic OO constructs in scala
 *
 * Fix the code so that the unit test 'CurrencyExerciseTest' passes.
 *
 * In order for the tests to pass you need to do the following:
 *
 * Exercise 1:
 * - Create a class Euro
 * - Provide it with two constructor parameters: euro:Int, cents:Int
 * - Provide the cents field with default value: 0
 * - Provide an immutable field named: inCents that converts euro + cents into cents.
 * - Create an object Euro with a factory method named: fromCents that creates an Euro based on cents.
 * - Create a method named: + to the Euro class that adds another Euro
 * - Create a method named: * to the Euro class that multiplies an Euro
 *
 * Exercise 2:
 * - Create an abstract class Currency
 * - Provide it with one constructor parameter: symbol:String
 * - Extend the previously created Euro class from Currency
 * - Override the toString method of Euro to represent the following String:
 *   -> symbol + ': ' + euro + ',' + cents.  E.g: EUR 200,05
 * - In case the cents are 0 use this representation:
 *   -> symbol + ': ' + euro + ',--. E.g.: EUR 200.--
 *
 * Exercise 3:
 * - Mix the Ordered trait in Euro
 * - Implement the compare method
 *
 * Exercise 4:
 * - Provide an implicit class that adds a *(euro:Euro) method to Int
 * - Create a new currency Dollar
 * - Provide a implicit conversion method that converts from Dollar to Euro using the
 *   [[org.scalalabs.basic.lab01.DefaultCurrencyConverter]]
 *
 * Exercise 5:
 * - Extend the conversion method from Dollar to Euro with an implicit parameter
 *   of type [[org.scalalabs.basic.lab01.CurrencyConverter]]
 * - Use the implicit CurrencyConverter to do the conversion.
 */

class Euro(var euro: Int, var cents: Int = 0) extends Currency("EUR") with Ordered[Euro] {
  val inCents: Int = { euro * 100 + cents }

  def +(that: Euro): Euro = {
    val total_cents: Int = { this.inCents + that.inCents }
    new Euro({ total_cents / 100 }, { total_cents % 100 })
  }

  def *(factor: Int): Euro = {
    val total_cents: Int = { this.inCents * factor }
    new Euro({ total_cents / 100 }, { total_cents % 100 })
  }

  override def toString: String = {
    if (cents != 0) {
      "%s: %d,%02d".format(symbol, euro, cents)
    } else {
      "%s: %d,--".format(symbol, euro)
    }
  }

  def /(divider: Int) = {
    if (divider <= 0) {
      throw new IllegalArgumentException
    } else {
      val resultInCents: Int = { inCents / divider }
      new Euro({ resultInCents / 100 }, { resultInCents % 100 })
    }
  }

  def compare(that: Euro): Int = { this.inCents - that.inCents }

}

abstract class Currency(val symbol: String) {
  def toString(): String
}

class Dollar(var dollar: Int, var dollarCents: Int = 0) {
  val inCents: Int = { dollar * 100 + dollarCents }
}

object Euro {
  def fromCents(cents: Int): Euro = {
    new Euro({ cents / 100 }, { cents % 100 })
  }

  implicit class EuroMultiply(factor: Int) {
    def *(euro: Euro) = { euro * factor }
  }

  implicit def dollarToEuro(dollar: Dollar)(
    implicit
    converter: CurrencyConverter = DefaultCurrencyConverter): Euro = {
    val totalEuroCents: Int = converter.toEuroCents(dollar.inCents)
    new Euro({ totalEuroCents / 100 }, { totalEuroCents % 100 })
  }
}
