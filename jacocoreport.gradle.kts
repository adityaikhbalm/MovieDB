apply(plugin = "jacoco")

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
    }
}

task<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest", "createDebugCoverageReport")

    reports {
        html.isEnabled = true
        html.destination = file("${project.buildDir}/coverage-report")
        xml.isEnabled = false
        csv.isEnabled = false
    }

    val fileFilter = mutableSetOf(
        "**/*App.*",
        "**/*Application.*",
        "**/*Activity.*",
        "**/*Fragment.*",
        "**/*View.*",
        "**/*ViewGroup.*",
        "**/*JsonAdapter.*",
        "**/di/**",

        "android/databinding/**/*.class",
        "**/android/databinding/*Binding.class",
        "**/android/databinding/*",
        "**/androidx/databinding/*",
        "**/BR.*",

        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",

        "androidx/**/*.*",
        "android/**/*.*",
        "**/*\$Lambda$*.*",
        "**/*\$inlined$*.*",

        "**/*$Result.*",
        "**/*$Result$*.*",
        "**/*JsonAdapter.*"
    )

    classDirectories.setFrom(
        fileTree(project.buildDir) {
            include("**/tmp/kotlin-classes/debug/**")

            exclude(fileFilter)
        }
    )

    sourceDirectories.setFrom(
        fileTree("${project.buildDir}") {
            include(
                "src/main/java/**",
                "src/main/kotlin/**"
            )
        }
    )

    executionData.setFrom(
        fileTree(project.buildDir) {
            include("jacoco/jacocoTestReportDebug.exec")
        }
    )
}