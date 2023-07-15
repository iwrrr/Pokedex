plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.hwaryun.pokemon_catched"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}