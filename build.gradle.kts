import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories.applyDefault()
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("org.jacoco:org.jacoco.core:0.8.7")
    }
}

allprojects {
    repositories.applyDefault()
    repositories {
        maven("https://jitpack.io")
    }
}

subprojects {
    applySpotless
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
                "-Xuse-experimental=" +
                        "kotlin.Experimental," +
                        "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                        "kotlinx.coroutines.InternalCoroutinesApi," +
                        "kotlinx.coroutines.FlowPreview"
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks {
    register<Copy>("copyGitHooks") {
        from("$rootDir/scripts/") {
            include("**/*.sh")
            rename("(.*).sh", "$1")
        }
        into("$rootDir/.git/hooks")
    }

    register<Exec>("installGitHooks") {
        workingDir(rootDir)
        commandLine("chmod")
        args("-R", "+x", ".git/hooks/")
        dependsOn(named("copyGitHooks"))
        doLast {
            logger.info("Git hooks installed successfully.")
        }
    }
}