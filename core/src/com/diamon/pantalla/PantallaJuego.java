package com.diamon.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.diamon.dato.Dato;
import com.diamon.nivel.Niveles;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Nivel;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;
import com.diamon.personaje.Bala;
import com.diamon.personaje.BalaExplosiva;
import com.diamon.personaje.Bomba;
import com.diamon.personaje.Cursor;
import com.diamon.personaje.ExplosionTerreno;
import com.diamon.personaje.Jugador;
import com.diamon.personaje.Misil;
import com.diamon.personaje.Satelite;
import com.diamon.utilidad.EditorNivel;

public class PantallaJuego extends Pantalla {

	private Nivel mundo;

	private Jugador jugador;

	private Image pausa;

	private TextButton reanudar;

	private Image menuPausa;

	private TextButton menu;

	private TextButton editarNivel;

	private TextButton terminarEdicion;

	private Image dispararBomba;

	private Image dispararMisil;

	private Image vida;

	private Image bomba;

	private Image misil;

	private Image helicoptero;

	private Image barraDeItem;

	private Image barraDeItemPuntaje;

	private Image vidaJefe;

	private Image cantidadVidaJefe;

	private Image jefe;

	private volatile boolean pausar;

	private Music[] musica;

	private Music musicaGefe;

	private Sound sonido;

	private int numeroNivel;

	private float tiempoCuadro;

	private boolean editar;

	private EditorNivel editor;

	private Cursor cursor;

	private TiledMap[] mapas;

	private int puntos;

	private boolean escape;

	private Label fps;

	private Label textoPuntos;

	private Label textoNumeroNivel;

	private Label textoVida;

	private Label textoBomba;

	private Label textoMisil;

	private Label textoPausa;

	private Label gameOver;

	private boolean game;

	private int indice;

	private boolean gefe;

	private boolean[] clic;

	private boolean[] desClic;

	private float tiempoEspera;

	private boolean inmunidadJugador;

	private boolean uiJefe;

	private float cantidadVida;

	public PantallaJuego(Juego juego) {
		super(juego);

	}

	@SuppressWarnings("static-access")
	@Override
	public void mostrar() {

		////////

		if (publicidad != null) {

			publicidad.mostrarBanner();
		}

		clic = new boolean[2];

		desClic = new boolean[2];

		for (int i = 0; i < clic.length; i++) {

			clic[i] = false;

		}

		for (int i = 0; i < desClic.length; i++) {

			desClic[i] = true;

		}

		indice = 0;

		musica = new Music[4];

		escape = true;

		game = true;

		if (!dato.isDiparoAutomatico()) {

			if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

				Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(32, 32, Pixmap.Format.RGBA8888), 0, 0));

			}

		}

		if (dato.isPartida()) {

			dato.setNumeroNivel(1);

		}

		numeroNivel = dato.getNumeroNivel();

		vidaJefe = new Image(recurso.get("textura/vidaJefe.png", Texture.class));

		cantidadVidaJefe = new Image(recurso.get("textura/catidadVidaJefe.png", Texture.class));

		jefe = new Image(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).findRegion("alienazul1-1"));

		if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

			vidaJefe.setPosition(Juego.ANCHO_PANTALLA - Juego.ANCHO_PANTALLA / 3, 450);

			vidaJefe.setSize(Juego.ANCHO_PANTALLA / 3, 32);

			vidaJefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

			cantidadVidaJefe.setPosition(Juego.ANCHO_PANTALLA - Juego.ANCHO_PANTALLA / 3 + 4, 458);

			cantidadVidaJefe.setSize(204, 16);

			cantidadVidaJefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

			jefe.setPosition(Juego.ANCHO_PANTALLA - Juego.ANCHO_PANTALLA / 3 - 34, 448);

			jefe.setSize(32, 32);

			jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		}

		if (Gdx.app.getType() == Gdx.app.getType().Android) {

			vidaJefe.setPosition(364, 450);

			vidaJefe.setSize(Juego.ANCHO_PANTALLA / 3, 32);

			vidaJefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

			cantidadVidaJefe.setPosition(368, 458);

			cantidadVidaJefe.setSize(204, 16);

			cantidadVidaJefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

			jefe.setPosition(330, 448);

			jefe.setSize(32, 32);

			jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		}

		barraDeItem = new Image(
				recurso.get("textura/barrasHelicoptero.atlas", TextureAtlas.class).findRegion("barra1"));

		barraDeItem.setPosition(0, 450);

		barraDeItem.setSize(Juego.ANCHO_PANTALLA / 3, 32);

		barraDeItem.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		nivel.addActor(barraDeItem);

		barraDeItemPuntaje = new Image(
				recurso.get("textura/barrasHelicoptero.atlas", TextureAtlas.class).findRegion("barra2"));

		barraDeItemPuntaje.setPosition(0, 0);

		barraDeItemPuntaje.setSize(Juego.ANCHO_PANTALLA / 3, 32);

		barraDeItemPuntaje.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		nivel.addActor(barraDeItemPuntaje);

		fps = new Label("", recurso.get("ui/uiskin.json", Skin.class));

		fps.setPosition(580, 12);

		nivel.addActor(fps);

		gameOver = new Label("Fin del Juego", recurso.get("ui/uieli.json", Skin.class));

		gameOver.setPosition((Juego.ANCHO_PANTALLA / 2 - 64), Juego.ALTO_PANTALLA / 2);

		textoPuntos = new Label("", recurso.get("ui/uiskin.json", Skin.class));

		textoPuntos.setPosition(8, 12);

		nivel.addActor(textoPuntos);

		textoNumeroNivel = new Label("", recurso.get("ui/uiskin.json", Skin.class));

		textoNumeroNivel.setPosition(112, 12);

		nivel.addActor(textoNumeroNivel);

		textoVida = new Label("", recurso.get("ui/uiskin.json", Skin.class));

		textoVida.setSize(32, 32);

		textoVida.setPosition(66, 450);

		nivel.addActor(textoVida);

		textoBomba = new Label("", recurso.get("ui/uiskin.json", Skin.class));

		textoBomba.setSize(32, 32);

		textoBomba.setPosition(115, 450);

		nivel.addActor(textoBomba);

		textoMisil = new Label("", recurso.get("ui/uiskin.json", Skin.class));

		textoMisil.setSize(32, 32);

		textoMisil.setPosition(148, 450);

		nivel.addActor(textoMisil);

		if (dato.getHelicoptero() == Dato.HELICOPTERO_NORMAL) {

			helicoptero = new Image(recurso.get("textura/helicoptero.atlas", TextureAtlas.class).getRegions().get(0));
		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_REDONDO) {

			helicoptero = new Image(
					recurso.get("textura/helicopteroRedondo.atlas", TextureAtlas.class).getRegions().get(0));
		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_NEGRO) {

			helicoptero = new Image(
					recurso.get("textura/helicopteroNegro.atlas", TextureAtlas.class).getRegions().get(0));

		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_MEDICO) {

			helicoptero = new Image(
					recurso.get("textura/helicopteroMedico.atlas", TextureAtlas.class).getRegions().get(0));
		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_VERDE) {

			helicoptero = new Image(
					recurso.get("textura/helicopteroVerde.atlas", TextureAtlas.class).getRegions().get(0));

		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_SATELITAL) {

			helicoptero = new Image(recurso.get("textura/elicoptero1.atlas", TextureAtlas.class).getRegions().get(0));

		}

		helicoptero.setSize(32, 24);

		helicoptero.setPosition(0, 454);

		nivel.addActor(helicoptero);

		dispararMisil = new Image(recurso.get("textura/iconos.atlas", TextureAtlas.class).findRegion("iconoexplosion"));

		dispararMisil.setSize(32, 32);

		dispararMisil.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		dispararMisil.setPosition(548, 0);

		if (Gdx.app.getType() == Gdx.app.getType().Android) {

			nivel.addActor(dispararMisil);

		}

		dispararBomba = new Image(recurso.get("textura/iconos.atlas", TextureAtlas.class).findRegion("iconobomba"));

		dispararBomba.setSize(32, 32);

		dispararBomba.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		dispararBomba.setPosition(508, 0);

		if (Gdx.app.getType() == Gdx.app.getType().Android) {

			nivel.addActor(dispararBomba);

		}

		vida = new Image(recurso.get("textura/item.atlas", TextureAtlas.class).findRegion("vida"));

		vida.setSize(32, 32);

		vida.setPosition(34, 450.0F);

		nivel.addActor(vida);

		bomba = new Image(recurso.get("textura/item.atlas", TextureAtlas.class).findRegion("bomba"));

		bomba.setSize(32, 32);

		bomba.setPosition(86, 450.0F);

		nivel.addActor(bomba);

		misil = new Image(recurso.get("textura/item.atlas", TextureAtlas.class).findRegion("misil"));

		misil.setSize(32, 32);

		misil.setPosition(128, 450);

		nivel.addActor(misil);

		pausa = new Image(recurso.get("textura/pausa.png", Texture.class));

		pausa.setSize(64, 64);

		pausa.setPosition(576, 416);

		textoPausa = new Label("Pausa", recurso.get("ui/uieli.json", Skin.class));

		textoPausa.setPosition(280, 300);

		editarNivel = new TextButton("Editar Niveles", recurso.get("ui/uieli.json", Skin.class));

		editarNivel.setSize(213.0F, 32);

		editarNivel.setPosition(213.0F, 260);

		editarNivel.setColor(1.0F, 1.0F, 1.0F, 0.0F);

		reanudar = new TextButton("Reanudar", recurso.get("ui/uieli.json", Skin.class));

		reanudar.setSize(213.0F, 32);

		menu = new TextButton("Menu", recurso.get("ui/uieli.json", Skin.class));

		menu.setSize(213.0F, 32);

		if (dato.isEditor()) {

			reanudar.setPosition(213.0F, 212);

			menu.setPosition(213.0F, 164);

		}

		if (!dato.isEditor()) {

			reanudar.setPosition(213.0F, 248);

			menu.setPosition(213.0F, 200);

		}

		menuPausa = new Image(recurso.get("textura/menuPausa.png", Texture.class));

		menuPausa.setSize(320.0F, 240.0F);

		menuPausa.setPosition(160.0F, 120.0F);

		terminarEdicion = new TextButton("Terminar", recurso.get("ui/uiskin.json", Skin.class));

		terminarEdicion.setSize(96, 32);

		terminarEdicion.setPosition(528, 16);

		terminarEdicion.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		menuPausa.setColor(1.0F, 1.0F, 1.0F, 0.0F);

		reanudar.setColor(1.0F, 1.0F, 1.0F, 0.0F);

		menu.setColor(1.0F, 1.0F, 1.0F, 0.0F);

		pausa.setColor(1.0F, 1.0F, 1.0F, 0.9F);

		textoPausa.setColor(1.0F, 1.0F, 1.0F, 0.0F);

		ventanaDePausa(false, false);

		if (Gdx.app.getType() == Gdx.app.getType().Android) {

			nivel.addActor(pausa);

		}

		nivel.addActor(menuPausa);

		nivel.addActor(reanudar);

		nivel.addActor(menu);

		if (dato.isEditor()) {

			nivel.addActor(editarNivel);

		}

		nivel.addActor(textoPausa);

		if (dato.getHelicoptero() == Dato.HELICOPTERO_NORMAL) {

			jugador = new Jugador(recurso.get("textura/helicoptero.atlas", TextureAtlas.class).getRegions(), 0.01f,
					Animation.PlayMode.LOOP, this);

			jugador.setTipo(Jugador.HELICOPTERO_NORMAL);

			jugador.setSize(48, 22);

		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_REDONDO) {

			jugador = new Jugador(recurso.get("textura/helicopteroRedondo.atlas", TextureAtlas.class).getRegions(),
					0.01f, Animation.PlayMode.LOOP, this);

			jugador.setTipo(Jugador.HELICOPTERO_REDONDO);

			jugador.setSize(64, 32);

		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_NEGRO) {

			jugador = new Jugador(recurso.get("textura/helicopteroNegro.atlas", TextureAtlas.class).getRegions(), 0.01f,
					Animation.PlayMode.LOOP, this);

			jugador.setTipo(Jugador.HELICOPTERO_NEGRO);

			jugador.setSize(64, 32);

		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_MEDICO) {

			jugador = new Jugador(recurso.get("textura/helicopteroMedico.atlas", TextureAtlas.class).getRegions(),
					0.01f, Animation.PlayMode.LOOP, this);

			jugador.setTipo(Jugador.HELICOPTERO_MEDICO);

			jugador.setSize(64, 32);

		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_VERDE) {

			jugador = new Jugador(recurso.get("textura/helicopteroVerde.atlas", TextureAtlas.class).getRegions(), 0.01f,
					Animation.PlayMode.LOOP, this);

			jugador.setTipo(Jugador.HELICOPTERO_VERDE);

			jugador.setSize(64, 32);

		}

		if (dato.getHelicoptero() == Dato.HELICOPTERO_SATELITAL) {

			jugador = new Jugador(recurso.get("textura/elicoptero1.atlas", TextureAtlas.class).getRegions(), 0.01f,
					Animation.PlayMode.LOOP, this);

			jugador.setTipo(Jugador.HELICOPTERO_SATELITAL);

			jugador.setSize(64, 32);

		}

		mapas = new TiledMap[40];

		for (int i = 0; i < mapas.length; i++) {

			mapas[i] = recurso.get("mapa/nivel" + (i + 1) + "/nivel" + (i + 1) + ".tmx");

		}

		mundo = new Niveles(this, jugador, mapas);

		jugador.setTerminarNivel(true);

		cursor = new Cursor(recurso.get("textura/invisible.png", Texture.class), this);

		cursor.setSize(16, 16);

		cursor.setPosition(0, 0);

		pausar = false;

		editar = false;

		musica[0] = recurso.get("audio/Battle Theme 1.ogg", Music.class);
		musica[1] = recurso.get("audio/The Outer Forest.ogg", Music.class);
		musica[2] = recurso.get("audio/bellgrave.ogg", Music.class);
		musica[3] = recurso.get("audio/Battle in the winter.ogg", Music.class);

		musicaGefe = recurso.get("audio/Orbital Colossus.ogg", Music.class);

		sonido = recurso.get("audio/helicoptero.ogg", Sound.class);

		if (dato.isSonido()) {

			AgregarMusica();

		}

		editor = new EditorNivel(nivel, configuracion, dato, camara, personajes, this, recurso, cursor);

		puntos = dato.getPuntos();

	}

	private void botonesDeseleccionados() {

		if (!reanudar.isOver())

		{

			if (desClic[0])

			{

				clic[0] = true;

				desClic[0] = false;
			}

		}

		if (!menu.isOver())

		{

			if (desClic[1])

			{

				clic[1] = true;

				desClic[1] = false;
			}

		}

	}

	private void AgregarMusica() {

		if (musicaGefe != null) {

			musicaGefe.stop();
		}

		if (sonido != null) {

			sonido.stop();

		}

		if (musica[indice] != null) {

			musica[indice].stop();

		}

		if ((dato.getNumeroNivel() <= 10)) {

			indice = 0;

		}

		if ((dato.getNumeroNivel() >= 11) && (dato.getNumeroNivel() <= 20)) {

			indice = 1;

		}

		if ((dato.getNumeroNivel() >= 21) && (dato.getNumeroNivel() <= 30)) {

			indice = 2;

		}

		if ((dato.getNumeroNivel() >= 31) && (dato.getNumeroNivel() <= 40)) {

			indice = 3;

		}

		sonido.loop(dato.getVolumenSonido());

		if (!mundo.isIntro())

		{

			musica[indice].setLooping(true);

			musica[indice].play();

		}

	}

	@SuppressWarnings("static-access")
	private void ventanaDePausa(boolean visible, boolean animated) {

		float alphaTo = visible ? 0.8f : 0;

		float duration = animated ? 1 : 0;

		Touchable touchEnabled = visible ? Touchable.enabled : Touchable.disabled;

		menuPausa.addAction(Actions.sequence(Actions.touchable(touchEnabled), Actions.alpha(alphaTo, duration)));

		menu.addAction(Actions.sequence(Actions.touchable(touchEnabled), Actions.alpha(alphaTo, duration)));

		reanudar.addAction(Actions.sequence(Actions.touchable(touchEnabled), Actions.alpha(alphaTo, duration)));

		editarNivel.addAction(Actions.sequence(Actions.touchable(touchEnabled), Actions.alpha(alphaTo, duration)));

		textoPausa.addAction(Actions.sequence(Actions.touchable(touchEnabled), Actions.alpha(alphaTo, duration)));

		if (visible) {

			if (Gdx.app.getType() == Gdx.app.getType().Android) {

				pausa.remove();

			}

		} else {
			nivel.addAction(Actions.sequence(Actions.delay(duration), Actions.run(new Runnable() {

				public void run() {

					if (Gdx.app.getType() == Gdx.app.getType().Android) {

						nivel.addActor(pausa);

					}

					escape = true;

				}
			})));

		}

	}

	@Override
	public void eventos() {

		pausa.addListener(new ClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!mundo.isIntro())

				{

					if (!pausar) {

						if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

							Gdx.graphics.setCursor(
									Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("textura/cursor.png")), 0, 0));

						}

						mundo.setMoverCamara(false);

						if (dato.isSonido())

						{

							if (jugador.isGefe())

							{

								musicaGefe.pause();
							} else {

								musica[indice].pause();

							}
							sonido.pause();
						}

						ventanaDePausa(true, false);

					}

					pausar = true;

				}

				super.clicked(event, x, y);
			}
		});

		editarNivel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				pausar = false;

				mundo.setMoverCamara(true);

				editor.agregarUI();

				if (dato.isSonido())

				{

					musica[indice].stop();

					sonido.stop();

				}

				editor.setTerminar(false);

				jugador.setTerminarNivel(true);

				editar = true;

				ventanaDePausa(false, true);

				nivel.addActor(terminarEdicion);

				super.clicked(event, x, y);
			}
		});

		terminarEdicion.addListener(new ClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!dato.isDiparoAutomatico()) {

					if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

						Gdx.graphics
								.setCursor(Gdx.graphics.newCursor(new Pixmap(32, 32, Pixmap.Format.RGBA8888), 0, 0));

					}

				}

				if (Gdx.app.getType() == Gdx.app.getType().Android) {

					nivel.addActor(pausa);

				}

				if (dato.isSonido())

				{

					musica[indice].play();

					sonido.loop(dato.getVolumenSonido());

				}

				editor.setTerminar(true);

				editar = false;

				configuracion.escribirDatos(dato);

				terminarEdicion.remove();

				editor.eliminarUI();

				numeroNivel = dato.getNumeroNivel();

				jugador.setTerminarNivel(true);

				super.clicked(event, x, y);
			}
		});

		reanudar.addListener(new ClickListener() {

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

			@SuppressWarnings("static-access")
			@Override
			public void clicked(InputEvent event, float x, float y) {

				pausar = false;

				if (!pausar) {

					if (!dato.isDiparoAutomatico()) {

						if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

							Gdx.graphics.setCursor(
									Gdx.graphics.newCursor(new Pixmap(32, 32, Pixmap.Format.RGBA8888), 0, 0));

						}

					}
					mundo.setMoverCamara(true);

					if (dato.isSonido())

					{

						if (jugador.isGefe())

						{

							musicaGefe.play();
						} else {

							musica[indice].play();

						}
						sonido.loop(dato.getVolumenSonido());
					}

					ventanaDePausa(false, true);
				}

				super.clicked(event, x, y);
			}
		});

		dispararBomba.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent ev, float x, float y, int puntero, int boton) {

				jugador.setDispararBomba(true);

				return false;
			}

		});

		dispararMisil.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent ev, float x, float y, int puntero, int boton) {

				jugador.setDispararMisil(true);

				return false;
			}

		});

		menu.addListener(new ClickListener() {

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

				puntos = 0;

				////////

				if (publicidad != null) {

					publicidad.ocultarBanner();
				}

				juego.setScreen(new PantallaMenu(juego));

				super.clicked(event, x, y);
			}
		});

		if (!pausar) {

			nivel.addListener(new InputListener() {

				@Override
				public boolean handle(Event ev) {

					return super.handle(ev);
				}

				@Override
				public boolean touchDown(InputEvent ev, float x, float y, int puntero, int boton) {

					cursor.setPosition(x + (camara.position.x - Juego.ANCHO_PANTALLA / 2), y);

					editor.toquePresionado(ev, x, y, puntero, boton);

					if (mundo.isIntro()) {

						return true;

					} else {

						return jugador.toquePresionado(ev, x, y, puntero, boton);

					}

				}

				@Override
				public void touchUp(InputEvent ev, float x, float y, int puntero, int boton) {

					editor.toqueLevantado(ev, x, y, puntero, boton);

					if (!mundo.isIntro()) {

						jugador.toqueLevantado(ev, x, y, puntero, boton);

					}

				}

				@Override
				public void touchDragged(InputEvent ev, float x, float y, int puntero) {

					if (!mundo.isIntro()) {

						jugador.toqueDeslizando(ev, x, y, puntero);

					}

					editor.toqueDeslizando(ev, x, y, puntero);

					cursor.setPosition(x + (camara.position.x - Juego.ANCHO_PANTALLA / 2), y);

				}

				@Override
				public boolean mouseMoved(InputEvent ev, float x, float y) {

					if (!mundo.isIntro()) {

						jugador.ratonMoviendo(ev, x, y);

					}

					cursor.setPosition(x + (camara.position.x - Juego.ANCHO_PANTALLA / 2), y);

					editor.ratonMoviendo(ev, x, y);

					return super.mouseMoved(ev, x, y);
				}

				@Override
				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

					super.enter(event, x, y, pointer, fromActor);
				}

				@Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

					super.exit(event, x, y, pointer, toActor);
				}

				@SuppressWarnings("static-access")
				@Override
				public boolean keyDown(InputEvent ev, int codigoTecla) {

					editor.teclaPresionada(ev, codigoTecla);

					if (!escape)

					{

						if (Keys.ESCAPE == codigoTecla) {

							pausar = false;

							if (!pausar) {

								if (!dato.isDiparoAutomatico()) {

									if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

										Gdx.graphics.setCursor(Gdx.graphics
												.newCursor(new Pixmap(32, 32, Pixmap.Format.RGBA8888), 0, 0));

									}

								}
								mundo.setMoverCamara(true);

								if (dato.isSonido())

								{

									if (jugador.isGefe())

									{

										musicaGefe.play();
									} else {

										musica[indice].play();

									}
									sonido.loop(dato.getVolumenSonido());
								}

								ventanaDePausa(false, true);
							}

						}

					}

					if (escape)

					{

						if (!mundo.isIntro())

						{

							if (Keys.ESCAPE == codigoTecla) {

								escape = false;

								if (!pausar) {

									if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

										Gdx.graphics.setCursor(Gdx.graphics
												.newCursor(new Pixmap(Gdx.files.internal("textura/cursor.png")), 0, 0));
									}

									mundo.setMoverCamara(false);

									if (dato.isSonido())

									{

										if (jugador.isGefe())

										{

											musicaGefe.pause();
										} else {

											musica[indice].pause();

										}
										sonido.pause();
									}

									ventanaDePausa(true, false);

								}
								pausar = true;
							}

						}

					}

					if (mundo.isIntro()) {

						return true;

					} else {

						return jugador.teclaPresionada(ev, codigoTecla);

					}

				}

				@Override
				public boolean keyUp(InputEvent ev, int codigoTecla) {

					editor.teclaLevantada(ev, codigoTecla);

					if (mundo.isIntro()) {

						return true;

					} else {

						return jugador.teclaLevantada(ev, codigoTecla);

					}

				}

				@Override
				public boolean keyTyped(InputEvent event, char character) {

					return super.keyTyped(event, character);
				}
			});
		}

	}

	@Override
	public void colisiones() {

		for (int i = 0; i < personajes.size; i++) {

			if (personajes.get(i) instanceof Jugador) {

				puntos += ((Jugador) personajes.get(i)).getPuntos();

				((Jugador) personajes.get(i)).setPuntos(0);

			}

			if (personajes.get(i) instanceof Satelite) {

				puntos += ((Satelite) personajes.get(i)).getPuntos();

				((Satelite) personajes.get(i)).setPuntos(0);

			}

			Personaje personaje1 = personajes.get(i);

			Rectangle rectangulo1 = personaje1.getBoundingRectangle();

			for (int j = i + 1; j < personajes.size; j++) {

				Personaje personaje2 = personajes.get(j);

				Rectangle rectangulo2 = personaje2.getBoundingRectangle();

				if (rectangulo1.overlaps(rectangulo2)) {

					if (!dato.isPrueba()) {

						if (!jugador.isFinNivel()) {

							personaje1.colision(personaje2);

							personaje2.colision(personaje1);

						}

					}

				}
			}

			Personaje personaje = personajes.get(i);

			if (personaje.isRemover()) {

				puntos(personajes, i);

				personajes.removeIndex(i);
			}
		}

	}

	private void puntos(Array<Personaje> personajes, int i) {

		if (personajes.get(i) instanceof Bala) {

			puntos += ((Bala) personajes.get(i)).getPuntos();

		}

		if (personajes.get(i) instanceof Misil) {

			puntos += ((Misil) personajes.get(i)).getPuntos();

		}

		if (personajes.get(i) instanceof ExplosionTerreno) {

			puntos += ((ExplosionTerreno) personajes.get(i)).getPuntos();

		}
		if (personajes.get(i) instanceof Bomba) {

			puntos += ((Bomba) personajes.get(i)).getPuntos();

		}

		if (personajes.get(i) instanceof BalaExplosiva) {

			puntos += ((BalaExplosiva) personajes.get(i)).getPuntos();

		}

	}

	@SuppressWarnings("static-access")
	@Override
	public void actualizar(float delta) {

		if (!pausar) {

			if (!editar) {

				mundo.actualizar(delta);

			}

		}

		if (editar) {

			editor.actualizar();

		}

		if (jugador.isVivo()) {

			if (jugador.isGefe()) {

				if (mundo != null) {

					if (((Niveles) mundo).getJefeNivel() != null) {

						if (((Niveles) mundo).getJefeNivel().getDureza() <= 0) {

						} else {

							if (uiJefe) {

								nivel.addActor(cantidadVidaJefe);

								nivel.addActor(jefe);

								nivel.addActor(vidaJefe);

								uiJefe = false;
							}

							cantidadVidaJefe.setSize(((Niveles) mundo).getJefeNivel().getDureza() * cantidadVida, 16);

						}

					}

				}

			} else {

				if (jugador.isFinNivel()) {

					cantidadVidaJefe.remove();

					jefe.remove();

					vidaJefe.remove();

				}

			}

			if (jugador.isCambioTipo()) {

				if (jugador.getTipo() == Jugador.HELICOPTERO_NORMAL) {

					helicoptero.setDrawable(new TextureRegionDrawable(
							recurso.get("textura/helicoptero.atlas", TextureAtlas.class).getRegions().get(0)));

				}

				if (jugador.getTipo() == Jugador.HELICOPTERO_REDONDO) {

					helicoptero.setDrawable(new TextureRegionDrawable(
							recurso.get("textura/helicopteroRedondo.atlas", TextureAtlas.class).getRegions().get(0)));

				}

				if (jugador.getTipo() == Jugador.HELICOPTERO_NEGRO) {

					helicoptero.setDrawable(new TextureRegionDrawable(
							recurso.get("textura/helicopteroNegro.atlas", TextureAtlas.class).getRegions().get(0)));

				}

				if (jugador.getTipo() == Jugador.HELICOPTERO_MEDICO) {

					helicoptero.setDrawable(new TextureRegionDrawable(
							recurso.get("textura/helicopteroMedico.atlas", TextureAtlas.class).getRegions().get(0)));

				}

				if (jugador.getTipo() == Jugador.HELICOPTERO_VERDE) {

					helicoptero.setDrawable(new TextureRegionDrawable(
							recurso.get("textura/helicopteroVerde.atlas", TextureAtlas.class).getRegions().get(0)));

				}

				if (jugador.getTipo() == Jugador.HELICOPTERO_SATELITAL) {

					helicoptero.setDrawable(new TextureRegionDrawable(
							recurso.get("textura/elicoptero1.atlas", TextureAtlas.class).getRegions().get(0)));

				}

				jugador.setCambioTipo(false);
			}

		}

		if (!jugador.isVivo()) {

			if (jugador.isFinNivel()) {

				if (dato.getNumeroNivel() != 40) {

					dato.anadirPuntuaciones(puntos, dato.getNumeroNivel(), "Gana");

				}

			}

			if (dato.isSonido())

			{

				if (musica[indice].isPlaying())

				{

					musica[indice].stop();

					sonido.stop();

				}

				if (jugador.isGefe() && musicaGefe.isPlaying())

				{

					musicaGefe.stop();

					sonido.stop();
				}

			}

			tiempoCuadro += delta;

			if (game) {

				nivel.addActor(gameOver);

				game = false;
			}

			if (tiempoCuadro / 0.6f >= 1) {

				nivel.addAction(Actions.sequence(Actions.delay(1f), Actions.run(new Runnable() {

					public void run() {

						if (dato.getNumeroNivel() == 40 && jugador.isFinNivel()) {

							dato.setPuntos(0);

							dato.setVidas(3);

							dato.setMisiles(10);

							dato.setBombas(10);

							dato.setContinuar(false);

							dato.setHelicoptero(1);

							dato.setNumeroSatelite(0);

							dato.anadirPuntuaciones(puntos, dato.getNumeroNivel(), "Gana");

							dato.setNumeroNivel(1);

							dato.setEditor(true);

							if (publicidad != null) {

								publicidad.ocultarBanner();
							}

							if (dato.isSonido())

							{

								if (sonido != null) {

									sonido.stop();

								}

							}
							if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

								Gdx.graphics.setCursor(Gdx.graphics
										.newCursor(new Pixmap(Gdx.files.internal("textura/cursor.png")), 0, 0));

							}

							juego.setScreen(new PantallaCreditos(juego));

						} else {

							dato.setPuntos(0);

							dato.setVidas(3);

							dato.setMisiles(10);

							dato.setBombas(10);

							dato.setHelicoptero(1);

							dato.setNumeroSatelite(0);

							dato.anadirPuntuaciones(puntos, dato.getNumeroNivel(), "Pierde");

							if (publicidad != null) {

								publicidad.ocultarBanner();
							}

							if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

								Gdx.graphics.setCursor(Gdx.graphics
										.newCursor(new Pixmap(Gdx.files.internal("textura/cursor.png")), 0, 0));

							}

							juego.setScreen(new PantallaPuntuaciones(juego));

						}

					}
				})));

				tiempoCuadro = 0;
			}

		}

		if (jugador.isTerminarNivel()) {

			game = true;

			gameOver.remove();

			if (editar) {

				numeroNivel = dato.getNumeroNivel();

			}

			for (int i = 0; i < 40; i++) {

				if (numeroNivel == i + 1) {

					jugador.setTerminarNivel(false);

					dato.setNumeroNivel(numeroNivel);

					mundo.liberarRecursos();

					if (!editar) {

						if (dato.isSonido())

						{

							if (mundo.isIntro())

							{

								musica[indice].stop();

								nivel.addAction(Actions.sequence(Actions.delay(10f), Actions.run(new Runnable() {

									public void run() {

										AgregarMusica();

									}
								})));
							} else

							{

								AgregarMusica();

							}
						}

					}

					dato.setPuntos(puntos);

					dato.setVidas(jugador.getVida());

					dato.setMisiles(jugador.getMisil());

					dato.setBombas(jugador.getBomba());

					dato.setHelicoptero(jugador.getTipo());

					dato.setNumeroSatelite(jugador.getNumeroDeSatelites());

					gefe = false;

					mundo = new Niveles(this, jugador, mapas);

					inmunidadJugador = true;

					jugador.setInmune(inmunidadJugador);

					if (numeroNivel == 10) {

						if (mundo != null) {

							jefe = new Image(recurso.get("textura/alienAzul.atlas", TextureAtlas.class)
									.findRegion("alienazul1-4"));

							if (Gdx.app.getType() == Gdx.app.getType().Android) {

								jefe.setPosition(330, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}

							if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

								jefe.setPosition(Juego.ANCHO_PANTALLA - Juego.ANCHO_PANTALLA / 3 - 34, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}

							cantidadVida = (float) 204 / ((Niveles) mundo).getJefeNivel().getDureza();

						}

						uiJefe = true;

					}

					if (numeroNivel == 20) {

						if (mundo != null) {

							jefe = new Image(recurso.get("textura/alienverde.atlas", TextureAtlas.class)
									.findRegion("alienverde1-4"));

							if (Gdx.app.getType() == Gdx.app.getType().Android) {

								jefe.setPosition(330, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}

							if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

								jefe.setPosition(Juego.ANCHO_PANTALLA - Juego.ANCHO_PANTALLA / 3 - 34, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}

							cantidadVida = (float) 204 / ((Niveles) mundo).getJefeNivel().getDureza();

						}

						uiJefe = true;

					}

					if (numeroNivel == 30) {

						if (mundo != null) {

							jefe = new Image(recurso.get("textura/triplepurpura.atlas", TextureAtlas.class)
									.findRegion("triplepurpura1-4"));

							if (Gdx.app.getType() == Gdx.app.getType().Android) {

								jefe.setPosition(330, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}

							if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

								jefe.setPosition(Juego.ANCHO_PANTALLA - Juego.ANCHO_PANTALLA / 3 - 34, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}
							cantidadVida = (float) 204 / ((Niveles) mundo).getJefeNivel().getDureza();

						}

						uiJefe = true;
					}

					if (numeroNivel == 40) {

						if (mundo != null) {

							jefe = new Image(recurso.get("textura/triplenaranja.atlas", TextureAtlas.class)
									.findRegion("triplenaranja1-4"));

							if (Gdx.app.getType() == Gdx.app.getType().Android) {

								jefe.setPosition(330, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}

							if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

								jefe.setPosition(Juego.ANCHO_PANTALLA - Juego.ANCHO_PANTALLA / 3 - 34, 448);

								jefe.setSize(32, 32);

								jefe.setColor(1.0F, 1.0F, 1.0F, 0.7F);

							}

							cantidadVida = (float) 204 / ((Niveles) mundo).getJefeNivel().getDureza();

						}

						uiJefe = true;

					}

				}

			}

			numeroNivel++;

		}

		if (dato.isSonido())

		{

			if (jugador.isFinNivel()) {

				if (musica[indice].isPlaying()) {

					musica[indice].stop();

				}

			}

		}

		if (dato.isSonido())

		{

			if (jugador.isGefe()) {

				if (!gefe) {

					musica[indice].stop();

					musicaGefe.play();

					musicaGefe.setLooping(true);

					gefe = true;

				}

			} else {

				if (musicaGefe.isPlaying()) {

					musicaGefe.stop();

				}

			}

		}

		if (inmunidadJugador) {

			tiempoEspera += delta;

			if (tiempoEspera / 3 >= 1) {

				inmunidadJugador = false;

				jugador.setInmune(inmunidadJugador);

				tiempoEspera = 0;

			}

		}

		botonesDeseleccionados();

	}

	@Override
	public void dibujar(Batch pincel, float delta) {

		mundo.dibujar(pincel, delta);

		pincel.begin();

		cursor.dibujar(pincel, delta);

		pincel.end();

		textoPuntos.setText("Puntos " + puntos);

		textoNumeroNivel.setText("Nivel " + dato.getNumeroNivel());

		textoVida.setText("" + jugador.getVida());

		textoBomba.setText("" + jugador.getBomba());

		textoMisil.setText("" + jugador.getMisil());

		if (dato.isPrueba()) {

			test();

		}

		if (dato.isMostrarFPS()) {

			fps.setText("FPS: " + Gdx.graphics.getFramesPerSecond());

		}

	}

	@Override
	public void guardarDatos() {

		mundo.guardarDatos();

		configuracion.escribirDatos(dato);

	}

	@Override
	public void liberarRecursos() {

		mundo.liberarRecursos();

	}

	private void test() {

		for (Personaje personaje : personajes) {

			pincelPrueba.begin(ShapeRenderer.ShapeType.Line);

			Rectangle r = personaje.getBoundingRectangle();

			pincelPrueba.rect(r.x, r.y, r.width, r.height);

			pincelPrueba.end();
		}

	}

}
