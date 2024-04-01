
plugins {
    id("org.sonarqube")
}

sonarqube {
    properties {
        property("sonar.tests", "src/test/kotlin")
        property("sonar.sources", "src/main/kotlin")
        property("sonar.host.url", "https://localhost.com")
        property("sonar.projectKey", "kanastra-test")
        property("sonar.projectName", "kanastra-test")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/xml/report.xml")
    }
}
