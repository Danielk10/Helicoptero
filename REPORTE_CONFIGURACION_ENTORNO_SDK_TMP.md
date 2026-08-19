# Reporte de Configuración del Entorno de Compilación en `/tmp` 🚁

Este documento detalla la reconfiguración integral del entorno de desarrollo y compilación del proyecto **Helicoptero**, migrando todas las rutas de instalación del SDK de Android, NDK, cachés de Gradle y artefactos de compilación hacia el directorio temporal `/tmp`.

---

## 🎯 Objetivo

Evitar el agotamiento del espacio en disco en el directorio persistente `/home` (entornos Cloud Shell o sistemas con cuotas de almacenamiento limitadas), trasladando todas las operaciones pesadas, herramientas y descargas a la memoria volátil/temporal `/tmp`.

---

## 🏗️ Resumen de Configuraciones Implementadas

### 1. Aislamiento del SDK y Herramientas Android
* **Directorio SDK:** `/tmp/android-sdk`
* **Directorio de configuración/licencias de Android (`ANDROID_USER_HOME`):** `/tmp/.android`
* **Directorio de preferencias (`ANDROID_PREFS_ROOT`):** `/tmp`
* **Script de aprovisionamiento:** [`setup-sdk.sh`](./setup-sdk.sh)
  * Descarga las herramientas de línea de comandos (`cmdline-tools`).
  * Instala `platform-tools`, `platforms;android-23`, `platforms;android-37.0`, `build-tools;37.0.0`, `cmake;4.1.2` y `ndk;30.0.14904198` directamente en `/tmp/android-sdk`.
  * Genera [`local.properties`](./local.properties) con `sdk.dir=/tmp/android-sdk`.

### 2. Redirección del Cache de Gradle
* **Gradle User Home (`GRADLE_USER_HOME`):** `/tmp/.gradle` (evita acumular distribuciones y descargas de dependencias en `~/.gradle`).
* **Cache de Proyecto (`--project-cache-dir`):** `/tmp/helicoptero/.gradle` (evita la creación del directorio `.gradle` en la raíz del repositorio).
* **Parámetros en [`gradle.properties`](./gradle.properties):**
  ```properties
  org.gradle.project.cache.dir=/tmp/helicoptero/.gradle
  systemProp.gradle.user.home=/tmp/.gradle
  systemProp.android.user.home=/tmp/.android
  ```

### 3. Redirección de los Artefactos de Salida (Build Outputs)
* **Directorio de Build Raíz y Submódulos:** `/tmp/helicoptero/${project.name}`
* **Módulo Android (`android`):** `/tmp/helicoptero`
* **Ubicación del APK Release:** `/tmp/helicoptero/outputs/apk/release/android-release.apk`
* **Ubicación del AAB Release:** `/tmp/helicoptero/outputs/bundle/release/android-release.aab`
* **Ubicación del APK Debug:** `/tmp/helicoptero/outputs/apk/debug/android-debug.apk`

---

## 📦 Dependencias de Anuncios y Telemetría Actualizadas

Se han alineado las versiones con el estándar de **Flash-EEPROM-Tool**:
* **Google Play Services Ads:** `25.3.0` (`com.google.android.gms:play-services-ads:25.3.0`)
* **Microsoft AppCenter Analytics & Crashes:** `5.0.6` (`com.microsoft.appcenter:appcenter-analytics:5.0.6`, `appcenter-crashes:5.0.6`)
* **Android Gradle Plugin (AGP):** `9.2.1`
* **Target SDK / Compile SDK:** `37` (Android 15+)
* **Min SDK:** `23` (Android 6.0+)
* **Java Compatibility:** `Java 17`

---

## 🚀 Comandos Rápidos de Compilación

```bash
# 1. Configurar SDK
bash setup-sdk.sh

# 2. Compilar APK y AAB de producción
./gradlew :android:bundleRelease :android:assembleRelease

# 3. Limpiar salidas generadas en /tmp
rm -rf /tmp/helicoptero/outputs/
```
