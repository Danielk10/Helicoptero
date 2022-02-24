package com.diamon.nivel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.diamon.dato.Dato;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Nivel;
import com.diamon.nucleo.Personaje;
import com.diamon.pantalla.PantallaJuego;
import com.diamon.personaje.AntiAereo;
import com.diamon.personaje.BarcoVerde;
import com.diamon.personaje.CajaBomba;
import com.diamon.personaje.CajaHelicopteroMedico;
import com.diamon.personaje.CajaHelicopteroNegro;
import com.diamon.personaje.CajaHelicopteroNormal;
import com.diamon.personaje.CajaHelicopteroRedondo;
import com.diamon.personaje.CajaHelicopteroSatelital;
import com.diamon.personaje.CajaHelicopteroVerde;
import com.diamon.personaje.CajaMisil;
import com.diamon.personaje.CajaSatelite;
import com.diamon.personaje.CajaVelocidad;
import com.diamon.personaje.CajaVida;
import com.diamon.personaje.CamionetaCarga;
import com.diamon.personaje.CamionetaGris;
import com.diamon.personaje.CamionetaVerde;
import com.diamon.personaje.CarroAmarillo;
import com.diamon.personaje.CarroGris;
import com.diamon.personaje.Fondo;
import com.diamon.personaje.Fuego;
import com.diamon.personaje.JefeCuatro;
import com.diamon.personaje.JefeDos;
import com.diamon.personaje.JefeTres;
import com.diamon.personaje.JefeUno;
import com.diamon.personaje.Jugador;
import com.diamon.personaje.MaquinaParedDerecha;
import com.diamon.personaje.MaquinaParedIzquierda;
import com.diamon.personaje.NaveFCatorce;
import com.diamon.personaje.NaveFCinco;
import com.diamon.personaje.NaveFCuatro;
import com.diamon.personaje.NaveFDiesciceis;
import com.diamon.personaje.NaveFDiez;
import com.diamon.personaje.NaveFDoce;
import com.diamon.personaje.NaveFDos;
import com.diamon.personaje.NaveFNueve;
import com.diamon.personaje.NaveFOcho;
import com.diamon.personaje.NaveFOnce;
import com.diamon.personaje.NaveFQuince;
import com.diamon.personaje.NaveFSeis;
import com.diamon.personaje.NaveFSiete;
import com.diamon.personaje.NaveFTrece;
import com.diamon.personaje.NaveFTres;
import com.diamon.personaje.NaveFUno;
import com.diamon.personaje.NubeCinco;
import com.diamon.personaje.NubeCuatro;
import com.diamon.personaje.NubeDos;
import com.diamon.personaje.NubeLarga;
import com.diamon.personaje.NubeSeis;
import com.diamon.personaje.NubeSiete;
import com.diamon.personaje.NubeTres;
import com.diamon.personaje.NubeUno;
import com.diamon.personaje.PlatilloA;
import com.diamon.personaje.PlatilloDeLuz;
import com.diamon.personaje.PlatilloVolador;
import com.diamon.personaje.Robot;
import com.diamon.personaje.Sierra;
import com.diamon.personaje.Terreno;

public class Niveles extends Nivel {

	private TiledMapRenderer render;

	private float xFondo = camara.position.x - Juego.ANCHO_PANTALLA / 2;

	private NaveFUno actorIntro1;

	private NaveFUno actorIntro2;

	private NaveFUno actorIntro3;

	private NaveFUno actorIntro4;

	private JefeUno gefeUno;

	private JefeDos gefeDos;

	private JefeTres gefeTres;

	private JefeCuatro gefeCuatro;

	private boolean parallax;

	public Niveles(PantallaJuego pantalla, Jugador jugador, TiledMap[] mapas) {

		super(pantalla, jugador);

		int indice = 1;

		mapa = mapas[dato.getNumeroNivel() - 1];

		render = new OrthogonalTiledMapRenderer(mapa, 1);

		if ((dato.getNumeroNivel() <= 10)) {

			for (int i = 0; i < fondo.length; i++) {

				indice = 1;

				parallax = false;

				fondo[i] = new Fondo(
						new TextureRegion(recurso.get("textura/fondoNivel" + indice + ".png", Texture.class)),
						pantalla);

				fondo[i].setSize(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

				fondo[i].setPosition(0, 0);

				personajes.add(fondo[i]);
			}

		}

		if ((dato.getNumeroNivel() >= 11) && (dato.getNumeroNivel() <= 20)) {

			indice = 2;

			parallax = false;

			for (int i = 0; i < fondo.length; i++) {

				fondo[i] = new Fondo(
						new TextureRegion(recurso.get("textura/fondoNivel" + indice + ".png", Texture.class)),
						pantalla);

				fondo[i].setSize(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

				fondo[i].setPosition(0, 0);

				personajes.add(fondo[i]);
			}

		}

		if ((dato.getNumeroNivel() >= 21) && (dato.getNumeroNivel() <= 30)) {

			indice = 3;

			parallax = true;

			for (int i = 0; i < fondo.length; i++) {

				fondo[i] = new Fondo(
						new TextureRegion(recurso.get("textura/fondoNivel" + indice + ".png", Texture.class)),
						pantalla);

				fondo[i].setSize(Juego.ANCHO_PANTALLA + 2, Juego.ALTO_PANTALLA);

				fondo[i].setPosition(0, 0);

				personajes.add(fondo[i]);
			}

		}

		if ((dato.getNumeroNivel() >= 31) && (dato.getNumeroNivel() <= 40)) {

			indice = 4;

			parallax = false;

			for (int i = 0; i < fondo.length; i++) {

				fondo[i] = new Fondo(
						new TextureRegion(recurso.get("textura/fondoNivel" + indice + ".png", Texture.class)),
						pantalla);

				fondo[i].setSize(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

				fondo[i].setPosition(0, 0);

				personajes.add(fondo[i]);
			}

		}

		String numeroNivel = "Nivel " + dato.getNumeroNivel();

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_LARGA, numeroNivel))

		{
			NubeLarga actor = new NubeLarga(new TextureRegion(recurso.get("textura/nube.png", Texture.class)),
					pantalla);

			actor.setSize(194, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_UNO, numeroNivel))

		{
			NubeUno actor = new NubeUno(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube1"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_DOS, numeroNivel))

		{
			NubeDos actor = new NubeDos(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube2"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_TRES, numeroNivel))

		{
			NubeTres actor = new NubeTres(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube3"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_CUATRO, numeroNivel))

		{
			NubeCuatro actor = new NubeCuatro(
					recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube4"), pantalla);

			actor.setSize(64, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_CINCO, numeroNivel))

		{
			NubeCinco actor = new NubeCinco(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube5"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_SEIS, numeroNivel))

		{
			NubeSeis actor = new NubeSeis(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube6"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NUBE_SIETE, numeroNivel))

		{
			NubeSiete actor = new NubeSiete(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube7"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		MapLayer capa = mapa.getLayers().get("terreno");

		Terreno[] terreno = new Terreno[capa.getObjects().getCount()];

		for (MapObject objetosMapa : capa.getObjects()) {

			int i = 0;

			if (objetosMapa instanceof RectangleMapObject) {

				Rectangle r = ((RectangleMapObject) objetosMapa).getRectangle();

				terreno[i] = new Terreno(new TextureRegion(recurso.get("textura/invisible.png", Texture.class)),
						pantalla);

				terreno[i].setSize(r.width, r.height);

				terreno[i].setPosition(r.x, r.y);

				personajes.add(terreno[i]);
			}

			i++;
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.PLATILLO_VOLADOR, numeroNivel)) {

			PlatilloVolador actor = new PlatilloVolador(

					recurso.get("textura/platilloVolador.atlas", TextureAtlas.class).getRegions(), 0.2f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(64, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(3.5f);

			actor.setDistanciaDesplazamientoY(100);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.PLATILLO_A, numeroNivel)) {

			PlatilloA actor = new PlatilloA(recurso.get("textura/ovni.atlas", TextureAtlas.class).getRegions(), 0.2f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(64, 24);

			actor.setDureza(7);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDuracionDisparo(0.5f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.PLATILLO_DE_LUZ, numeroNivel)) {

			PlatilloDeLuz actor = new PlatilloDeLuz(
					recurso.get("textura/platilloDeLuz.atlas", TextureAtlas.class).getRegions(), 0.08f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(64, 24);

			actor.setDureza(7);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDuracionDisparo(1.6f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_UNO, numeroNivel)) {

			NaveFUno actor = new NaveFUno(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_DOS, numeroNivel)) {

			NaveFDos actor = new NaveFDos(recurso.get("textura/aliengris.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_TRES, numeroNivel)) {

			NaveFTres actor = new NaveFTres(recurso.get("textura/alienpurpura.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_CUATRO, numeroNivel)) {

			NaveFCuatro actor = new NaveFCuatro(recurso.get("textura/alienrojo.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_CINCO, numeroNivel)) {

			NaveFCinco actor = new NaveFCinco(recurso.get("textura/alienverde.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_SEIS, numeroNivel)) {

			NaveFSeis actor = new NaveFSeis(recurso.get("textura/alienverde2.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_SIETE, numeroNivel)) {

			NaveFSiete actor = new NaveFSiete(
					recurso.get("textura/aliennaranja.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_OCHO, numeroNivel)) {

			NaveFOcho actor = new NaveFOcho(recurso.get("textura/aliennaranja2.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDuracionDisparo(0.2f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_NUEVE, numeroNivel)) {

			NaveFNueve actor = new NaveFNueve(recurso.get("textura/tripleazul.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(5);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_DIEZ, numeroNivel)) {

			NaveFDiez actor = new NaveFDiez(recurso.get("textura/triplegris.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(5);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_ONCE, numeroNivel)) {

			NaveFOnce actor = new NaveFOnce(recurso.get("textura/triplepurpura.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(5);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_DOCE, numeroNivel)) {

			NaveFDoce actor = new NaveFDoce(recurso.get("textura/tripleverde.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(7);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_TRECE, numeroNivel)) {

			NaveFTrece actor = new NaveFTrece(recurso.get("textura/triplerojo.atlas", TextureAtlas.class).getRegions(),
					0.1f, Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(5);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_CATORCE, numeroNivel)) {

			NaveFCatorce actor = new NaveFCatorce(
					recurso.get("textura/tripleverde2.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(5);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_QUINCE, numeroNivel)) {

			NaveFQuince actor = new NaveFQuince(
					recurso.get("textura/triplenaranja.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(7);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.NAVE_F_DIESCICEIS, numeroNivel)) {

			NaveFDiesciceis actor = new NaveFDiesciceis(
					recurso.get("textura/triplenaranja2.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(48, 48);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDistanciaMovimientoY(50);

			actor.setVelocidadX(2);

			actor.setVelocidadY(200);

			actor.setDureza(5);

			actor.setDuracionDisparo(0.33f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.ANTI_AEREO, numeroNivel)) {

			AntiAereo actor = new AntiAereo(recurso.get("textura/antiAereo.atlas", TextureAtlas.class).getRegions(),
					0.2f, Animation.PlayMode.NORMAL, pantalla);

			actor.setSize(48, 48);

			actor.setDureza(7);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDuracionDisparo(0.5f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.SIERRA, numeroNivel)) {

			Sierra actor = new Sierra(recurso.get("textura/sierra.atlas", TextureAtlas.class).getRegions(), 0.05f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(42, 42);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CARRO_GRIS, numeroNivel)) {

			CarroGris actor = new CarroGris(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("Carro1"), pantalla);

			actor.setSize(64, 23);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CARRO_AMARILLO, numeroNivel)) {

			CarroAmarillo actor = new CarroAmarillo(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("Carro2"), pantalla);

			actor.setSize(64, 23);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAMIONETA_CARGA, numeroNivel)) {

			CamionetaCarga actor = new CamionetaCarga(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta1"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAMIONETA_GRIS, numeroNivel)) {

			CamionetaGris actor = new CamionetaGris(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta2"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAMIONETA_VERDE, numeroNivel)) {

			CamionetaVerde actor = new CamionetaVerde(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta3"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.BARCO_VERDE, numeroNivel))

		{
			BarcoVerde actor = new BarcoVerde(new TextureRegion(recurso.get("textura/barcoVerde.png", Texture.class)),
					pantalla);

			actor.setSize(256, 98);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);
		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.ROBOT, numeroNivel)) {

			Robot actor = new Robot(recurso.get("textura/robot.atlas", TextureAtlas.class).getRegions(), 0.3f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(64, 64);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDureza(12);

			actor.setDuracionDisparo(1f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.MAQUINA_PARED_IZQUIERDA, numeroNivel)) {

			MaquinaParedIzquierda actor = new MaquinaParedIzquierda(
					recurso.get("textura/maquinaPared.atlas", TextureAtlas.class).getRegions(), 0.3f,
					Animation.PlayMode.NORMAL, pantalla);

			actor.setSize(32, 64);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDureza(8);

			actor.setDuracionDisparo(0.5f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.MAQUINA_PARED_DERECHA, numeroNivel)) {

			MaquinaParedDerecha actor = new MaquinaParedDerecha(
					recurso.get("textura/maquinaParedD.atlas", TextureAtlas.class).getRegions(), 0.3f,
					Animation.PlayMode.NORMAL, pantalla);

			actor.setSize(32, 64);

			actor.setPosition(posicion.x, posicion.y);

			actor.setDureza(8);

			actor.setDuracionDisparo(0.5f);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_VIDA, numeroNivel)) {

			CajaVida actor = new CajaVida(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja1"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_MISIL, numeroNivel)) {

			CajaMisil actor = new CajaMisil(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja4"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_BOMBA, numeroNivel)) {

			CajaBomba actor = new CajaBomba(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja3"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_SATELITE, numeroNivel)) {

			CajaSatelite actor = new CajaSatelite(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja2"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_VELOCIDAD, numeroNivel)) {

			CajaVelocidad actor = new CajaVelocidad(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja5"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_HELICOPTERO_NORMAL, numeroNivel)) {

			CajaHelicopteroNormal actor = new CajaHelicopteroNormal(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja6"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_HELICOPTERO_REDONDO, numeroNivel)) {

			CajaHelicopteroRedondo actor = new CajaHelicopteroRedondo(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja9"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_HELICOPTERO_NEGRO, numeroNivel)) {

			CajaHelicopteroNegro actor = new CajaHelicopteroNegro(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja11"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_HELICOPTERO_MEDICO, numeroNivel)) {

			CajaHelicopteroMedico actor = new CajaHelicopteroMedico(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja10"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_HELICOPTERO_VERDE, numeroNivel)) {

			CajaHelicopteroVerde actor = new CajaHelicopteroVerde(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja8"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.CAJA_HELICOPTERO_SATELITAL, numeroNivel)) {

			CajaHelicopteroSatelital actor = new CajaHelicopteroSatelital(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja7"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(posicion.x, posicion.y);

			actor.setVelocidadY(400);

			actor.setDistanciaMovimientoY(20);

			personajes.add(actor);

		}

		for (Vector2 posicion : dato.getPosicionActores(Dato.FUEGO, numeroNivel)) {

			Fuego actor = new Fuego(recurso.get("textura/fuego.atlas", TextureAtlas.class).getRegions(), 0.05f,
					Animation.PlayMode.LOOP, pantalla);

			actor.setSize(32, 64);

			actor.setPosition(posicion.x, posicion.y);

			personajes.add(actor);

		}

		agregarGefe(dato.getNumeroNivel());

		intro();

	}

	public void intro() {

		intro = (dato.getNumeroNivel() == 1);

		this.moverCamara = !intro;

		if (intro) {

			actorIntro1 = new NaveFUno(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actorIntro1.setSize(48, 48);

			actorIntro1.setPosition(550, 100);

			actorIntro1.setDistanciaMovimientoY(50);

			actorIntro1.setVelocidadX(2);

			actorIntro1.setVelocidadY(200);

			actorIntro1.setDuracionDisparo(0.33f);

			actorIntro1.setLado(NaveFUno.IZQUIERDO);

			personajes.add(actorIntro1);

			actorIntro2 = new NaveFUno(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actorIntro2.setSize(48, 48);

			actorIntro2.setPosition(640, 70);

			actorIntro2.setDistanciaMovimientoY(50);

			actorIntro2.setVelocidadX(2);

			actorIntro2.setVelocidadY(200);

			actorIntro2.setDuracionDisparo(0.33f);

			actorIntro2.setLado(NaveFUno.IZQUIERDO);

			personajes.add(actorIntro2);

			actorIntro3 = new NaveFUno(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actorIntro3.setSize(48, 48);

			actorIntro3.setPosition(500, 230);

			actorIntro3.setDistanciaMovimientoY(50);

			actorIntro3.setVelocidadX(2);

			actorIntro3.setVelocidadY(200);

			actorIntro3.setDuracionDisparo(0.33f);

			actorIntro3.setLado(NaveFUno.IZQUIERDO);

			personajes.add(actorIntro3);

			actorIntro4 = new NaveFUno(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			actorIntro4.setSize(48, 48);

			actorIntro4.setPosition(640, 200);

			actorIntro4.setDistanciaMovimientoY(50);

			actorIntro4.setVelocidadX(2);

			actorIntro4.setVelocidadY(200);

			actorIntro4.setDuracionDisparo(0.33f);

			actorIntro4.setLado(NaveFUno.IZQUIERDO);

			personajes.add(actorIntro4);

			jugador.setPosition(-320, 384);

			jugador.setIntro(!intro);

			nivel.addAction(Actions.sequence(Actions.delay(3f), Actions.run(new Runnable() {

				public void run() {

					actorIntro1.setLado(NaveFUno.DERECHO);
					actorIntro2.setLado(NaveFUno.DERECHO);
					actorIntro3.setLado(NaveFUno.DERECHO);
					actorIntro4.setLado(NaveFUno.DERECHO);

				}
			})));

			nivel.addAction(Actions.sequence(Actions.delay(9f), Actions.run(new Runnable() {

				public void run() {

					intro = false;

					moverCamara = !intro;

					jugador.setIntro(!intro);

				}
			})));

		} else {

			jugador.setIntro(!intro);

		}

	}

	private void agregarGefe(int numero) {

		if (numero == 10) {

			gefeUno = new JefeUno(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			gefeUno.setSize(128, 128);

			gefeUno.setPosition(13440 - gefeUno.getWidth(), 240);

			gefeUno.setDistanciaMovimientoY(50);

			gefeUno.setVelocidadX(2);

			gefeUno.setVelocidadY(200);

			gefeUno.setDureza(300);

			gefeUno.setDuracionDisparo(0.33f);

			personajes.add(gefeUno);

		}

		if (numero == 20) {

			gefeDos = new JefeDos(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			gefeDos.setSize(128, 128);

			gefeDos.setPosition(13440 - gefeDos.getWidth(), 240);

			gefeDos.setDureza(500);

			personajes.add(gefeDos);

		}

		if (numero == 30) {

			gefeTres = new JefeTres(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			gefeTres.setSize(128, 128);

			gefeTres.setPosition(13440 - gefeTres.getWidth(), 240);

			gefeTres.setDureza(700);

			personajes.add(gefeTres);

		}

		if (numero == 40) {

			gefeCuatro = new JefeCuatro(recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions(), 0.1f,
					Animation.PlayMode.LOOP, pantalla);

			gefeCuatro.setSize(128, 128);

			gefeCuatro.setPosition(13440 - gefeCuatro.getWidth(), 240);

			gefeCuatro.setDureza(900);

			personajes.add(gefeCuatro);

		}

	}

	@Override
	protected void iniciar() {

		camara.position.x = Juego.ANCHO_PANTALLA / 2;

		camara.position.y = Juego.ANCHO_PANTALLA / 2;

		camara.zoom = 1;

		camara.setToOrtho(false, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

		camara.update();

		pincel.setProjectionMatrix(camara.combined);

		moverCamara = true;

		jugador.setPosition(0, 384);

		jugador.setVivo(true);

		jugador.setFinNivel(false);

		jugador.setTerminarNivel(false);

		jugador.resetearJugador();

		personajes.add(jugador);
	}

	@Override
	public void dibujar(Batch pincel, float delta) {

		pincel.begin();

		for (Personaje personaje : personajes) {

			if (personaje instanceof NubeLarga || personaje instanceof NubeUno || personaje instanceof NubeDos
					|| personaje instanceof NubeTres || personaje instanceof NubeCuatro
					|| personaje instanceof NubeCinco || personaje instanceof NubeSeis || personaje instanceof NubeSiete
					|| personaje instanceof Fondo) {

				personaje.dibujar(pincel, delta);
			}
		}

		pincel.end();

		render.setView(camara);

		render.render();

		pincel.begin();

		for (Personaje personaje : personajes) {

			if (!(personaje instanceof NubeLarga) && !(personaje instanceof NubeUno) && !(personaje instanceof NubeDos)
					&& !(personaje instanceof NubeTres) && !(personaje instanceof NubeCuatro)
					&& !(personaje instanceof NubeCinco) && !(personaje instanceof NubeSeis)
					&& !(personaje instanceof NubeSiete) && !(personaje instanceof Fondo)) {

				personaje.dibujar(pincel, delta);
			}
		}

		pincel.end();

	}

	@Override
	public void actualizar(float delta) {

		if (!jugador.isFinNivel() && jugador.isVivo()) {

			if (!parallax) {

				if (dato.isFondoScroll()) {

					if (xFondo <= camara.position.x - Juego.ANCHO_PANTALLA / 2 - Juego.ANCHO_PANTALLA) {

						xFondo = camara.position.x - Juego.ANCHO_PANTALLA / 2;
					}

					fondo[0].setPosition(xFondo, 0);

					fondo[1].setPosition(xFondo + Juego.ANCHO_PANTALLA, 0);

				}

			}

			if (dato.isFondoParallax()) {

				fondo[1].setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

			} else {

				if (parallax) {

					fondo[1].setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

		}

		for (Personaje personaje : personajes) {

			personaje.actualizar(delta);
		}

		if (Juego.ANCHO_PANTALLA / 2 + camara.position.x >= Juego.LARGO_NIVEL) {

			if (dato.getNumeroNivel() == 10 || dato.getNumeroNivel() == 20 || dato.getNumeroNivel() == 30
					|| dato.getNumeroNivel() == 40) {

				jugador.setGefe(true);

				moverCamara = false;

				if (gefeUno != null) {

					if (gefeUno.getDureza() <= 0) {

						gefeUno.setVivo(false);

						if (!gefeUno.isVivo()) {

							jugador.setGefe(false);

							jugador.setFinNivel(true);
						}

					}

				}

				if (gefeDos != null) {

					if (gefeDos.getDureza() <= 0) {

						gefeDos.setVivo(false);

						if (!gefeDos.isVivo()) {

							jugador.setGefe(false);

							jugador.setFinNivel(true);
						}

					}

				}

				if (gefeTres != null) {

					if (gefeTres.getDureza() <= 0) {

						gefeTres.setVivo(false);

						if (!gefeTres.isVivo()) {

							jugador.setGefe(false);

							jugador.setFinNivel(true);
						}

					}

				}

				if (gefeCuatro != null) {

					if (gefeCuatro.getDureza() <= 0) {

						gefeCuatro.setVivo(false);

						if (!gefeCuatro.isVivo()) {

							jugador.setGefe(false);

							jugador.setFinNivel(true);
						}

					}

				}

			} else {

				moverCamara = false;

				jugador.setFinNivel(true);

			}

		}

		if (!jugador.isVivo()) {

			moverCamara = false;
		}

		if (moverCamara) {

			if (jugador.isItemVelocidad()) {

				camara.position.x += jugador.getVelocidadCamaraItem() / Juego.DELTA_A_PIXEL * delta;

			} else {

				camara.position.x += Juego.VELOCIDAD_CAMARA / Juego.DELTA_A_PIXEL * delta;

			}

		}

	}

	@Override
	public void liberarRecursos() {

		personajes.clear();

		mapa.dispose();

	}

	@Override
	public void guardarDatos() {

	}
}
