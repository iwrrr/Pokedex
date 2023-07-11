plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.hwaryun.pokedex"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
}