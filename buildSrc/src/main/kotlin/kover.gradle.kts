
plugins {
    id("org.jetbrains.kotlinx.kover")
}

kover {
    xmlReport { onCheck.set(true) }
    htmlReport { onCheck.set(true) }
    filters {
        classes {
            excludes += listOf("**.*\$\$serializer")
        }
    }
}
