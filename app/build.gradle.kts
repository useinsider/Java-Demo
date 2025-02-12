plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.useinsider.insiderjavademo"
    compileSdk = 35

    defaultConfig {
        // TODO: Please change with your application ID as it is in google-service.json
        //  and agconnect-services.json.
        applicationId = "com.useinsider.insiderjavademo"

        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // TODO: Please change with your partner name. You can find the partner name after login
        //  into the Insider panel. The left side of your mail address.
        manifestPlaceholders["partner"] = "your_partner_name"
        manifestPlaceholders["googleAdsAppId"] = project.findProperty("GOOGLE_ADS_APP_ID") ?: ""


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.core.ktx)

    //Required
    implementation(libs.insider.sdk)
    implementation(libs.firebase.messaging)
    implementation(libs.lifecycle.process)
    implementation(libs.play.services.ads)
    implementation(libs.security.crypto)

    implementation(libs.huawei.push)
    implementation(libs.huawei.ads)
    implementation(libs.huawei.location)


    //Optional for Geofence
    implementation(libs.play.services.location)

    //Optional for logging
    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}