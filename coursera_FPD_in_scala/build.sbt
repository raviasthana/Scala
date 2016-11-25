name := "coursera_fpd_in_scala"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-deprecation")

// grading libraries
libraryDependencies += "junit" % "junit" % "4.10" % "test"

// test
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

// scala check
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"