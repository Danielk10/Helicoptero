package com.diamon.terminos;

import com.diamon.pantalla.PantallaCompleta;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.content.*;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Terminos extends Activity {

	private WebView pagina;

	private PantallaCompleta pantallaCompleta;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		pantallaCompleta = new PantallaCompleta(this);

		pantallaCompleta.pantallaCompleta();

		pantallaCompleta.ocultarBotonesVirtuales();

		pagina = new WebView(this);

		RelativeLayout mainLayout = new RelativeLayout(this);

		FrameLayout frame = new FrameLayout(this);

		RelativeLayout.LayoutParams mrecParameters = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		mrecParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mrecParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		mainLayout.addView(pagina, mrecParameters);

		frame.addView(mainLayout, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));

		setContentView(frame);

		pagina.getSettings().setJavaScriptEnabled(true);

		pagina.loadUrl("http://www.e-droid.net/privacy.php?ida=1454194&idl=es");

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {

			pantallaCompleta.ocultarBotonesVirtuales();
		}

	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		pantallaCompleta.ocultarBotonesVirtuales();

		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void onResume() {

		super.onResume();

	}

	@Override
	public void onPause() {

		super.onPause();

	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();

	}

}
