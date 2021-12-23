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

public class PantallaPuntuaciones extends Pantalla {

	private TextButton atras;

	private TextButton ponerACero;

	private Label titulo;

	private Label[] textosPunto;

	private Label[] numeroNivel;

	private Label[] estado;

	private Label[] punto;

	private boolean[] clic;

	private boolean[] desClic;

	private Music musica;

	public PantallaPuntuaciones(Juego juego) {
		super(juego);

	}

	@Override
	public void mostrar() {

		clic = new boolean[2];

		desClic = new boolean[2];

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

		titulo = new Label("Puntuaciones", recurso.get("ui/uieli.json", Skin.class));

		titulo.setPosition((Juego.ANCHO_PANTALLA / 3) + 20, Juego.ALTO_PANTALLA - 64);

		atras = new TextButton("Atrás", recurso.get("ui/uieli.json", Skin.class));

		atras.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		atras.setPosition(32, 32);

		ponerACero = new TextButton("Poner a Cero", recurso.get("ui/uieli.json", Skin.class));

		ponerACero.setSize(150, 32);

		ponerACero.setPosition(608 - ponerACero.getWidth(), 32);

		nivelMenu.addActor(titulo);

		nivelMenu.addActor(atras);

		nivelMenu.addActor(ponerACero);

		textosPunto = new Label[dato.getPuntuaciones().length];

		punto = new Label[dato.getPuntuaciones().length];

		numeroNivel = new Label[dato.getPuntuaciones().length];

		estado = new Label[dato.getPuntuaciones().length];

		int o = 360;

		for (int i = 0; i < textosPunto.length; i++)

		{

			textosPunto[i] = new Label("Puntos ", recurso.get("ui/uiskin.json", Skin.class));

			textosPunto[i].setPosition(Juego.ANCHO_PANTALLA / 5, o);

			numeroNivel[i] = new Label("" + dato.getNumeroNivelPuntuaciones()[i],
					recurso.get("ui/uiskin.json", Skin.class));

			numeroNivel[i].setPosition(Juego.ANCHO_PANTALLA / 3 + 48, o);

			estado[i] = new Label("" + dato.getEstadoPuntuaciones()[i], recurso.get("ui/uiskin.json", Skin.class));

			estado[i].setPosition(Juego.ANCHO_PANTALLA / 2 + 64, o);

			punto[i] = new Label("" + dato.getPuntuaciones()[i], recurso.get("ui/uiskin.json", Skin.class));

			punto[i].setPosition(Juego.ANCHO_PANTALLA / 2 + 160, o);

			nivelMenu.addActor(textosPunto[i]);

			nivelMenu.addActor(numeroNivel[i]);

			nivelMenu.addActor(estado[i]);

			nivelMenu.addActor(punto[i]);

			o -= 30;

		}

	}

	private void botonesDeseleccionados() {

		if (!atras.isOver())

		{

			if (desClic[0])

			{

				clic[0] = true;

				desClic[0] = false;
			}

		}

		if (!ponerACero.isOver())

		{

			if (desClic[1])

			{

				clic[1] = true;

				desClic[1] = false;
			}

		}

	}

	@Override
	public void eventos() {

		atras.addListener(new ClickListener() {

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

		ponerACero.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[1])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[1] = false;

					desClic[1] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				for (int i = 0; i < dato.getPuntuaciones().length; i++)

				{

					dato.getPuntuaciones()[i] = 0;

					dato.getNumeroNivelPuntuaciones()[i] = "----";

					dato.getEstadoPuntuaciones()[i] = "----";

					textosPunto[i].setText("Puntos ");

					numeroNivel[i].setText("" + dato.getNumeroNivelPuntuaciones()[i]);

					estado[i].setText("" + dato.getEstadoPuntuaciones()[i]);

					punto[i].setText("" + dato.getPuntuaciones()[i]);

				}

				configuracion.escribirDatos(dato);

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