
plugins {
    id("org.sonarqube")
}

sonarqube {
    val exclusions = listOf(
        "/src/main/kotlin/platform/policy/runner/starter/**",
        "/src/main/kotlin/platform/policy/runner/driven/processes/Queries.kt"
    )

    properties {
        property("sonar.tests", "src/test/kotlin")
        property("sonar.sources", "src/main/kotlin")
        property("sonar.host.url", "https://sonarqube.intranet.pagseguro.uol")
        property("sonar.projectKey", "ps-crossborder-platform-policy-runner")
        property("sonar.projectName", "policy-runner")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.coverage.exclusions", exclusions)
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/xml/report.xml")
    }
}
