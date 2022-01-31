package com.diamon.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaOpciones extends Pantalla {

	private TextButton atras;

	private Label titulo;

	private TextButton partida;

	private TextButton controles;

	private TextButton graficos;

	private TextButton sonido;

	private Label tituloPantallaCompleta;

	private Label tituloSincronizacionVertical;

	private Label tituloFiltradoBilineal;

	private Label tituloMostrarFPS;

	private Label tituloPrueba;

	private CheckBox pantallaCompleta;

	private CheckBox sincronizacionVertical;

	private CheckBox filtradoBilineal;

	private CheckBox mostrarFPS;

	private CheckBox prueba;

	private TextButton aceptarGraficos;

	private TextButton cancelarGraficos;

	private Label tituloOpcionesGraficos;

	private Label tituloOpcionesPartida;

	private TextButton aceptarPartida;

	private TextButton cancelarPartida;

	private Label tituloAutoDisparo;

	private CheckBox autoDisparo;

	private Label tituloOpcionesControles;

	private TextButton atrasControles;

	private Label tituloMusica;

	private Label tituloSonido;

	private Slider volumenMusica;

	private Slider volumenSonido;

	private CheckBox activarSonido;

	private Label tituloactivarSonido;

	private TextButton aceptarSonido;

	private TextButton cancelarSonido;

	private Label tituloOpcionesSonido;

	private Label textoArriba;

	private Label textoAbajo;

	private Label textoIzquierda;

	private Label textoDerecha;

	private Label textoDisparo;

	private Label textoDisparoMisil;

	private Label textoDisparoBomba;

	private Label textoPausaJuego;

	private Image arriba;

	private Image abajo;

	private Image izquierda;

	private Image derecha;

	private Image disparo;

	private Image disparoMisil;

	private Image disparoBomba;

	private Image pausaJuego;

	private Image clicIzquierdo;

	private Image clicDerecho;

	private boolean[] clic;

	private boolean[] desClic;

	public PantallaOpciones(Juego juego) {
		super(juego);

	}

	@Override
	public void mostrar() {

		clic = new boolean[12];

		desClic = new boolean[12];

		for (int i = 0; i < clic.length; i++) {

			clic[i] = false;

		}

		for (int i = 0; i < desClic.length; i++) {

			desClic[i] = true;

		}

		atras = new TextButton("Atrás", recurso.get("ui/uieli.json", Skin.class));

		atras.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		atras.setPosition(32, 32);

		titulo = new Label("Opciones", recurso.get("ui/uieli.json", Skin.class));

		titulo.setSize(Juego.ANCHO_PANTALLA / 3, 32);

		titulo.setPosition((Juego.ANCHO_PANTALLA / 3) + 50, Juego.ALTO_PANTALLA - 64);

		partida = new TextButton("Partida", recurso.get("ui/uieli.json", Skin.class));

		partida.setSize(Juego.ANCHO_PANTALLA / 3, 32);

		partida.setPosition(Juego.ANCHO_PANTALLA / 3, 240);

		controles = new TextButton("Controles", recurso.get("ui/uieli.json", Skin.class));

		controles.setSize(Juego.ANCHO_PANTALLA / 3, 32);

		controles.setPosition(Juego.ANCHO_PANTALLA / 3, 192);

		graficos = new TextButton("Gráficos", recurso.get("ui/uieli.json", Skin.class));

		graficos.setSize(Juego.ANCHO_PANTALLA / 3, 32);

		graficos.setPosition(Juego.ANCHO_PANTALLA / 3, 144);

		sonido = new TextButton("Sonido", recurso.get("ui/uieli.json", Skin.class));

		sonido.setSize(Juego.ANCHO_PANTALLA / 3, 32);

		sonido.setPosition(Juego.ANCHO_PANTALLA / 3, 96);

		textoPausaJuego = new Label("Pausa", recurso.get("ui/uiskin.json", Skin.class));

		textoPausaJuego.setPosition(Juego.ANCHO_PANTALLA / 4, 320);

		pausaJuego = new Image(recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlEscape"));

		pausaJuego.setSize(128, 24);

		pausaJuego.setPosition(Juego.ANCHO_PANTALLA / 2, 320);

		textoArriba = new Label("Arriba", recurso.get("ui/uiskin.json", Skin.class));

		textoArriba.setPosition(Juego.ANCHO_PANTALLA / 4, 288);

		arriba = new Image(recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlArriba"));

		arriba.setSize(128, 24);

		arriba.setPosition(Juego.ANCHO_PANTALLA / 2, 288);

		textoAbajo = new Label("Abajo", recurso.get("ui/uiskin.json", Skin.class));

		textoAbajo.setPosition(Juego.ANCHO_PANTALLA / 4, 256);

		abajo = new Image(recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlAbajo"));

		abajo.setSize(128, 24);

		abajo.setPosition(Juego.ANCHO_PANTALLA / 2, 256);

		textoIzquierda = new Label("Izquierda", recurso.get("ui/uiskin.json", Skin.class));

		textoIzquierda.setPosition(Juego.ANCHO_PANTALLA / 4, 224);

		izquierda = new Image(
				recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlIzquierdo"));

		izquierda.setSize(128, 24);

		izquierda.setPosition(Juego.ANCHO_PANTALLA / 2, 224);

		textoDerecha = new Label("Derecha", recurso.get("ui/uiskin.json", Skin.class));

		textoDerecha.setPosition(Juego.ANCHO_PANTALLA / 4, 192);

		derecha = new Image(recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlDerecho"));

		derecha.setSize(128, 24);

		derecha.setPosition(Juego.ANCHO_PANTALLA / 2, 192);

		textoDisparo = new Label("Disparar", recurso.get("ui/uiskin.json", Skin.class));

		textoDisparo.setPosition(Juego.ANCHO_PANTALLA / 4, 160);

		disparo = new Image(recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlX"));

		disparo.setSize(128, 24);

		disparo.setPosition(Juego.ANCHO_PANTALLA / 2, 160);

		textoDisparoMisil = new Label("Disparar Misil", recurso.get("ui/uiskin.json", Skin.class));

		textoDisparoMisil.setPosition(Juego.ANCHO_PANTALLA / 4, 128);

		disparoMisil = new Image(recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlZ"));

		disparoMisil.setSize(128, 24);

		disparoMisil.setPosition(Juego.ANCHO_PANTALLA / 2, 128);

		textoDisparoBomba = new Label("Disparar Bomba", recurso.get("ui/uiskin.json", Skin.class));

		textoDisparoBomba.setPosition(Juego.ANCHO_PANTALLA / 4, 96);

		disparoBomba = new Image(
				recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("controlEspacio"));

		disparoBomba.setSize(128, 24);

		disparoBomba.setPosition(Juego.ANCHO_PANTALLA / 2, 96);

		clicIzquierdo = new Image(
				recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("clicIzquierdo"));

		clicIzquierdo.setSize(128, 24);

		clicIzquierdo.setPosition(Juego.ANCHO_PANTALLA / 2 + 160, 160);

		clicDerecho = new Image(recurso.get("textura/controles.atlas", TextureAtlas.class).findRegion("clicDerecho"));

		clicDerecho.setSize(128, 24);

		clicDerecho.setPosition(Juego.ANCHO_PANTALLA / 2 + 160, 128);

		tituloOpcionesControles = new Label("Controles", recurso.get("ui/uieli.json", Skin.class));

		tituloOpcionesControles.setPosition((Juego.ANCHO_PANTALLA / 3) + 50, Juego.ALTO_PANTALLA - 64);

		atrasControles = new TextButton("Atrás", recurso.get("ui/uieli.json", Skin.class));

		atrasControles.setSize(Juego.ANCHO_PANTALLA / 7, 32);

		atrasControles.setPosition(32, 32);

		tituloOpcionesPartida = new Label("Partida", recurso.get("ui/uieli.json", Skin.class));

		tituloOpcionesPartida.setPosition((Juego.ANCHO_PANTALLA / 3) + 54, Juego.ALTO_PANTALLA - 64);

		tituloAutoDisparo = new Label("Auto Disparo", recurso.get("ui/uiskin.json", Skin.class));

		tituloAutoDisparo.setPosition(Juego.ANCHO_PANTALLA / 4, 288);

		autoDisparo = new CheckBox("", recurso.get("ui/uiskin.json", Skin.class));

		autoDisparo.setPosition(Juego.ANCHO_PANTALLA / 2 + 128, 288);

		cancelarPartida = new TextButton("Cancelar", recurso.get("ui/uieli.json", Skin.class));

		cancelarPartida.setSize(Juego.ANCHO_PANTALLA / 7, 32);

		cancelarPartida.setPosition(32, 32);

		aceptarPartida = new TextButton("Aceptar", recurso.get("ui/uieli.json", Skin.class));

		aceptarPartida.setSize(Juego.ANCHO_PANTALLA / 7, 32);

		aceptarPartida.setPosition(608 - aceptarPartida.getWidth(), 32);

		autoDisparo.setChecked(dato.isDiparoAutomatico());

		tituloPantallaCompleta = new Label("Pantalla Completa", recurso.get("ui/uiskin.json", Skin.class));

		tituloPantallaCompleta.setPosition(Juego.ANCHO_PANTALLA / 4, 288);

		pantallaCompleta = new CheckBox("", recurso.get("ui/uiskin.json", Skin.class));

		pantallaCompleta.setPosition(Juego.ANCHO_PANTALLA / 2 + 128, 288);

		tituloSincronizacionVertical = new Label("V-Sync", recurso.get("ui/uiskin.json", Skin.class));

		tituloSincronizacionVertical.setPosition(Juego.ANCHO_PANTALLA / 4, 240);

		sincronizacionVertical = new CheckBox("", recurso.get("ui/uiskin.json", Skin.class));

		sincronizacionVertical.setPosition(Juego.ANCHO_PANTALLA / 2 + 128, 240);

		tituloFiltradoBilineal = new Label("Filtrado Bilineal", recurso.get("ui/uiskin.json", Skin.class));

		tituloFiltradoBilineal.setPosition(Juego.ANCHO_PANTALLA / 4, 192);

		filtradoBilineal = new CheckBox("", recurso.get("ui/uiskin.json", Skin.class));

		filtradoBilineal.setPosition(Juego.ANCHO_PANTALLA / 2 + 128, 192);

		tituloMostrarFPS = new Label("Mostrar FPS", recurso.get("ui/uiskin.json", Skin.class));

		tituloMostrarFPS.setPosition(Juego.ANCHO_PANTALLA / 4, 144);

		mostrarFPS = new CheckBox("", recurso.get("ui/uiskin.json", Skin.class));

		mostrarFPS.setPosition(Juego.ANCHO_PANTALLA / 2 + 128, 144);

		tituloPrueba = new Label("Prueba", recurso.get("ui/uiskin.json", Skin.class));

		tituloPrueba.setPosition(Juego.ANCHO_PANTALLA / 4, 96);

		prueba = new CheckBox("", recurso.get("ui/uiskin.json", Skin.class));

		prueba.setPosition(Juego.ANCHO_PANTALLA / 2 + 128, 96);

		cancelarGraficos = new TextButton("Cancelar", recurso.get("ui/uieli.json", Skin.class));

		cancelarGraficos.setSize(Juego.ANCHO_PANTALLA / 7, 32);

		cancelarGraficos.setPosition(32, 32);

		aceptarGraficos = new TextButton("Aceptar", recurso.get("ui/uieli.json", Skin.class));

		aceptarGraficos.setSize(Juego.ANCHO_PANTALLA / 7, 32);

		aceptarGraficos.setPosition(608 - aceptarGraficos.getWidth(), 32);

		tituloOpcionesGraficos = new Label("Gráficos", recurso.get("ui/uieli.json", Skin.class));

		tituloOpcionesGraficos.setPosition((Juego.ANCHO_PANTALLA / 3) + 50, Juego.ALTO_PANTALLA - 64);

		pantallaCompleta.setChecked(dato.isPantallaCompleta());

		sincronizacionVertical.setChecked(dato.isSincronizacionVertical());

		filtradoBilineal.setChecked(dato.isFiltradoBilineal());

		mostrarFPS.setChecked(dato.isMostrarFPS());

		prueba.setChecked(dato.isPrueba());

		tituloMusica = new Label("Volumen de la Musica", recurso.get("ui/uiskin.json", Skin.class));

		tituloMusica.setPosition(Juego.ANCHO_PANTALLA / 8, 240);

		volumenMusica = new Slider(0.0f, 1.0f, 0.1f, false,
				new Skin(Gdx.files.internal("ui/skin/neon-ui.json"), new TextureAtlas("ui/skin/neon-ui.atlas")));

		volumenMusica.setPosition(Juego.ANCHO_PANTALLA / 2 - 64, 240);

		tituloSonido = new Label("Volumen del Sonido", recurso.get("ui/uiskin.json", Skin.class));

		tituloSonido.setPosition(Juego.ANCHO_PANTALLA / 8, 192);

		volumenSonido = new Slider(0.0f, 1.0f, 0.1f, false,
				new Skin(Gdx.files.internal("ui/skin/neon-ui.json"), new TextureAtlas("ui/skin/neon-ui.atlas")));

		volumenSonido.setPosition(Juego.ANCHO_PANTALLA / 2 - 64, 192);

		tituloactivarSonido = new Label("Musica del Juego", recurso.get("ui/uiskin.json", Skin.class));

		tituloactivarSonido.setPosition(Juego.ANCHO_PANTALLA / 8, 144);

		activarSonido = new CheckBox("", recurso.get("ui/uiskin.json", Skin.class));

		activarSonido.setPosition(Juego.ANCHO_PANTALLA / 2 - 64, 144);

		cancelarSonido = new TextButton("Cancelar", recurso.get("ui/uieli.json", Skin.class));

		cancelarSonido.setSize(Juego.ANCHO_PANTALLA / 7, 32);

		cancelarSonido.setPosition(32, 32);

		aceptarSonido = new TextButton("Aceptar", recurso.get("ui/uieli.json", Skin.class));

		aceptarSonido.setSize(Juego.ANCHO_PANTALLA / 7, 32);

		aceptarSonido.setPosition(608 - cancelarSonido.getWidth(), 32);

		tituloOpcionesSonido = new Label("Sonido", recurso.get("ui/uieli.json", Skin.class));

		tituloOpcionesSonido.setPosition((Juego.ANCHO_PANTALLA / 3) + 56, Juego.ALTO_PANTALLA - 64);

		activarSonido.setChecked(dato.isSonido());

		volumenMusica.setValue(dato.getVolumenMusica());

		volumenSonido.setValue(dato.getVolumenSonido());

		anadirBotonesOpciones(true);

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

		if (!partida.isOver())

		{

			if (desClic[1])

			{

				clic[1] = true;

				desClic[1] = false;
			}

		}

		if (!controles.isOver())

		{

			if (desClic[2])

			{

				clic[2] = true;

				desClic[2] = false;
			}

		}

		if (!graficos.isOver())

		{

			if (desClic[3])

			{

				clic[3] = true;

				desClic[3] = false;
			}

		}

		if (!sonido.isOver())

		{

			if (desClic[4])

			{

				clic[4] = true;

				desClic[4] = false;
			}

		}

		if (!cancelarPartida.isOver())

		{

			if (desClic[5])

			{

				clic[5] = true;

				desClic[5] = false;
			}

		}

		if (!aceptarPartida.isOver())

		{

			if (desClic[6])

			{

				clic[6] = true;

				desClic[6] = false;
			}

		}

		if (!atrasControles.isOver())

		{

			if (desClic[7])

			{

				clic[7] = true;

				desClic[7] = false;
			}

		}

		if (!cancelarGraficos.isOver())

		{

			if (desClic[8])

			{

				clic[8] = true;

				desClic[8] = false;
			}

		}

		if (!aceptarGraficos.isOver())

		{

			if (desClic[9])

			{

				clic[9] = true;

				desClic[9] = false;
			}

		}

		if (!cancelarSonido.isOver())

		{

			if (desClic[10])

			{

				clic[10] = true;

				desClic[10] = false;
			}

		}

		if (!aceptarSonido.isOver())

		{

			if (desClic[11])

			{

				clic[11] = true;

				desClic[11] = false;
			}

		}

	}

	private void anadirBotonesOpciones(boolean anadir) {

		if (anadir) {
			nivelMenu.addActor(titulo);

			nivelMenu.addActor(partida);

			nivelMenu.addActor(controles);

			nivelMenu.addActor(graficos);

			nivelMenu.addActor(sonido);

			nivelMenu.addActor(atras);

		} else {
			titulo.remove();

			partida.remove();

			controles.remove();

			graficos.remove();

			sonido.remove();

			atras.remove();

		}

	}

	private void anadirBotonesSonido(boolean anadir) {

		if (anadir) {
			nivelMenu.addActor(tituloMusica);

			nivelMenu.addActor(volumenMusica);

			nivelMenu.addActor(tituloSonido);

			nivelMenu.addActor(volumenSonido);

			nivelMenu.addActor(tituloactivarSonido);

			nivelMenu.addActor(activarSonido);

			nivelMenu.addActor(aceptarSonido);

			nivelMenu.addActor(cancelarSonido);

			nivelMenu.addActor(tituloOpcionesSonido);

		} else {

			tituloMusica.remove();

			volumenMusica.remove();

			tituloSonido.remove();

			volumenSonido.remove();

			tituloactivarSonido.remove();

			activarSonido.remove();

			aceptarSonido.remove();

			cancelarSonido.remove();

			tituloOpcionesSonido.remove();

		}

	}

	private void anadirBotonesGraficos(boolean anadir) {

		if (anadir) {

			nivelMenu.addActor(tituloPantallaCompleta);

			nivelMenu.addActor(pantallaCompleta);

			nivelMenu.addActor(tituloSincronizacionVertical);

			nivelMenu.addActor(sincronizacionVertical);

			nivelMenu.addActor(tituloFiltradoBilineal);

			nivelMenu.addActor(filtradoBilineal);

			nivelMenu.addActor(tituloMostrarFPS);

			nivelMenu.addActor(mostrarFPS);

			nivelMenu.addActor(tituloPrueba);

			nivelMenu.addActor(prueba);

			nivelMenu.addActor(aceptarGraficos);

			nivelMenu.addActor(cancelarGraficos);

			nivelMenu.addActor(tituloOpcionesGraficos);

		} else {

			tituloPantallaCompleta.remove();

			pantallaCompleta.remove();

			tituloSincronizacionVertical.remove();

			sincronizacionVertical.remove();

			tituloFiltradoBilineal.remove();

			filtradoBilineal.remove();

			tituloMostrarFPS.remove();

			mostrarFPS.remove();

			tituloPrueba.remove();

			prueba.remove();

			aceptarGraficos.remove();

			cancelarGraficos.remove();

			tituloOpcionesGraficos.remove();

		}

	}

	private void anadirBotonesPartida(boolean anadir) {

		if (anadir) {

			nivelMenu.addActor(tituloOpcionesPartida);

			nivelMenu.addActor(tituloAutoDisparo);

			nivelMenu.addActor(autoDisparo);

			nivelMenu.addActor(aceptarPartida);

			nivelMenu.addActor(cancelarPartida);

		} else {

			tituloOpcionesPartida.remove();

			tituloAutoDisparo.remove();

			autoDisparo.remove();

			aceptarPartida.remove();

			cancelarPartida.remove();

		}

	}

	private void anadirBotonesControles(boolean anadir) {

		if (anadir) {

			nivelMenu.addActor(tituloOpcionesControles);

			nivelMenu.addActor(atrasControles);

			nivelMenu.addActor(textoArriba);

			nivelMenu.addActor(arriba);

			nivelMenu.addActor(textoAbajo);

			nivelMenu.addActor(abajo);

			nivelMenu.addActor(textoIzquierda);

			nivelMenu.addActor(izquierda);

			nivelMenu.addActor(textoDerecha);

			nivelMenu.addActor(derecha);

			nivelMenu.addActor(textoDisparo);

			nivelMenu.addActor(disparo);

			nivelMenu.addActor(textoDisparoMisil);

			nivelMenu.addActor(disparoMisil);

			nivelMenu.addActor(textoDisparoBomba);

			nivelMenu.addActor(disparoBomba);

			nivelMenu.addActor(textoPausaJuego);

			nivelMenu.addActor(pausaJuego);

			nivelMenu.addActor(clicIzquierdo);

			nivelMenu.addActor(clicDerecho);

		} else {

			tituloOpcionesControles.remove();

			atrasControles.remove();

			textoArriba.remove();

			arriba.remove();

			textoAbajo.remove();

			abajo.remove();

			textoIzquierda.remove();

			izquierda.remove();

			textoDerecha.remove();

			derecha.remove();

			textoDisparo.remove();

			disparo.remove();

			textoDisparoMisil.remove();

			disparoMisil.remove();

			textoDisparoBomba.remove();

			disparoBomba.remove();

			textoPausaJuego.remove();

			pausaJuego.remove();

			clicDerecho.remove();

			clicIzquierdo.remove();

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

		partida.addListener(new ClickListener() {

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

				anadirBotonesOpciones(false);

				anadirBotonesPartida(true);

				super.clicked(event, x, y);
			}

		});

		controles.addListener(new ClickListener() {

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

				anadirBotonesOpciones(false);

				anadirBotonesControles(true);

				super.clicked(event, x, y);
			}

		});

		aceptarSonido.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[11])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[11] = false;

					desClic[11] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(true);

				dato.setVolumenMusica(volumenMusica.getValue());

				dato.setVolumenSonido(volumenSonido.getValue());

				dato.setSonido(activarSonido.isChecked());

				anadirBotonesSonido(false);

				sonido();

				configuracion.escribirDatos(dato);

				super.clicked(event, x, y);
			}

		});

		cancelarSonido.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[10])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[10] = false;

					desClic[10] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(true);

				activarSonido.setChecked(dato.isSonido());

				volumenMusica.setValue(dato.getVolumenMusica());

				volumenSonido.setValue(dato.getVolumenSonido());

				anadirBotonesSonido(false);

			}

		});

		aceptarGraficos.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[9])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[9] = false;

					desClic[9] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@SuppressWarnings("static-access")
			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(true);

				dato.setPantallaCompleta(pantallaCompleta.isChecked());

				dato.setSincronizacionVertical(sincronizacionVertical.isChecked());

				dato.setFiltradoBilineal(filtradoBilineal.isChecked());

				dato.setMostrarFPS(mostrarFPS.isChecked());

				dato.setPrueba(prueba.isChecked());

				anadirBotonesGraficos(false);

				filtradoBilineal();

				if (dato.isPantallaCompleta()) {

					if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

						Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

					}

				}

				if (!dato.isPantallaCompleta()) {

					if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

						Gdx.graphics.setWindowedMode(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);
					}

				}

				configuracion.escribirDatos(dato);

				super.clicked(event, x, y);
			}

		});

		cancelarGraficos.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[8])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[8] = false;

					desClic[8] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(true);

				pantallaCompleta.setChecked(dato.isPantallaCompleta());

				sincronizacionVertical.setChecked(dato.isSincronizacionVertical());

				filtradoBilineal.setChecked(dato.isFiltradoBilineal());

				mostrarFPS.setChecked(dato.isMostrarFPS());

				prueba.setChecked(dato.isPrueba());

				anadirBotonesGraficos(false);

				super.clicked(event, x, y);
			}

		});

		aceptarPartida.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[6])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[6] = false;

					desClic[6] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(true);

				dato.setDiparoAutomatico(autoDisparo.isChecked());

				anadirBotonesPartida(false);

				configuracion.escribirDatos(dato);

				super.clicked(event, x, y);
			}

		});

		cancelarPartida.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[5])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[5] = false;

					desClic[5] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(true);

				autoDisparo.setChecked(dato.isDiparoAutomatico());

				anadirBotonesPartida(false);

				super.clicked(event, x, y);
			}

		});

		atrasControles.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[7])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[7] = false;

					desClic[7] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(true);

				anadirBotonesControles(false);

				super.clicked(event, x, y);
			}

		});

		graficos.addListener(new ClickListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {

				if (clic[3])

				{

					if (dato.isSonido())

					{

						recurso.get("audio/menu.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					clic[3] = false;

					desClic[3] = true;

				}

				return super.mouseMoved(event, x, y);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				anadirBotonesOpciones(false);

				anadirBotonesGraficos(true);

				super.clicked(event, x, y);
			}

		});

		sonido.addListener(new ClickListener() {

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

				anadirBotonesOpciones(false);

				anadirBotonesSonido(true);

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

		configuracion.escribirDatos(dato);

	}

	private void filtradoBilineal() {

		if (dato.isFiltradoBilineal()) {

			recurso.get("textura/balaExplosiva.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
			recurso.get("textura/pausa.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/invisible.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/nube.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/fondoNivel1.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/fondoNivel2.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/fondoNivel3.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/fondoNivel4.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/fondo1.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/menuPausa.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/badlogic.jpg", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/titulo.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/cursor.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/bala.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/barcoVerde.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/paracaisdista.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
			recurso.get("textura/paracaisdista1.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);

			recurso.get("textura/paracaisdista2.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);

			recurso.get("textura/paracaisdista3.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);

			for (Texture tetura : recurso.get("textura/item.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/barrasHelicoptero.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/satelite.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/maquinaParedD.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/iconos.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/elirecursos.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/controles.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/cajas.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/carros.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/sierra.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/antiAereo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/robot.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/ovni.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/platilloVolador.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/platilloDeLuz.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/aliengris.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/alienpurpura.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/alienrojo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/alienverde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/alienverde2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/aliennaranja.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/aliennaranja2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/tripleazul.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/triplegris.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/triplepurpura.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/tripleverde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/tripleverde2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/triplerojo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/triplenaranja.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/triplenaranja2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/helicoptero.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/helicopteroRedondo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/helicopteroNegro.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/helicopteroMedico.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/helicopteroVerde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/elicoptero1.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/bomba.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/bombaAmarilla.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/bombaRoja.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/bombaVerde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/bolaPlasma.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/maquinaPared.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/misil.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/fuego.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/humo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/explosion1.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/explosion.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

			for (Texture tetura : recurso.get("textura/nubes.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

		}

		if (!dato.isFiltradoBilineal()) {

			recurso.get("textura/balaExplosiva.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/pausa.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/invisible.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/nube.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/fondoNivel1.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/fondoNivel2.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/fondoNivel3.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/fondoNivel4.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/fondo1.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/menuPausa.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/badlogic.jpg", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/titulo.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/cursor.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/bala.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/barcoVerde.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/paracaisdista.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/paracaisdista1.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);

			recurso.get("textura/paracaisdista2.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);

			recurso.get("textura/paracaisdista3.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);

			for (Texture tetura : recurso.get("textura/barrasHelicoptero.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/satelite.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/maquinaParedD.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/iconos.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/elirecursos.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/controles.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/cajas.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/carros.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/sierra.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/antiAereo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/robot.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/ovni.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/platilloVolador.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/platilloDeLuz.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/aliengris.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/alienpurpura.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/alienrojo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/alienverde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/alienverde2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/aliennaranja.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/aliennaranja2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/tripleazul.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/triplegris.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/triplepurpura.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/tripleverde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/tripleverde2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/triplerojo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/triplenaranja.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/triplenaranja2.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/helicoptero.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/helicopteroRedondo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/helicopteroNegro.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/helicopteroMedico.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/helicopteroVerde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/elicoptero1.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/bomba.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/bombaAmarilla.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/bombaRoja.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/bombaVerde.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/bolaPlasma.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/maquinaPared.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/misil.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/fuego.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/humo.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/explosion1.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/explosion.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/nubes.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/item.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

		}

	}

	private void sonido() {

		recurso.get("audio/Battle Theme 1.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/Battle in the winter.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/The Outer Forest.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/Orbital Colossus.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/bellgrave.ogg", Music.class).setVolume(dato.getVolumenMusica());

		if (dato.isSonido()) {

			if (!recurso.get("audio/Battle in the winter.ogg", Music.class).isPlaying()) {

				recurso.get("audio/Battle in the winter.ogg", Music.class).setLooping(true);

				recurso.get("audio/Battle in the winter.ogg", Music.class).play();

			}

		} else {

			recurso.get("audio/Battle in the winter.ogg", Music.class).stop();

		}

	}

	@Override
	public void liberarRecursos() {
		// TODO Auto-generated method stub

	}

}
