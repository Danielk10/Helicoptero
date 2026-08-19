# Notas de Lanzamiento - Helicoptero v1.2.6 🚁

## 📋 Novedades y Correcciones

### 🎮 Corrección de Icono de la Aplicación
- **Eliminación del icono por defecto de libGDX:** Se removió el archivo vectorial y recursos que causaban que en Android 8.0+ se visualizara el logo genérico de libGDX.
- **Generación de Iconos Adaptativos:** Se crearon los iconos de primer plano (`ic_launcher_foreground.png`) y fondos adaptativos con el logo oficial del juego para todas las densidades de pantalla (`mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`).

### 🛡️ Integridad de Base de Datos y Assets (.dat)
- **Desactivación de Ofuscación:** Se configuró `minifyEnabled false` en el tipo de compilación release para evitar que R8/ProGuard corrompa la lectura, deserialización y acceso a los archivos de base de datos de assets (`.dat`/`.data`).

### 📢 Publicidad AdMob en Producción
- **Inicialización de Mobile Ads SDK:** Se integró la llamada explícita `MobileAds.initialize(...)` en el flujo de arranque.
- **Verificación de IDs de Producción:** Comprobación del App ID (`ca-app-pub-5141499161332805~5712767508`), Banner Unit ID (`ca-app-pub-5141499161332805/3936571775`) e Interstitial Unit ID (`ca-app-pub-5141499161332805/2847166323`).

### ⚡ Entorno y Compilación en `/tmp`
- **Aislamiento Total en `/tmp`:** Redirección de la instalación del Android SDK (`/tmp/android-sdk`), del cache global de Gradle (`/tmp/.gradle`), cache local de proyecto (`/tmp/helicoptero/.gradle`) y directorio de build a `/tmp/helicoptero/` para evitar ocupar cuota en `$HOME`.
- **Automatización de SDK y Publicación:** Script autónomo `setup-sdk.sh` y script de subida a Play Store `upload_play_store.py`.

---

## 🛠️ Artefactos de la Versión

- **APK Release:** `Helicoptero_v1.2.6.apk`
- **AAB Release:** `android-release.aab`
- **Version Code:** `15`
- **Version Name:** `1.2.6`
