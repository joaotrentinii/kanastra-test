
plugins {
    id("org.jetbrains.kotlinx.kover")
}

kover {
    xmlReport { onCheck.set(true) }
    htmlReport { onCheck.set(true) }
    filters {
        classes {
            excludes += listOf(
                "**.*\$\$serializer",
                "platform.policy.runner.starter.**",
                "platform.policy.runner.driven.processes.Queries**",
            )
        }
    }
}
