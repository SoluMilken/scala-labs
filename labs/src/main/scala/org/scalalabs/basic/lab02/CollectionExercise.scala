package org.scalalabs.basic.lab02
/**
 * This Lab contains exercises where the usage of
 * higher order collection methods can be rehearsed.
 */
import sys._

object CollectionExercise01 {

  /**
   * Taken from: <a href="http://code.google.com/codejam/contest/1460488/dashboard">Problem A. Speaking in Tongues</a>
   *
   * Problem
   * The aim of this task is to translate a language into a new language called Googlerese.
   * To translate we take any message and replace each English letter with another English letter.
   * This mapping is one-to-one and onto, which means that the same input letter always gets replaced
   * with the same output letter, and different input letters always get replaced with different output letters.
   * A letter may be replaced by itself. Spaces are left as-is.
   *
   * For example (and here is a hint!), the translation algorithm includes the following three mappings:
   * 'a' -> 'y', 'o' -> 'e', and 'z' -> 'q'. This means that "a zoo" will become "y qee".
   *
   * Sample Input/Output
   * Input:
   * Case 1: ejp mysljylc kd kxveddknmc re jsicpdrysi
   * Case 2: rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
   * Case 3: de kr kd eoya kw aej tysr re ujdr lkgc jv
   *
   * Output:
   * Case 1: our language is impossible to understand
   * Case 2: there are twenty six factorial possibilities
   * Case 3: so it is okay if you want to just give up
   *
   */
  def googleCodeJamGooglerese(lines: String*): Seq[String] = {
    val codeBook = Map(
      'f' -> 'c',
      't' -> 'w',
      'k' -> 'i',
      'e' -> 'o',
      'j' -> 'u',
      's' -> 'n',
      'u' -> 'j',
      'm' -> 'l',
      'd' -> 's',
      'p' -> 'r',
      'i' -> 'd',
      'x' -> 'm',
      'a' -> 'y',
      'y' -> 'a',
      'l' -> 'g',
      'n' -> 'b',
      'c' -> 'e',
      'g' -> 'v',
      'o' -> 'k',
      'h' -> 'x',
      'r' -> 't',
      'b' -> 'h',
      'v' -> 'p',
      'w' -> 'f',
    )
    var output = List[String]()
    for (i <- 0 to (lines.length - 1)) {
      var line = lines(i)
      var outputString: String = ""
      for (char <- line) {
        if (char != ' ') {
          outputString = outputString + codeBook(char)
        } else {
          outputString = outputString + " "
        }
      }
      output = output :+ outputString
    }
    output
  }
}

/*========================================================== */

object CollectionExercise02 {

  class Person(val age: Int, val name: String) extends Ordered[Person] {

    def compare(that: Person): Int = {
        if (this.name == that.name) { 0 }
        else if (this.name < that.name) { -1 }
        else { 1 }
    }

    override def toString: String = {s"$name: $age"}
  }

  /**
   * Take a look at the java class: {@link ImperativeSample}. The
   * groupAdultsPerAgeGroup is implemented using an imperative programming
   * style.
   * Rewrite the method groupAdultsPerAgeGroup in the ImperativeSample java class
   * using a functional approach.
   */
  def groupAdultsPerAgeGroup(persons: Seq[Person]): Map[Int, Seq[Person]] = {
    var ageGroup: Map[Int, Seq[Person]] = Map()
    for (person <- persons) {
      if (person.age > 18) {
        var category: Int = (person.age / 10) * 10
        if (ageGroup.contains(category)) {
          var oldPersons: Seq[Person] = ageGroup(category)
          ageGroup = ageGroup - category
          ageGroup += (category -> {oldPersons :+ person} )
        } else {
          ageGroup += (category -> List(person))
        }
      }
    }
    var sortedAgeGroup: Map[Int, Seq[Person]] = Map()
    for ((age, persons) <- ageGroup) {
      sortedAgeGroup += (age -> persons.sorted)
    }
    sortedAgeGroup
  }
}

/*========================================================== */

object CollectionExercise03 {
  /**
   * Create a method that checks that each subsequent value is greater than
   * the previous one.
   * E.g.:
   * checkValuesIncrease(Seq(1,2,3)) == true
   * checkValuesIncrease(Seq(1,2,2)) == false
   */
  def checkValuesIncrease[T](seq: Seq[T])(implicit ev: T => Ordered[T]): Boolean = {
    if (seq.length == 0) { return true }

    var prevElement = seq(0)
    for (i <- 1 to (seq.length - 1)){
      var currentElement = seq(i)
      if (prevElement >= currentElement){
        return false
      }
      prevElement = currentElement
    }
    true
  }
}

/*========================================================== */

object CollectionExercise04 {
  /**
   * Calculate the length of the longest word in a list of sentences.
   * To keep it simple it's ok to use String.split to extract all words of a sentence.
   */
  def calcLengthLongestWord(lines: String*): Int = {
    var maxLength: Int = -1
    var tokenLen: Int = 0
    var tokens = new Array[String](1000)

    for (line <- lines){
      tokens = line.split(" ")
      for (token <- tokens) {
        tokenLen = token.length
        if (tokenLen > maxLength){
          maxLength = tokenLen
        }
      }
    }
    maxLength
  }
}

/*========================================================== */

object CollectionExercise05 {
  /**
   * Filter all even numbers of the given sequence using foldLeft.
   * E.g. Seq(1,2,3) is Seq(2)
   */
  def filterWithFoldLeft(seq: Seq[Int]): Seq[Int] = {
    error("fix me")
  }

  /**
   * Group all numbers based on whether they are even or odd using foldLeft.
   * For even use 'true' for odd use 'false'.
   * E.g: Seq(1,2,3) is Map(true -> Seq(2), false -> Seq(1,3))
   */
  def groupByWithFoldLeft(seq: Seq[Int]): Map[Boolean, Seq[Int]] = {
    error("fix me")
  }
}

