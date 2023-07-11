plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.hwaryun.pokemon_detail"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
}