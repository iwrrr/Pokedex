plugins {
    id("android.app")
    id("android.hilt")
}

android {
    namespace = "com.hwaryun.pokedex"
}

dependencies {
    implementation(project(":core:designsystem"))
}