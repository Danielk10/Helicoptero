package com.diamon.publicidad;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.diamon.helicoptero.Publicidad;
import com.diamon.helicoptero.R;
import com.diamon.helicoptero.android.AndroidLauncher;
import com.diamon.terminos.Terminos;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MostrarPublicidad implements Publicidad {

    private static final String AD_UNIT_ID = "ca-app-pub-5141499161332805/3936571775";
    private static final String INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-5141499161332805/2847166323";

    private final AndroidLauncher actividad;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private boolean interstitialLoading = false;
    private Button privacyButton;

    public MostrarPublicidad(AndroidLauncher actividad) {
        this.actividad = actividad;

        actividad.runOnUiThread(this::crearBanner);

        // No se inicializa MobileAds ni se pide ningún anuncio hasta que UMP
        // resuelva el consentimiento de privacidad.
        ConsentManager.requestConsent(actividad, canRequestAds -> {
            if (actividad.isFinishing() || actividad.isDestroyed()) {
                return;
            }
            if (canRequestAds) {
                MobileAds.initialize(actividad, initializationStatus -> {
                    if (actividad.isFinishing() || actividad.isDestroyed()) {
                        return;
                    }
                    actividad.runOnUiThread(this::cargarInterstitial);
                });
            }
            actualizarBotonPrivacidad();
        });
    }

    private void crearBanner() {
        if (adView != null) {
            return;
        }
        adView = new AdView(actividad);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setVisibility(View.GONE); // El juego no muestra banner.
    }

    private void cargarInterstitial() {
        if (!ConsentManager.canRequestAds() || interstitialLoading || interstitialAd != null) {
            return;
        }
        interstitialLoading = true;
        InterstitialAd.load(
                actividad,
                INTERSTITIAL_AD_UNIT_ID,
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialLoading = false;
                        interstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        interstitialLoading = false;
                        interstitialAd = null;
                    }
                });
    }

    /**
     * Añade un botón discreto para abrir las opciones de privacidad. Solo se
     * muestra cuando UMP indica que es obligatorio ofrecer ese punto de
     * entrada (EEE / estados regulados de EE. UU.).
     */
    public void instalarBotonPrivacidad(ViewGroup parent) {
        if (parent == null || privacyButton != null) {
            return;
        }
        privacyButton = new Button(actividad);
        privacyButton.setText(actividad.getString(R.string.menu_privacy_options));
        privacyButton.setTextSize(11f);
        privacyButton.setAlpha(0.75f);
        privacyButton.setContentDescription(actividad.getString(R.string.menu_privacy_options));
        privacyButton.setVisibility(View.GONE);
        privacyButton.setOnClickListener(
                v -> ConsentManager.showPrivacyOptionsForm(
                        actividad,
                        error -> {
                            actualizarBotonPrivacidad();
                            onConsentChanged();
                        }));

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.START;
        parent.addView(privacyButton, params);
        actualizarBotonPrivacidad();
    }

    private void actualizarBotonPrivacidad() {
        if (privacyButton == null) {
            return;
        }
        boolean visible = ConsentManager.isPrivacyOptionsRequired();
        actividad.runOnUiThread(
                () -> privacyButton.setVisibility(visible ? View.VISIBLE : View.GONE));
    }

    /** Reaplica el estado tras cambiar las opciones de privacidad. */
    public void onConsentChanged() {
        boolean canRequestAds = ConsentManager.canRequestAds();
        if (canRequestAds) {
            MobileAds.initialize(actividad, initializationStatus -> cargarInterstitial());
        } else {
            if (interstitialAd != null) {
                interstitialAd = null;
            }
            if (adView != null) {
                adView.setVisibility(View.GONE);
            }
        }
        actualizarBotonPrivacidad();
    }

    @Override
    public void mostrarBanner() {
        actividad.runOnUiThread(
                () -> {
                    if (adView != null && ConsentManager.canRequestAds()) {
                        // El juego mantiene el banner oculto por diseño.
                        adView.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void ocultarBanner() {
        actividad.runOnUiThread(
                () -> {
                    if (adView != null) {
                        adView.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void mostrarInterstitial() {
        if (!ConsentManager.canRequestAds()) {
            return;
        }
        actividad.runOnUiThread(
                () -> {
                    if (interstitialAd != null) {
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        interstitialAd = null;
                                        cargarInterstitial();
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(
                                            @NonNull AdError adError) {
                                        interstitialAd = null;
                                        cargarInterstitial();
                                    }
                                });
                        interstitialAd.show(actividad);
                        interstitialAd = null;
                    } else {
                        cargarInterstitial();
                    }
                });
    }

    public AdView getBanner() {
        return adView;
    }

    @Override
    public void botonAtrasInterstitial() {}

    @Override
    public void cargarBanner() {
        if (!ConsentManager.canRequestAds() || adView == null) {
            return;
        }
        actividad.runOnUiThread(
                () -> adView.loadAd(new AdRequest.Builder().build()));
    }

    @Override
    public void iniciarActividad() {
        Intent nuevaActividad = new Intent(actividad, Terminos.class);
        actividad.startActivity(nuevaActividad);
    }
}
