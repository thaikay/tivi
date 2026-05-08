# 🚀 ThaiTV APK Build Guide

## Prerequisite

1. **Android Studio** - Download from [https://developer.android.com/studio](https://developer.android.com/studio)
2. **Java Development Kit (JDK) 11+** - Usually included with Android Studio
3. **Android SDK** - API Level 24 (Android 7.0) or higher
4. **Git** - For version control

## Installation Steps

### 1. Clone Repository

```bash
git clone https://github.com/thaikay/tivi.git
cd tivi
```

### 2. Open in Android Studio

1. Open Android Studio
2. Click **File** → **Open**
3. Navigate to the `tivi` folder
4. Click **OK**
5. Wait for Gradle to sync (may take 2-5 minutes)

### 3. Build APK

#### Debug APK (for testing)

```bash
./gradlew assembleDebug
```

Output: `app/build/outputs/apk/debug/app-debug.apk`

#### Release APK (for distribution)

```bash
./gradlew assembleRelease
```

Output: `app/build/outputs/apk/release/app-release-unsigned.apk`

### 4. Sign Release APK (Optional but Recommended)

To sign your APK for distribution on Google Play Store:

#### Generate Keystore

```bash
keytool -genkey -v -keystore my-release-key.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
```

Follow the prompts to set password and key information.

#### Sign APK

```bash
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.keystore app/build/outputs/apk/release/app-release-unsigned.apk my-key-alias
```

#### Align APK

```bash
$ANDROID_SDK_ROOT/build-tools/34.0.0/zipalign -v 4 app/build/outputs/apk/release/app-release-unsigned.apk ThaiTV-1.0.0.apk
```

## Install on Device

### Via Android Studio

1. Connect your Android device or launch an emulator
2. Click **Run** → **Run 'app'** (or press **Shift+F10**)
3. Select your device
4. Click **OK**

### Via Command Line

```bash
# List connected devices
adb devices

# Install APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Or for release
adb install ThaiTV-1.0.0.apk
```

### Via File Manager

1. Transfer APK to your Android device
2. Open file manager on the device
3. Locate the APK file
4. Tap to install
5. Accept permissions if prompted

## Features

✅ **Add M3U Playlists** - Import from URL or file  
✅ **Manage Channels** - View, search, favorite  
✅ **Play Videos** - Smooth playback with ExoPlayer  
✅ **Local Database** - Room database for offline access  
✅ **Material Design** - Modern UI/UX  
✅ **Full Screen Player** - Landscape orientation  

## Troubleshooting

### Gradle Sync Failed

- Check Android SDK API levels are installed (24-34)
- Update Gradle: `File` → `Settings` → `Build, Execution, Deployment` → `Gradle`
- Clear cache: `File` → `Invalidate Caches`

### Build Error

```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

### APK Installation Failed

- Check device has sufficient storage (at least 50MB free)
- Enable installation from unknown sources: `Settings` → `Security`
- Uninstall previous version first

### App Crashes on Launch

- Check logcat in Android Studio: `View` → `Tool Windows` → `Logcat`
- Grant permissions: `Settings` → `Apps` → `ThaiTV` → `Permissions`
- Ensure Internet permission is enabled

## Performance Optimization

### Minify & Shrink Resources

Automatically enabled for release builds. Check `app/build.gradle`:

```gradle
release {
    minifyEnabled true
    shrinkResources true
    proguardFiles ...
}
```

### Reduce APK Size

1. Use WebP format for images instead of PNG
2. Remove unused dependencies
3. Enable ProGuard/R8 minification
4. Split APK by ABI (CPU architecture)

## Publishing to Google Play

1. Create Google Play Developer account ($25 one-time)
2. Prepare signed release APK
3. Go to [Google Play Console](https://play.google.com/console)
4. Create new app
5. Upload APK with metadata (screenshots, description)
6. Set pricing and content rating
7. Submit for review

## Additional Resources

- [Android Developer Documentation](https://developer.android.com/docs)
- [Gradle Build System](https://developer.android.com/build)
- [Android Studio Setup](https://developer.android.com/studio/intro)
- [ExoPlayer Guide](https://exoplayer.dev/)
- [Room Database Documentation](https://developer.android.com/training/data-storage/room)

## Support

For issues or questions:
- Check GitHub Issues: https://github.com/thaikay/tivi/issues
- Create a new issue with detailed description
- Include error logs and device information

---

**Happy Building! 🎉**
