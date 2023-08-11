plugins {
  // Apply the application plugin to add support for building an application in Java.
  application

  id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {

  // jPdf Tweak - Swiss Army Knife for PDF files
  implementation(":jpdftweakcore:")
  implementation(":itext:")
  implementation(":forms:")
  implementation(":bcprov:")
  implementation(":bcmail:")

  // Commons lang for String utils
  implementation(":commons-lang3:3.12.0")
}

application {
  // The main class for the application.
  mainClass.set("eu.tobse.tool.pdfwatermark.App")
}

repositories {
  flatDir {
    dirs("lib")
  }
}