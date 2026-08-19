# Notas de Lanzamiento - Helicoptero v1.2.6 🚁

## 📋 Novedades y Mejoras Principales

### ⚡ Entorno y Compilación
- **Aislamiento Total en `/tmp`:** Redirección completa de la instalación del Android SDK (`/tmp/android-sdk`), del cache global de Gradle (`/tmp/.gradle`), cache local de proyecto (`/tmp/helicoptero/.gradle`) y directorio de build a `/tmp/helicoptero/` para evitar consumir cuota en `$HOME`.
- **Automatización de SDK:** Inclusión de `setup-sdk.sh` para aprovisionamiento rápido y autónomo de herramientas Android (Build-Tools 37.0.0, Platform Android 37, NDK y CMake).
- **Publicación Automatizada:** Integración del script `upload_play_store.py` y guía `GUIA_PUBLICACION_PLAY_STORE.md` para lanzamientos automatizados a Google Play Store.

### 📦 Dependencias y Compatibilidad
- Actualizadas las dependencias de Google Play Services Ads a la versión `25.3.0`.
- Actualizadas las dependencias de Microsoft AppCenter SDK a `5.0.6`.
- Compatibilidad con Java 17 y compilación dirigida a Android Target SDK 37.
- ProGuard/R8 habilitado y optimizado con preservación de librerías nativas de LibGDX (`armeabi-v7a`, `arm64-v8a`, `x86`, `x86_64`).

---

## 🛠️ Artefactos de la Versión

- **APK Release:** `Helicoptero_v1.2.6.apk`
- **AAB Release:** `android-release.aab`
- **Version Code:** `15`
- **Version Name:** `1.2.6`
