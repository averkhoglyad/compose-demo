import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "io.averkhoglyad"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {

    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose-demo"
            packageVersion = "1.0.0"
        }
    }
}

val voyagerVersion = "1.0.0-rc06"
dependencies {
//    commonMainImplementation("com.arkivanov.decompose:decompose:1.0.0")

    commonMainImplementation("cafe.adriel.voyager:voyager-core-desktop:$voyagerVersion")
    commonMainImplementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    commonMainImplementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
    commonMainImplementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
    commonMainImplementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    commonMainImplementation("com.darkrockstudios:mpfilepicker:1.1.0")

    commonMainImplementation("com.vk.api:sdk:1.0.14")

}