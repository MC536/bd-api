name := "MC536-API"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  //Database
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  //ORM
  "org.hibernate" % "hibernate-core" % "4.3.5.Final",
  //Crypt
  "org.mindrot" % "jbcrypt" % "0.3m",
  //Log
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "net.logstash.logback" % "logstash-logback-encoder" % "3.0",
  //Test
  "junit" % "junit" % "4.11",
  "org.hamcrest" % "hamcrest-core" % "1.3",
  // Mail
  "com.sun.mail" % "javax.mail" % "1.4.7",
  "org.apache.commons" % "commons-email" % "1.3.3"
)
