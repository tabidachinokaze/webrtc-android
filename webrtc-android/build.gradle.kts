import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "org.webrtc"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    sourceSets {
        getByName("main") {
            java.srcDirs(
                "src/rtc_base/java",
                "src/api/java",
                "src/generated/java",
                "src/jni_zero/java",
            )
            jniLibs.srcDir("src/main/jni")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(libs.androidx.annotation)
    implementation(libs.junit)
}

mavenPublishing {
    coordinates("moe.tabidachi", "webrtc-android", "7420f62a24")

    pom {
        name.set("WebRTC Android")
        description.set("WebRTC library for Android")
        inceptionYear.set("2025")
        url.set("https://github.com/tabidachinokaze/webrtc-android")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("tabidachinokaze")
                name.set("tabidachinokaze")
                email.set("kaze@tabidachi.moe")
                url.set("https://github.com/tabidachinokaze/")
            }
        }
        scm {
            url.set("https://github.com/tabidachinokaze/webrtc-android/tree/master")
            connection.set("scm:git:github.com/tabidachinokaze/webrtc-android.git")
            developerConnection.set("scm:git:ssh://github.com/tabidachinokaze/webrtc-android.git")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}
