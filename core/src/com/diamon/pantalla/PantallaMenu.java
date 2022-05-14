package com.diamon.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Interpolation.Swing;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaMenu extends Pantalla {

	private TextButton jugar;

	private TextButton opciones;

	private TextButton puntuaciones;

	private TextButton creditos;

	private TextButton salir;

	private Image titulo;

	private Music musica;

	private boolean[] clic;

	private boolean[] desClic;

	public PantallaMenu(Juego juego) {
		super(juego);

	}

	@SuppressWarnings("static-access")
	@Override
	public void mostrar() {

		clic = new boolean[5];

		desClic = new boolean[5];

		for (int i = 0; i < clic.length; i++) {

			clic[i] = false;

		}

		for (int i = 0; i < desClic.length; i++) {

			desClic[i] = true;

		}

		if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

			Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("textura/cursor.png")), 0, 0));

		}

		musica = recurso.get("audio/Battle in the winter.ogg", Music.class);

		if (dato.isSonido())

		{

			if (!musica.isPlaying()) {

				musica.setLooping(true);

				musica.play();

			}

		}

		jugar = new TextButton("Jugar", recurso.get("ui/uieli.json", Skin.class));

		jugar.setSize(213, 32);

		jugar.setPosition(512, 240);

		opciones = new TextButton("Opciones", recurso.get("ui/uieli.json", Skin.class));

		opciones.setSize(213.0F, 32);

		opciones.setPosition(512, 192);

		puntuaciones = new TextButton("Puntuaciones", recurso.get("ui/uieli.json", Skin.class));

		puntuaciones.setSize(213.0F, 32);

		puntuaciones.setPosition(512, 144);

		creditos = new TextButton("Creditos", recurso.get("ui/uieli.json", Skin.class));

		creditos.setSize(213, 32);

		creditos.setPosition(512.0F, 96.0F);

		salir = new TextButton("Salir", recurso.get("ui/uieli.json", Skin.class));

		salir.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		salir.setPosition(32, 32);

		titulo = new Image(recurso.get("textura/titulo.png", Texture.class));

		titulo.setSize(640, 160);

		titulo.setPosition(Juego.ANCHO_PANTALLA / 2 - 20, 320);

		nivelMenu.addActor(titulo);

		nivelMenu.addActor(jugar);

		nivelMenu.addActor(opciones);

		nivelMenu.addActor(puntuaciones);

		nivelMenu.addActor(creditos);

		nivelMenu.addActor(salir);

		float moveDuration = 1;

		Interpolation.Swing swing = (Swing) Interpolation.swing;

		float delayOptionsButton = 0.25f;

		float moveX = -300;

		float moveY = 0;

		jugar.addAction(Actions.moveBy(moveX, moveY, moveDuration, swing));

		opciones.addAction(
				Actions.sequence(Actions.delay(delayOptionsButton), Actions.moveBy(moveX, moveY, moveDuration, swing)));

		puntuaciones.addAction(Actions.moveBy(moveX, moveY, moveDuration, swing));

		creditos.addAction(
				Actions.sequence(Actions.delay(delayOptionsButton), Actions.moveBy(moveX, moveY, moveDuration, swing)));

		titulo.addAction(Actions.moveBy(moveX, moveY, moveDuration, swing));

	}

	private void botonesDeseleccionados() {

		if (!jugar.isOver())

		{

			if (desClic[0])

			{

				clic[0] = true;

				desClic[0] = false;
			}

		}

		if (!opciones.isOver())

		{

			if (desClic[1])

			{

				clic[1] = true;

				desClic[1] = false;
			}

		}

		if (!puntuaciones.isOver())

		{

			if (desClic[2])

			{

				clic[2] = true;

				desClic[2] = false;
			}

		}

		if (!creditos.isOver())

		{

			if (desClic[3])

			{

				clic[3] = true;

				desClic[3] = false;
			}

		}

		if (!salir.isOver())

		{

			if (desClic[4])

			{

				clic[4] = true;

				desClic[4] = false;
			}

		}

	}

	@Override
	public void eventos() {

		jugar.addListener(new ClickListener() {

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

				juego.setScreen(new PantallaSeleccion(juego));

				super.clicked(event, x, y);
			}

		});

		opciones.addListener(new ClickListener() {

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

				juego.setScreen(new PantallaOpciones(juego));

				super.clicked(event, x, y);
			}
		});

		puntuaciones.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[2])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[2] = false;

					desClic[2] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				juego.setScreen(new PantallaPuntuaciones(juego));

				super.clicked(event, x, y);
			}
		});

		creditos.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[3])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					desClic[3] = true;

					clic[3] = false;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				juego.setScreen(new PantallaCreditos(juego));

				super.clicked(event, x, y);
			}
		});

		salir.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[4])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[4] = false;

					desClic[4] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				Gdx.app.exit();

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
