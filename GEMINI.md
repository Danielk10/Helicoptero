# Instrucciones de Compilación y SDK

Este documento describe cómo instalar el SDK de Android y compilar el proyecto **Helicoptero**.

## 1. Instalación del SDK

El SDK de Android necesario para compilar este proyecto se instala automáticamente ejecutando el script proporcionado:

```bash
bash setup-sdk.sh
```

- **Ubicación del SDK:** Todas las descargas y herramientas del SDK se instalan temporalmente en el directorio `/tmp/android-sdk` para evitar ocupar espacio en `/home`.
- **Cache de Gradle:** El directorio de cache global de Gradle se redirige a `/tmp/.gradle` y la cache local del proyecto a `/tmp/helicoptero/.gradle`.
- **Configuración de Android User Home:** Redirigido a `/tmp/.android`.

## 2. Compilación

Una vez que el script `setup-sdk.sh` termine y el SDK esté listo, el proyecto se puede compilar ejecutando Gradle:

```bash
# Compilar APK Debug
./gradlew :android:assembleDebug

# Compilar APK Release (firmado)
./gradlew :android:assembleRelease

# Compilar AAB (App Bundle) Release para Google Play Store
./gradlew :android:bundleRelease
```

## 3. Ubicación de los Archivos Generados (APK y AAB)

Después de una compilación exitosa, los archivos generados se encontrarán en las siguientes rutas absolutas en `/tmp` fuera del proyecto para mantener el espacio de trabajo limpio:

- **APK Release:**
  ```
  /tmp/helicoptero/outputs/apk/release/android-release.apk
  ```
- **AAB Release (para Google Play Store):**
  ```
  /tmp/helicoptero/outputs/bundle/release/android-release.aab
  ```
- **APK Debug:**
  ```
  /tmp/helicoptero/outputs/apk/debug/android-debug.apk
  ```

## 4. Limpieza de Artefactos de Compilación

Para limpiar las salidas y evitar consumo de memoria temporal:

```bash
rm -rf /tmp/helicoptero/outputs/
```
