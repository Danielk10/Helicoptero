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
* **Imagen nativa (GraalVM):** `/tmp/helicoptero/lwjgl3/native/nativeCompile/Helicoptero`

---

## 📦 Dependencias Android Actualizadas

Versiones actuales del proyecto:

* **Android Gradle Plugin (AGP):** `9.5.1`
* **Gradle:** `9.9.1`
* **Google Play Services Ads:** `25.5.0` (`com.google.android.gms:play-services-ads:25.5.0`)
* **User Messaging Platform (UMP):** `4.0.0`
* **Microsoft AppCenter Analytics & Crashes:** `5.0.6`
* **Target SDK / Compile SDK:** `37`
* **Min SDK:** `24`
* **Build Tools:** `37.0.0`
* **Java Compatibility:** `Java 17`
* **libGDX:** `1.14.2` / **LWJGL:** `3.4.3`

> **Nota:** Google Play Billing se mantiene intencionalmente en `8.3.0` en los proyectos que lo usan (C-PIC, PIC-k150-Programing y Flash-EEPROM-Tool). **Helicoptero no integra Billing.**

---

## ☕ Build Nativo con GraalVM (opcional)

El build nativo está **desactivado por defecto** (`enableGraalNative=false` en [`gradle.properties`](./gradle.properties)); solo se activa para generar el ejecutable de escritorio.

### Dependencias del build nativo
* **construo:** `2.3.0` (empaquetado/jlink multiplataforma, resuelto desde el Gradle Plugin Portal).
* **GraalVM native buildtools:** `1.1.18`.
* **gdx-svmhelper:** `2.0.1` (última versión publicada).

### Requisito de versión de GraalVM

⚠️ **Se debe usar GraalVM CE 23.1 para JDK 21** (en este entorno, `/tmp/graalvm21`).

* El build **no funciona con GraalVM 25**: `gdx-svmhelper 2.0.1` usa `org.graalvm.nativeimage.impl.ConfigurationCondition`, una API interna eliminada en las versiones nuevas, y `native-image` aborta con `NoClassDefFoundError`. No existe todavía una versión más reciente del helper.
* El `requiredVersion = '23.0'` de [`nativeimage.gradle`](./lwjgl3/nativeimage.gradle) sigue siendo válido con GraalVM 23.1.
* native-build-tools 1.x activa por defecto el **repositorio de metadatos de alcanzabilidad**, que exige GraalVM ≥ 24.2. Por eso [`nativeimage.gradle`](./lwjgl3/nativeimage.gradle) lo desactiva explícitamente:

  ```groovy
  metadataRepository {
    enabled.set(false)
  }
  ```

### Instalación de GraalVM 23.1 (JDK 21)

```bash
curl -fL -o /tmp/graalvm21.tar.gz \
  "https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-21.0.2/graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz"
mkdir -p /tmp/graalvm21
tar -xzf /tmp/graalvm21.tar.gz -C /tmp/graalvm21 --strip-components=1
rm -f /tmp/graalvm21.tar.gz
/tmp/graalvm21/bin/native-image --version
```

### Compilar la imagen nativa

```bash
# 1. Activar temporalmente el modo nativo
sed -i 's/^enableGraalNative=false/enableGraalNative=true/' gradle.properties

# 2. Compilar con GraalVM 23.1
JAVA_HOME=/tmp/graalvm21 \
ANDROID_HOME=/tmp/android-sdk ANDROID_SDK_ROOT=/tmp/android-sdk \
GRADLE_USER_HOME=/tmp/.gradle ANDROID_USER_HOME=/tmp/.android \
./gradlew --no-daemon :lwjgl3:nativeCompile

# 3. Restaurar el valor original
sed -i 's/^enableGraalNative=true/enableGraalNative=false/' gradle.properties
```

> El ejecutable resultante requiere entorno gráfico (OpenGL/GLFW); en una sesión headless falla en `glfwInit`, lo cual es esperado.

**Validado:** `:android:assembleDebug --rerun-tasks` y `:lwjgl3:nativeCompile` con `construo 2.3.0` + native buildtools `1.1.18` sobre GraalVM 23.1.2.

---

## 🚀 Comandos Rápidos de Compilación

```bash
# 1. Configurar SDK
bash setup-sdk.sh

# 2. Compilar APK y AAB de producción
./gradlew :android:bundleRelease :android:assembleRelease

# 3. Compilar APK debug (verificación rápida)
./gradlew :android:assembleDebug

# 4. Limpiar salidas generadas en /tmp
rm -rf /tmp/helicoptero/outputs/
```

---

## 🧹 Notas de Mantenimiento

* `/tmp` es **volátil**: tras reiniciar hay que volver a ejecutar `setup-sdk.sh` y reinstalar GraalVM si se quiere repetir el build nativo.
* Los artefactos de compilación (`/tmp/helicoptero`) y las cachés (`/tmp/.gradle`, `/tmp/.android`) se pueden borrar sin afectar al repositorio.
* No se deben versionar `local.properties`, `keystore.properties` ni `token.json`.
