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

public class PantallaSeleccion extends Pantalla {

	private TextButton atrasMenu;

	private Label titulo;

	private TextButton nuevaPartida;

	private TextButton continuarJuego;

	private boolean[] clic;

	private boolean[] desClic;

	public PantallaSeleccion(Juego juego) {
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

		atrasMenu = new TextButton("Atrás", recurso.get("ui/uieli.json", Skin.class));

		atrasMenu.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		atrasMenu.setPosition(32, 32);

		titulo = new Label("Opciones de Partida", recurso.get("ui/uieli.json", Skin.class));

		titulo.setPosition((Juego.ANCHO_PANTALLA / 3) - 27, Juego.ALTO_PANTALLA - 64);

		nuevaPartida = new TextButton("Nueva Partida", recurso.get("ui/uieli.json", Skin.class));

		nuevaPartida.setSize(213, 32);

		nuevaPartida.setPosition(Juego.ANCHO_PANTALLA / 3, 240);

		continuarJuego = new TextButton("Continuar Partida", recurso.get("ui/uieli.json", Skin.class));

		continuarJuego.setSize(213, 32);

		if (!dato.isContinuar())

		{

			nuevaPartida.setPosition(Juego.ANCHO_PANTALLA / 3, 240);

			continuarJuego.setPosition(Juego.ANCHO_PANTALLA / 3, 192);

		} else {

			nuevaPartida.setPosition(Juego.ANCHO_PANTALLA / 3, 192);

			continuarJuego.setPosition(Juego.ANCHO_PANTALLA / 3, 240);

		}

		nivelMenu.addActor(titulo);

		nivelMenu.addActor(atrasMenu);

		anadirBotonesPartida(true);

	}

	private void botonesDeseleccionados() {

		if (!atrasMenu.isOver())

		{

			if (desClic[0])

			{

				clic[0] = true;

				desClic[0] = false;
			}

		}

		if (!nuevaPartida.isOver())

		{

			if (desClic[1])

			{

				clic[1] = true;

				desClic[1] = false;
			}

		}

		if (!continuarJuego.isOver())

		{

			if (desClic[2])

			{

				clic[2] = true;

				desClic[2] = false;
			}

		}

	}

	private void anadirBotonesPartida(boolean anadir) {

		if (anadir) {

			nivelMenu.addActor(nuevaPartida);

			if (dato.isContinuar())

			{

				nivelMenu.addActor(continuarJuego);

			}

			nivelMenu.addActor(atrasMenu);

		} else {

			nuevaPartida.remove();

			if (dato.isContinuar())

			{

				continuarJuego.remove();

			}

			atrasMenu.remove();

		}

	}

	@Override
	public void eventos() {

		atrasMenu.addListener(new ClickListener() {

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

		nuevaPartida.addListener(new ClickListener() {

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

				if (dato.isSonido())

				{

					recurso.get("audio/Battle in the winter.ogg", Music.class).stop();

				}

				if (!dato.isContinuar())

				{

					dato.setContinuar(true);

				}

				dato.setPartida(true);

				dato.setPuntos(0);

				dato.setVidas(3);

				dato.setMisiles(10);

				dato.setBombas(10);

				dato.setHelicoptero(1);

				dato.setNumeroSatelite(0);

				juego.setScreen(new PantallaJuego(juego));

				super.clicked(event, x, y);
			}

		});

		continuarJuego.addListener(new ClickListener() {

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

				if (dato.isSonido())

				{

					recurso.get("audio/Battle in the winter.ogg", Music.class).stop();

				}

				dato.setPartida(false);

				juego.setScreen(new PantallaJuego(juego));

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
