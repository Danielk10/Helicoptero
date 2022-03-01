package com.diamon.pantalla;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaCreditos extends Pantalla {

	private static final float VELOCIDAD_CREDITOS = 0.5f;

	private TextButton aceptar;

	private Label titulo;

	private Label version;

	private Label[] creditosAgredecimientosNombre;

	private Label[] creditosAutores;

	private Label[] creditosDiseno;

	private Label[] creditosDisenoNombres;

	private Label[] creditosArtistasNombres;

	private Label[] creditosArtistasDeMusicaNombres;

	private Label creditosArtistas;

	private Label creditosArtistasDeMusica;

	private Label creditosAgredecimientos;

	private Label creditosEspecial;

	private Label creditoMotor;

	private Label creditoMotorNombre;

	private boolean[] clic;

	private boolean[] desClic;

	private Music musica;

	private float mover[];

	private float moverEspecial;

	private Array<Label> creditos;

	private float[] posiciones;

	private float posicionEspecial;

	public PantallaCreditos(Juego juego) {
		super(juego);

	}

	@Override
	public void mostrar() {

		clic = new boolean[1];

		desClic = new boolean[1];

		creditos = new Array<Label>();

		posicionEspecial = -1378.0f;

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

		String[] autores = { "Helicoptero", "2021 - 2022  Daniel Diamon y Diamond Black" };

		String[] titulosDesarrolladores = { "Diseño del Juego", "y Programación", "Diseñador Grafico" };

		String[] nombresDesarrolladores = { "Daniel Diamon", "Jesus Mnedez" };

		String[] nombresArtistas = { "Zuhria Alfitra - www.gameart2d.com",
				"Carlos Alface - www.carlosalface.blogspot.pt", "cemkalyoncu - www.opengameart.org",
				"yd - www.opengameart.org", "mishonis - www.opengameart.org", "2dGameCreation - www.opengameart.org",
				"knik1985 - www.opengameart.org", "www.enchantjs.com", "Tiled - www.mapeditor.org",
				"paint.net - www.getpaint.net", "Skin Composer - www.ray3k.wordpress.com",
				"GDX Texture Packer - www. crashinvaders.com", "Hiero - www.libgdx.com", "www.libgdx.com",
				"www.reygif.com", "RAY3K", "Jesus Mendez", "Daniel Diamon" };

		String[] nombresArtitasDeMusica = { "sfxr - www.drpetter.se", "Jobromedia - www.opengameart.org",
				"Alexandr Zhelanov - www.opengameart.org", "Tozan - www.opengameart.org",
				"Matthew Pablo - www.opengameart.org", "Zydro - www.opengameart.org", "Daniel Diamon" };

		String[] nombres = { "Alexander Hristov", "Jesus Code", "Nacho Cabanes", "Angel Navarro Baeza", "Yayo Arellano",
				"Luis Diamon", "Carlos Calanche", "Alfredo Casas", "Kelwins Mosquera", "Natalia Mosquera",
				"Herith Ducey", "Lucino Dulcey", "Ruth Celis", "Anye Mosquera", "Yelitza Vazquez", "Dannys Diamon",
				"Jorge Diamon", "Darwin Diamon", "Vanessa Diamon", "Jordan Rivas", "Angel Leonardo Reina",
				"Javier Narea", "Merlyannis Garcia", "Jennifer Rivero", "Luis Jose Garcia", "Veronica Mendez",
				"Ricardo Gonzalez Santamaria", "Jenina Estefania Santamaria" };

		creditosAutores = new Label[autores.length];

		creditosAgredecimientosNombre = new Label[nombres.length];

		creditosDiseno = new Label[titulosDesarrolladores.length];

		creditosDisenoNombres = new Label[nombresDesarrolladores.length];

		creditosArtistasNombres = new Label[nombresArtistas.length];

		creditosArtistasDeMusicaNombres = new Label[nombresArtitasDeMusica.length];

		int indice = 230;

		for (int i = 0; i < creditosAutores.length; i++) {

			creditosAutores[i] = new Label(autores[i], recurso.get("ui/creditos.json", Skin.class));

			creditosAutores[i].setPosition(Juego.ANCHO_PANTALLA / 2 - 50, indice);

			if (i == 1) {

				indice = indice - 24;

				creditosAutores[i].setPosition(Juego.ANCHO_PANTALLA / 4 - 20, indice);

			}

			creditos.add(creditosAutores[i]);

			nivelMenu.addActor(creditosAutores[i]);

			indice -= 24;
		}

		indice = indice - 24;

		for (int i = 0; i < creditosDiseno.length; i++) {

			creditosDiseno[i] = new Label(titulosDesarrolladores[i], recurso.get("ui/creditos.json", Skin.class));

			creditosDiseno[i].setPosition(Juego.ANCHO_PANTALLA / 4 - 20, indice);

			if (i == 2) {

				indice = indice - 24;

				creditosDiseno[i].setPosition(Juego.ANCHO_PANTALLA / 4 - 20, indice);

			}

			creditos.add(creditosDiseno[i]);

			nivelMenu.addActor(creditosDiseno[i]);

			indice -= 24;

		}

		indice = indice + 72;

		for (int i = 0; i < creditosDisenoNombres.length; i++) {

			creditosDisenoNombres[i] = new Label(nombresDesarrolladores[i], recurso.get("ui/uiskin.json", Skin.class));

			creditosDisenoNombres[i].setPosition(Juego.ANCHO_PANTALLA / 2 - 20, indice);

			if (i == 1) {

				indice = indice - 24;

				creditosDisenoNombres[i].setPosition(Juego.ANCHO_PANTALLA / 2 - 20, indice);

			}

			creditos.add(creditosDisenoNombres[i]);

			nivelMenu.addActor(creditosDisenoNombres[i]);

			indice -= 24;

		}

		indice = indice - 24;

		creditosArtistas = new Label("Arte y Graficos", recurso.get("ui/creditos.json", Skin.class));

		creditosArtistas.setPosition(Juego.ANCHO_PANTALLA / 4 - 20, indice);

		creditos.add(creditosArtistas);

		nivelMenu.addActor(creditosArtistas);

		for (int i = 0; i < creditosArtistasNombres.length; i++) {

			creditosArtistasNombres[i] = new Label(nombresArtistas[i], recurso.get("ui/uiskin.json", Skin.class));

			creditosArtistasNombres[i].setPosition(Juego.ANCHO_PANTALLA / 2 - 20, indice);

			creditos.add(creditosArtistasNombres[i]);

			nivelMenu.addActor(creditosArtistasNombres[i]);

			indice -= 24;

		}

		indice = indice - 24;

		creditosArtistasDeMusica = new Label("Musica", recurso.get("ui/creditos.json", Skin.class));

		creditosArtistasDeMusica.setPosition(Juego.ANCHO_PANTALLA / 4 - 20, indice);

		creditos.add(creditosArtistasDeMusica);

		nivelMenu.addActor(creditosArtistasDeMusica);

		for (int i = 0; i < creditosArtistasDeMusicaNombres.length; i++) {

			creditosArtistasDeMusicaNombres[i] = new Label(nombresArtitasDeMusica[i],
					recurso.get("ui/uiskin.json", Skin.class));

			creditosArtistasDeMusicaNombres[i].setPosition(Juego.ANCHO_PANTALLA / 2 - 20, indice);

			creditos.add(creditosArtistasDeMusicaNombres[i]);

			nivelMenu.addActor(creditosArtistasDeMusicaNombres[i]);

			indice -= 24;

		}

		indice = indice - 24;

		creditoMotor = new Label("Motor Grafico", recurso.get("ui/creditos.json", Skin.class));

		creditoMotor.setPosition(Juego.ANCHO_PANTALLA / 4 - 20, indice);

		creditos.add(creditoMotor);

		nivelMenu.addActor(creditoMotor);

		creditoMotorNombre = new Label("LibGDX - www.libgdx.com", recurso.get("ui/uiskin.json", Skin.class));

		creditoMotorNombre.setPosition(Juego.ANCHO_PANTALLA / 2 - 20, indice);

		creditos.add(creditoMotorNombre);

		nivelMenu.addActor(creditoMotorNombre);

		indice = indice - 48;

		creditosAgredecimientos = new Label("Agradecimientos", recurso.get("ui/creditos.json", Skin.class));

		creditosAgredecimientos.setPosition(Juego.ANCHO_PANTALLA / 2 - 12, indice);

		creditos.add(creditosAgredecimientos);

		nivelMenu.addActor(creditosAgredecimientos);

		indice = indice - 48;

		creditosEspecial = new Label("Gracias a Dios por todo y a mi Familia",
				recurso.get("ui/creditos.json", Skin.class));

		creditosEspecial.setPosition(Juego.ANCHO_PANTALLA / 3, indice);

		nivelMenu.addActor(creditosEspecial);

		indice = indice - 48;

		for (int i = 0; i < creditosAgredecimientosNombre.length; i++) {

			creditosAgredecimientosNombre[i] = new Label(nombres[i], recurso.get("ui/uiskin.json", Skin.class));

			creditosAgredecimientosNombre[i].setPosition(Juego.ANCHO_PANTALLA / 2 - 20, indice);

			creditos.add(creditosAgredecimientosNombre[i]);

			nivelMenu.addActor(creditosAgredecimientosNombre[i]);

			indice -= 24;

		}

		titulo = new Label("Créditos", recurso.get("ui/uieli.json", Skin.class));

		titulo.setPosition((Juego.ANCHO_PANTALLA / 2) - 64, Juego.ALTO_PANTALLA - 64);

		aceptar = new TextButton("Aceptar", recurso.get("ui/uieli.json", Skin.class));

		aceptar.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		aceptar.setPosition(Juego.ANCHO_PANTALLA / 2 - 40, 32);

		version = new Label("Version 1.0.0", recurso.get("ui/creditos.json", Skin.class));

		version.setPosition(608 - version.getWidth(), 32);

		version.setColor(0, 1, 0, 1f);

		nivelMenu.addActor(titulo);

		nivelMenu.addActor(aceptar);

		nivelMenu.addActor(version);

		mover = new float[creditos.size];

		for (int i = 0; i < creditos.size; i++) {

			mover[i] = creditos.get(i).getY();

		}

		posiciones = new float[creditos.size];

		for (int i = 0; i < creditos.size; i++) {

			float posicion = mover[i];

			posiciones[i] = posicion;

		}

		moverEspecial = creditosEspecial.getY();

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

		for (int i = 0; i < creditos.size; i++) {

			mover[i] += VELOCIDAD_CREDITOS / Juego.DELTA_A_PIXEL * delta;

			creditos.get(i).setY(mover[i]);

			if (creditos.get(i).getY() >= Juego.ALTO_PANTALLA - 120) {

				creditos.get(i).setColor(1, 1, 1, 0);

				mover[i] = posiciones[posiciones.length - 1];

				creditos.get(i).setColor(1, 1, 1, 1);

			}

		}

		moverEspecial += VELOCIDAD_CREDITOS / Juego.DELTA_A_PIXEL * delta;

		creditosEspecial.setY(moverEspecial);

		if (creditosEspecial.getY() >= Juego.ALTO_PANTALLA - 120) {

			creditosEspecial.setColor(1, 1, 1, 0);

			moverEspecial = posicionEspecial;

			creditosEspecial.setColor(1, 1, 1, 1);

		}

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