plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.hwaryun.data"
}

dependencies {
    api(project(":core:database"))
    api(project(":core:network"))

    implementation(libs.converter.gson)
}