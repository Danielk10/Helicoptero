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

		String[] autores = { "Helicoptero", "2021 - 2022  Daniel Diamon y Diamond Black" };

		String[] titulosDesarrolladores = { "Diseño del Juego", "y Programación", "Diseñador Grafico" };

		String[] nombresDesarrolladores = { "Daniel Diamon", "Jesus Mnedez" };

		String[] nombresArtistas = { "GameArt2D - Zuhria Alfitra - pzUH (www.gameart2d.com)",
				"Carlos Alface - kalface@gmail.com (www.carlosalface.blogspot.pt - www.opengameart.org)",
				"cemkalyoncu (www.opengameart.org)", "yd (www.opengameart.org)", "mishonis (www.opengameart.org)",
				"2dGameCreation (www.opengameart.org)", "knik1985 (www.opengameart.org)", "www.enchantjs.com",
				"Tiled (www.mapeditor.org)", "paint.net (www.getpaint.net)", "Skin Composer (www.ray3k.wordpress.com)",
				"GDX Texture Packer (www. crashinvaders.com)", "Hiero (www.libgdx.com)", "www.libgdx.com",
				"www.reygif.com", "RAY3K", "Daniel Diamon", "Jesus Mendez" };

		String[] nombresArtitasDeMusica = { "sfxr (www.drpetter.se)", "Jobromedia (www.opengameart.org)",
				"Alexandr Zhelanov (www.opengameart.org)", "Tozan (www.opengameart.org)",
				"Matthew Pablo (www.opengameart.org)", "Zydro (www.opengameart.org)", "Daniel Diamon",

		};

		String[] nombres = { "Alexander Hristov", "Jesus Code", "Nacho Cabanes", "Angel Navarro Baeza", "Yayo Arellano",
				"Luis Diamon", "Kelwins Mosquera", "Natalia Mosquera", "Herith Ducey", "Lucino Dulcey", "Ruth Celis",
				"Anye Mosquera", "Kelwins Mosquera", "Yelitza Vazquez", "Rafael Diamon", "Vanessa Diamon",
				"Jordan Rivas", "Angel Leonardo Reina", "Javier Narea", "Merlyannis Garcia", "Jennifer Rivero",
				"Luis Jose Garcia", "Veronica Mendez", "Ricardo Gonzalez Santamaria", "Jenina Estefania Santamaria" };

		creditosAutores = new Label[autores.length];

		creditosAgredecimientosNombre = new Label[nombres.length];

		creditosDiseno = new Label[titulosDesarrolladores.length];

		creditosDisenoNombres = new Label[nombresDesarrolladores.length];

		creditosArtistasNombres = new Label[nombresArtistas.length];

		creditosArtistasDeMusicaNombres = new Label[nombresArtitasDeMusica.length];

		creditosAutores[0] = new Label(autores[0], recurso.get("ui/creditos.json", Skin.class));

		creditosAutores[0].setPosition(Juego.ANCHO_PANTALLA / 2 - 50, 374);

		nivelMenu.addActor(creditosAutores[0]);

		creditosAutores[1] = new Label(autores[1], recurso.get("ui/creditos.json", Skin.class));

		creditosAutores[1].setPosition(Juego.ANCHO_PANTALLA / 4, 344);

		nivelMenu.addActor(creditosAutores[1]);

		int posicionDiseNonombre = 264;

		for (int i = 0; i < creditosDisenoNombres.length; i++) {

			creditosDisenoNombres[i] = new Label(nombresDesarrolladores[i], recurso.get("ui/uiskin.json", Skin.class));

			creditosDisenoNombres[i].setPosition(Juego.ANCHO_PANTALLA / 2, posicionDiseNonombre);

			nivelMenu.addActor(creditosDisenoNombres[i]);

			posicionDiseNonombre -= 24;

		}

		int posicionDiseno = 288;

		for (int i = 0; i < creditosDiseno.length; i++) {

			creditosDiseno[i] = new Label(titulosDesarrolladores[i], recurso.get("ui/creditos.json", Skin.class));

			creditosDiseno[i].setPosition(Juego.ANCHO_PANTALLA / 4, posicionDiseno);

			nivelMenu.addActor(creditosDiseno[i]);

			posicionDiseno -= 24;

		}

		creditosArtistas = new Label("Arte y Graficos", recurso.get("ui/creditos.json", Skin.class));

		creditosArtistas.setPosition(Juego.ANCHO_PANTALLA / 4, 216);

		nivelMenu.addActor(creditosArtistas);

		int posicionArtista = 216;

		for (int i = 0; i < creditosArtistasNombres.length; i++) {

			creditosArtistasNombres[i] = new Label(nombresArtistas[i], recurso.get("ui/uiskin.json", Skin.class));

			creditosArtistasNombres[i].setPosition(Juego.ANCHO_PANTALLA / 2, posicionArtista);

			nivelMenu.addActor(creditosArtistasNombres[i]);

			posicionArtista -= 24;

		}

		creditosArtistasDeMusica = new Label("Musica", recurso.get("ui/creditos.json", Skin.class));

		creditosArtistasDeMusica.setPosition(Juego.ANCHO_PANTALLA / 4, -240);

		nivelMenu.addActor(creditosArtistasDeMusica);

		int posicionArtistaMusica = -240;

		for (int i = 0; i < creditosArtistasDeMusicaNombres.length; i++) {

			creditosArtistasDeMusicaNombres[i] = new Label(nombresArtitasDeMusica[i],
					recurso.get("ui/uiskin.json", Skin.class));

			creditosArtistasDeMusicaNombres[i].setPosition(Juego.ANCHO_PANTALLA / 2, posicionArtistaMusica);

			nivelMenu.addActor(creditosArtistasDeMusicaNombres[i]);

			posicionArtistaMusica -= 24;

		}

		creditoMotor = new Label("Motor Grafico", recurso.get("ui/creditos.json", Skin.class));

		creditoMotor.setPosition(Juego.ANCHO_PANTALLA / 4, -428);

		nivelMenu.addActor(creditoMotor);

		creditoMotorNombre = new Label("LibdGDX (www.libgdx.com)", recurso.get("ui/creditos.json", Skin.class));

		creditoMotorNombre.setPosition(Juego.ANCHO_PANTALLA / 2, -428);

		nivelMenu.addActor(creditoMotorNombre);

		creditosAgredecimientos = new Label("Agradecimientos", recurso.get("ui/creditos.json", Skin.class));

		creditosAgredecimientos.setPosition(Juego.ANCHO_PANTALLA / 2 - 12, -476);

		nivelMenu.addActor(creditosAgredecimientos);

		creditosEspecial = new Label("Gracias a Dios por todo y a mi Familia",
				recurso.get("ui/creditos.json", Skin.class));

		creditosEspecial.setPosition(Juego.ANCHO_PANTALLA / 3, -500);

		nivelMenu.addActor(creditosEspecial);

		int posicionAgradesimientos = -524;

		for (int i = 0; i < creditosAgredecimientosNombre.length; i++) {

			creditosAgredecimientosNombre[i] = new Label(nombres[i], recurso.get("ui/uiskin.json", Skin.class));

			creditosAgredecimientosNombre[i].setPosition(Juego.ANCHO_PANTALLA / 2, posicionAgradesimientos);

			nivelMenu.addActor(creditosAgredecimientosNombre[i]);

			posicionAgradesimientos -= 24;

		}

		titulo = new Label("Créditos", recurso.get("ui/uieli.json", Skin.class));

		titulo.setPosition((Juego.ANCHO_PANTALLA / 2) - 64, Juego.ALTO_PANTALLA - 64);

		aceptar = new TextButton("Aceptar", recurso.get("ui/uieli.json", Skin.class));

		aceptar.setSize(Juego.ANCHO_PANTALLA / 8, 32);

		aceptar.setPosition(Juego.ANCHO_PANTALLA / 2 - 40, 32);

		version = new Label("Version 1.0.0", recurso.get("ui/uiskin.json", Skin.class));

		version.setPosition(608 - version.getWidth(), 32);

		nivelMenu.addActor(titulo);

		nivelMenu.addActor(aceptar);

		nivelMenu.addActor(version);

		mover = new float[creditosArtistasNombres.length + creditosAgredecimientosNombre.length + creditosAutores.length
				+ creditosDiseno.length + creditosDisenoNombres.length + creditosArtistasDeMusicaNombres.length + 6];

		int i = 0;

		for (Label texto : creditosArtistasNombres) {

			mover[i] = texto.getY();

			i += 1;

		}

		for (Label texto : creditosAgredecimientosNombre) {

			mover[i] = texto.getY();

			i += 1;

		}

		for (Label texto : creditosAutores) {

			mover[i] = texto.getY();

			i += 1;

		}

		for (Label texto : creditosDiseno) {

			mover[i] = texto.getY();

			i += 1;

		}

		for (Label texto : creditosDisenoNombres) {

			mover[i] = texto.getY();

			i += 1;

		}

		for (Label texto : creditosArtistasDeMusicaNombres) {

			mover[i] = texto.getY();

			i += 1;

		}

		mover[mover.length - 6] = creditoMotor.getY();

		mover[mover.length - 5] = creditoMotorNombre.getY();

		mover[mover.length - 4] = creditosEspecial.getY();

		mover[mover.length - 3] = creditosAgredecimientos.getY();

		mover[mover.length - 2] = creditosArtistas.getY();

		mover[mover.length - 1] = creditosArtistasDeMusica.getY();

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

		int i = 0;

		for (Label texto : creditosArtistasNombres) {

			mover[i] += 0.5f / Juego.DELTA_A_PIXEL * delta;

			texto.setY(mover[i]);

			i += 1;

		}

		for (Label texto : creditosAgredecimientosNombre) {

			mover[i] += 0.5f / Juego.DELTA_A_PIXEL * delta;

			texto.setY(mover[i]);

			i += 1;

		}

		for (Label texto : creditosAutores) {

			mover[i] += 0.5f / Juego.DELTA_A_PIXEL * delta;

			texto.setY(mover[i]);

			i += 1;

		}

		for (Label texto : creditosDiseno) {

			mover[i] += 0.5f / Juego.DELTA_A_PIXEL * delta;

			texto.setY(mover[i]);

			i += 1;

		}

		for (Label texto : creditosDisenoNombres) {

			mover[i] += 0.5f / Juego.DELTA_A_PIXEL * delta;

			texto.setY(mover[i]);

			i += 1;

		}

		for (Label texto : creditosArtistasDeMusicaNombres) {

			mover[i] += 0.5f / Juego.DELTA_A_PIXEL * delta;

			texto.setY(mover[i]);

			i += 1;

		}

		mover[mover.length - 6] += 0.5f / Juego.DELTA_A_PIXEL * delta;

		mover[mover.length - 5] += 0.5f / Juego.DELTA_A_PIXEL * delta;

		mover[mover.length - 4] += 0.5f / Juego.DELTA_A_PIXEL * delta;

		mover[mover.length - 3] += 0.5f / Juego.DELTA_A_PIXEL * delta;

		mover[mover.length - 2] += 0.5f / Juego.DELTA_A_PIXEL * delta;

		mover[mover.length - 1] += 0.5f / Juego.DELTA_A_PIXEL * delta;

		creditoMotor.setY(mover[mover.length - 6]);

		creditoMotorNombre.setY(mover[mover.length - 5]);

		creditosEspecial.setY(mover[mover.length - 4]);

		creditosAgredecimientos.setY(mover[mover.length - 3]);

		creditosArtistas.setY(mover[mover.length - 2]);

		creditosArtistasDeMusica.setY(mover[mover.length - 1]);

		/*
		 * if (mover <= -Juego.ANCHO_PANTALLA) {
		 * 
		 * mover = 0; }
		 */

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