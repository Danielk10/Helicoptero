package com.diamon.pantalla;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaMultijugador extends Pantalla {

	private TextButton atras;

	private Label titulo;

	private TextButton cliente;

	private TextButton servidor;

	private boolean[] clic;

	private boolean[] desClic;

	public PantallaMultijugador(Juego juego) {
		super(juego);

	}

	@Override
	public void mostrar() {

		clic = new boolean[3];

		desClic = new boolean[3];

		for (int i = 0; i < clic.length; i++) {

			clic[i] = false;

		}

		for (int i = 0; i < desClic.length; i++) {

			desClic[i] = true;

		}

		atras = new TextButton("Atrás", recurso.get("ui/uieli.json", Skin.class));

		atras.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		atras.setPosition(32, 32);

		titulo = new Label("Opciones de Multijugador", recurso.get("ui/uieli.json", Skin.class));

		titulo.setPosition((Juego.ANCHO_PANTALLA / 4) - 14, Juego.ALTO_PANTALLA - 64);

		cliente = new TextButton("Cliente", recurso.get("ui/uieli.json", Skin.class));

		cliente.setSize(213, 32);

		cliente.setPosition(Juego.ANCHO_PANTALLA / 3, 240);

		servidor = new TextButton("Servidor", recurso.get("ui/uieli.json", Skin.class));

		servidor.setSize(213, 32);

		servidor.setPosition(Juego.ANCHO_PANTALLA / 3, 192);

		nivelMenu.addActor(titulo);

		anadirBotones(true);

	}

	private void anadirBotones(boolean anadir) {

		if (anadir) {

			nivelMenu.addActor(cliente);

			nivelMenu.addActor(servidor);

			nivelMenu.addActor(atras);

		} else {

			cliente.remove();

			servidor.remove();

			atras.remove();

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

		if (!cliente.isOver())

		{

			if (desClic[1])

			{

				clic[1] = true;

				desClic[1] = false;
			}

		}

		if (!servidor.isOver())

		{

			if (desClic[2])

			{

				clic[2] = true;

				desClic[2] = false;
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

				juego.setScreen(new PantallaSeleccion(juego));

				super.clicked(event, x, y);
			}

		});

		cliente.addListener(new ClickListener() {

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

				super.clicked(event, x, y);
			}

		});

		servidor.addListener(new ClickListener() {

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
