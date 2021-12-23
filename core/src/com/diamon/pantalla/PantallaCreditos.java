package com.diamon.pantalla;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaCreditos extends Pantalla {

	private TextButton aceptar;

	private Label titulo;

	private Label programador;

	private Label nombreProgramador;

	private Label diseno;

	private Label nombreDiseno;

	private Label version;

	private boolean[] clic;

	private boolean[] desClic;

	private Music musica;

	public PantallaCreditos(Juego juego) {
		super(juego);

	}

	@Override
	public void mostrar() {

		clic = new boolean[1];

		desClic = new boolean[1];

		for (int i = 0; i < clic.length; i++) {

			clic[i] = false;

		}

		for (int i = 0; i < desClic.length; i++) {

			desClic[i] = true;

		}

		musica = recurso.get("audio/Battle in the winter.ogg", Music.class);

		if (dato.isSonido())

		{

			if (!musica.isPlaying()) {

				musica.setLooping(true);

				musica.play();

			}

		}

		titulo = new Label("Créditos", recurso.get("ui/uieli.json", Skin.class));

		titulo.setPosition((Juego.ANCHO_PANTALLA / 2) - 64, Juego.ALTO_PANTALLA - 64);

		aceptar = new TextButton("Aceptar", recurso.get("ui/uieli.json", Skin.class));

		aceptar.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		aceptar.setPosition(Juego.ANCHO_PANTALLA / 2 - 40, 32);

		programador = new Label("Programador:", recurso.get("ui/uiskin.json", Skin.class));

		programador.setPosition(Juego.ANCHO_PANTALLA / 4, 288);

		nombreProgramador = new Label("Daniel Diamon", recurso.get("ui/uiskin.json", Skin.class));

		nombreProgramador.setPosition(Juego.ANCHO_PANTALLA / 2, 288);

		diseno = new Label("Disenador Grafico:", recurso.get("ui/uiskin.json", Skin.class));

		diseno.setPosition(Juego.ANCHO_PANTALLA / 4, 240);

		nombreDiseno = new Label("Jesus Mendez", recurso.get("ui/uiskin.json", Skin.class));

		nombreDiseno.setPosition(Juego.ANCHO_PANTALLA / 2, 240);

		version = new Label("Version 1.0.0", recurso.get("ui/uiskin.json", Skin.class));

		version.setPosition(608 - version.getWidth(), 32);

		nivelMenu.addActor(titulo);

		nivelMenu.addActor(aceptar);

		nivelMenu.addActor(version);

		nivelMenu.addActor(programador);

		nivelMenu.addActor(nombreProgramador);

		nivelMenu.addActor(diseno);

		nivelMenu.addActor(nombreDiseno);

	}

	private void botonesDeseleccionados() {

		if (!aceptar.isOver())

		{

			if (desClic[0])

			{

				clic[0] = true;

				desClic[0] = false;
			}

		}

	}

	@Override
	public void eventos() {

		aceptar.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[0])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[0] = false;

					desClic[0] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				juego.setScreen(new PantallaMenu(juego));

				super.clicked(event, x, y);
			}

		});

	}

	@Override
	public void colisiones() {

	}

	@Override
	public void actualizar(float delta) {

		botonesDeseleccionados();

	}

	@Override
	public void dibujar(Batch pincel, float delta) {

	}

	@Override
	public void guardarDatos() {

	}

	@Override
	public void liberarRecursos() {

	}

}