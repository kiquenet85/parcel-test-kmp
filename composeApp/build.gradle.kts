plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.touchlab.skie)
    kotlin("plugin.serialization") version "1.9.22"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true

            export(libs.decompose.decompose)
            export(libs.essenty.lifecycle)
        }
    }

    dependencies {
        //TODO mockative requires this but may be deleted in the future
        configurations
            .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
            .forEach {
                add(it.name, "io.mockative:mockative-processor:2.0.1")
            }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            // Koin
            implementation(libs.bundles.koin.android)

            // Ktor
            implementation(libs.ktor.client.okhttp)

            // SQLDelight
            implementation(libs.sqldelight.android.driver)
        }
        commonMain.dependencies {

            api(libs.decompose.decompose)
            api(libs.essenty.lifecycle)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Coroutines
            implementation(libs.org.jetbrains.kotlinx.coroutines.core)

            // Compose - Coil
            implementation(libs.coil.compose)

            // Koin
            implementation(libs.bundles.koin.shared)

            // Ktor
            implementation(libs.coil.network.ktor)

            // Material Design
            implementation(compose.material3)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // SQLDelight
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines)

            // Voyager
            implementation(libs.bundles.voyager)
        }

        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)

            // SQLDelight
            implementation(libs.sqldelight.ios.driver)

            // TouchLab
            implementation(libs.touchlab.stately.common)
        }

        commonTest.dependencies {
            // Mockative
            val commonTest by getting {
                dependencies {
                    // Mockative
                    implementation(libs.mockative)

                    // Turbine
                    implementation(libs.app.cash.turbine)
                }
            }

            // Coroutines
            implementation(libs.org.jetbrains.kotlinx.coroutines.test)

            // Kotlin test
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.test.junit)
        }
    }
}

android {
    namespace = "co.nes.parceltestkmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "co.nes.parceltestkmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

sqldelight {
    databases {
        create("VacationDatabase") {
            packageName.set("co.nes.parceltestkmp.data_access.database")
        }
    }
}
