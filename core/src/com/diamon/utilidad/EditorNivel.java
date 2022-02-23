package com.diamon.utilidad;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.diamon.dato.Configuraciones;
import com.diamon.dato.Dato;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;
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
import com.diamon.personaje.Cursor;
import com.diamon.personaje.Fondo;
import com.diamon.personaje.Fuego;
import com.diamon.personaje.Jugador;
import com.diamon.personaje.MaquinaParedDerecha;
import com.diamon.personaje.MaquinaParedIzquierda;
import com.diamon.personaje.NaveFUno;
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
import com.diamon.personaje.NubeLarga;
import com.diamon.personaje.NubeCinco;
import com.diamon.personaje.NubeCuatro;
import com.diamon.personaje.NubeDos;
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

public class EditorNivel {

	private OrthographicCamera camara;

	private Vector3 corriendo;

	private Vector3 detras;

	private Vector3 delta;

	private Array<Personaje> personajes;

	private Pantalla pantalla;

	private AssetManager recurso;

	private boolean agregar;

	private boolean moverEnY;

	private boolean terminar;

	protected Stage nivel;

	private Configuraciones configuracion;

	private Dato dato;

	protected String tipo;

	private TextButton borrarActores;

	private TextButton agreagarActores;

	private TextButton moverEsenario;

	private TextButton derecha;

	private TextButton izquierda;

	private boolean pD, pI;

	private TextButton moverEsenarioEnY;

	private Label zoomCamara;

	private Label mundo;

	private TextButton zoomCamaraMas;

	private TextButton zoomCamaraMenos;

	private TextButton fondoScroll;

	private TextButton fondoParallax;

	private TextButton actualizarActores;

	private SelectBox<String> tipoActor;

	private SelectBox<String> numeroNivel;

	private Cursor cursor;

	private boolean actualizar;

	private boolean toque;

	private int velocidadCamara;

	public EditorNivel(final Stage nivel, final Configuraciones configuracion, final Dato dato,
			final OrthographicCamera camara, final Array<Personaje> personajes, final Pantalla pantalla,
			final AssetManager recurso, Cursor cursor) {

		this.camara = camara;

		this.configuracion = configuracion;

		this.dato = dato;

		this.personajes = personajes;

		this.recurso = recurso;

		this.pantalla = pantalla;

		this.nivel = nivel;

		this.cursor = cursor;

		velocidadCamara = 5;

		pD = pI = false;

		actualizar = false;

		toque = false;

		agregar = false;

		moverEnY = false;

		terminar = true;

		tipo = "";

		corriendo = new Vector3();

		detras = new Vector3(-1, -1, -1);

		delta = new Vector3();

		ui();

		eventos();

	}

	private void ui() {

		borrarActores = new TextButton("Borrar Actores", recurso.get("ui/uiskin.json", Skin.class));

		borrarActores.setSize(160, 32);

		borrarActores.setPosition(16, 16);

		borrarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		derecha = new TextButton("Derecha +", recurso.get("ui/uiskin.json", Skin.class));

		derecha.setSize(96, 32);

		derecha.setPosition(432, 16);

		derecha.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		izquierda = new TextButton("Izquierda -", recurso.get("ui/uiskin.json", Skin.class));

		izquierda.setSize(96, 32);

		izquierda.setPosition(336, 16);

		izquierda.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		actualizarActores = new TextButton("Actualizar Actores", recurso.get("ui/uiskin.json", Skin.class));

		actualizarActores.setSize(160, 32);

		actualizarActores.setPosition(176, 16);

		actualizarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		numeroNivel = new SelectBox<String>(recurso.get("ui/uiskin.json", Skin.class));

		numeroNivel.setSize(64, 32);

		numeroNivel.setPosition(64, 416);

		numeroNivel.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		String[] niveles = new String[40];

		for (int i = 0; i < niveles.length; i++) {

			niveles[i] = "" + (i + 1);

		}

		numeroNivel.setItems(niveles);

		numeroNivel.setSelectedIndex(dato.getNumeroNivel() - 1);

		mundo = new Label("Nivel: ", recurso.get("ui/uiskin.json", Skin.class), "default-font", Color.GREEN);

		mundo.setSize(96, 32);

		mundo.setPosition(16, 416);

		mundo.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		agreagarActores = new TextButton("Agregar Actores", recurso.get("ui/uiskin.json", Skin.class));

		agreagarActores.setSize(144, 32);

		agreagarActores.setPosition(16, 352);

		agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		moverEsenario = new TextButton("Mover Escenario", recurso.get("ui/uiskin.json", Skin.class));

		moverEsenario.setSize(144, 32);

		moverEsenario.setPosition(16, 448);

		moverEsenario.setColor(1, 0, 0, 1);

		moverEsenarioEnY = new TextButton("Mover Escenario Y", recurso.get("ui/uiskin.json", Skin.class));

		moverEsenarioEnY.setSize(144, 32);

		moverEsenarioEnY.setPosition(160, 448);

		moverEsenarioEnY.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		fondoScroll = new TextButton("Fondo Scroll", recurso.get("ui/uiskin.json", Skin.class));

		fondoScroll.setSize(144, 32);

		fondoScroll.setPosition(160, 416);

		if (dato.isFondoScroll()) {

			fondoScroll.setColor(1, 0, 0, 1);

		} else {

			fondoScroll.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		}

		fondoParallax = new TextButton("Fondo Parallax", recurso.get("ui/uiskin.json", Skin.class));

		fondoParallax.setSize(144, 32);

		fondoParallax.setPosition(160, 384);

		if (dato.isFondoParallax()) {

			fondoParallax.setColor(1, 0, 0, 1);

		} else {

			fondoParallax.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		}

		zoomCamara = new Label("Zoom: ", recurso.get("ui/uiskin.json", Skin.class), "default-font", Color.GREEN);

		zoomCamara.setSize(96, 32);

		zoomCamara.setPosition(16, 384);

		zoomCamara.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		zoomCamaraMas = new TextButton("+", recurso.get("ui/uiskin.json", Skin.class));

		zoomCamaraMas.setSize(32, 32);

		zoomCamaraMas.setPosition(96, 384);

		zoomCamaraMas.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		zoomCamaraMenos = new TextButton("-", recurso.get("ui/uiskin.json", Skin.class));

		zoomCamaraMenos.setSize(32, 32);

		zoomCamaraMenos.setPosition(64, 384);

		zoomCamaraMenos.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		tipoActor = new SelectBox<String>(recurso.get("ui/uiskin.json", Skin.class));

		tipoActor.setSize(144, 32);

		tipoActor.setPosition(160, 352);

		tipoActor.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		tipoActor.setItems("Fuego", "NubeLarga", "NubeUno", "NubeDos", "NubeTres", "NubeCuatro", "NubeCinco",
				"NubeSeis", "NubeSiete", "CarroGris", "CarroAmarillo", "CamionetaCarga", "CamionetaGris",
				"CamionetaVerde", "BarcoVerde", "AntiAereo", "Sierra", "MaquinaParedIzquierda", "MaquinaParedDerecha",
				"Robot", "PlatilloVolador", "PlatilloA", "PlatilloDeLuz", "NaveFUno", "NaveFDos", "NaveFTres",
				"NaveFCuatro", "NaveFCinco", "NaveFSeis", "NaveFSiete", "NaveFOcho", "NaveFNueve", "NaveFDiez",
				"NaveFOnce", "NaveFDoce", "NaveFTrece", "NaveFCatorce", "NaveFQuince", "NaveFDiesciceis", "CajaVida",
				"CajaMisil", "CajaBomba", "CajaSatelite", "CajaVelocidad", "CajaHelicopteroNormal",
				"CajaHelicopteroRedondo", "CajaHelicopteroNegro", "CajaHelicopteroMedico", "CajaHelicopteroVerde",
				"CajaHelicopteroSatelital");

		tipo = "com.diamon.personaje." + tipoActor.getSelected();

	}

	public void agregarUI() {

		agregar = false;

		moverEnY = false;

		actualizar = false;

		tipo = "com.diamon.personaje." + tipoActor.getSelected();

		moverEsenario.setColor(1, 0, 0, 1);

		moverEsenarioEnY.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		actualizarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		if (dato.isFondoScroll()) {

			fondoScroll.setColor(1, 0, 0, 1);

		} else {

			fondoScroll.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		}

		if (dato.isFondoParallax()) {

			fondoParallax.setColor(1, 0, 0, 1);

		} else {

			fondoParallax.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		}

		nivel.addActor(borrarActores);

		nivel.addActor(moverEsenario);

		nivel.addActor(moverEsenarioEnY);

		nivel.addActor(agreagarActores);

		nivel.addActor(zoomCamara);

		nivel.addActor(zoomCamaraMenos);

		nivel.addActor(zoomCamaraMas);

		nivel.addActor(mundo);

		nivel.addActor(fondoScroll);

		nivel.addActor(fondoParallax);

		nivel.addActor(tipoActor);

		nivel.addActor(numeroNivel);

		nivel.addActor(actualizarActores);

		nivel.addActor(derecha);

		nivel.addActor(izquierda);

	}

	public void eliminarUI() {

		agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

		borrarActores.remove();
		moverEsenario.remove();
		moverEsenarioEnY.remove();
		agreagarActores.remove();
		zoomCamara.remove();
		zoomCamaraMas.remove();
		zoomCamaraMenos.remove();
		tipoActor.remove();
		numeroNivel.remove();
		mundo.remove();
		fondoScroll.remove();
		fondoParallax.remove();
		derecha.remove();
		izquierda.remove();

		actualizarActores.remove();

		agregar = false;

		actualizar = false;

	}

	public boolean isTerminar() {
		return terminar;
	}

	public void setTerminar(boolean terminar) {
		this.terminar = terminar;

	}

	public boolean ratonDeslizando(InputEvent ev) {

		return true;

	}

	public boolean ratonMoviendo(InputEvent ev, float x, float y) {

		return true;

	}

	public boolean ratonClick(InputEvent ev) {

		return true;
	}

	public boolean ratonPresionado(InputEvent ev) {

		return true;

	}

	public boolean ratonLevantado(InputEvent ev) {

		return true;
	}

	public boolean teclaPresionada(InputEvent ev, int codigoTecla) {

		switch (codigoTecla) {

		case Keys.RIGHT:

			pD = true;

			break;

		case Keys.LEFT:

			pI = true;

			break;

		case Keys.VOLUME_UP:

			pD = true;

			break;

		case Keys.VOLUME_DOWN:

			pI = true;

			break;

		}

		return true;
	}

	public boolean teclaLevantada(InputEvent ev, int codigoTecla) {

		switch (codigoTecla) {

		case Keys.RIGHT:

			for (int i = 0; i < personajes.size; i++) {

				if ((personajes.get(i) instanceof Fondo)) {

					personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

			pD = false;

			break;

		case Keys.LEFT:

			for (int i = 0; i < personajes.size; i++) {

				if ((personajes.get(i) instanceof Fondo)) {

					personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

			pI = false;

			break;

		case Keys.VOLUME_UP:

			for (int i = 0; i < personajes.size; i++) {

				if ((personajes.get(i) instanceof Fondo)) {

					personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

			pD = false;

			break;

		case Keys.VOLUME_DOWN:

			for (int i = 0; i < personajes.size; i++) {

				if ((personajes.get(i) instanceof Fondo)) {

					personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

			pI = false;

			break;

		}

		return true;
	}

	public boolean teclaTipo(InputEvent ev, char caracter) {

		return true;

	}

	public void toqueDeslizando(InputEvent ev, float x, float y, int puntero) {

		if (!terminar) {

			float y1 = Juego.ALTO_PANTALLA - y;

			if (agregar) {

				personajes.get(personajes.size - 1).setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
						y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			}

			if (!agregar) {

				if (!moverEnY) {

					camara.unproject(corriendo.set(x, 0, 0));

					if (!(detras.x == -1 && detras.y == -1 && detras.z == -1)) {

						for (int i = 0; i < personajes.size; i++) {

							if ((personajes.get(i) instanceof Fondo)) {

								personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

							}

						}

						camara.unproject(delta.set(detras.x, 0, 0));

						delta.sub(corriendo);

						camara.position.add(delta.x, 0, 0);
					}
					detras.set(x, 0, 0);

				} else {

					for (int i = 0; i < personajes.size; i++) {

						if ((personajes.get(i) instanceof Fondo)) {

							personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

						}

					}

					camara.unproject(corriendo.set(x, y1, 0));

					if (!(detras.x == -1 && detras.y == -1 && detras.z == -1)) {

						camara.unproject(delta.set(detras.x, detras.y, 0));

						delta.sub(corriendo);

						camara.position.add(delta.x, delta.y, 0);
					}
					detras.set(x, y1, 0);

				}

			}

		}

	}

	public boolean toqueLevantado(InputEvent ev, float x, float y, int puntero, int boton) {

		if (!terminar) {

			for (int i = 0; i < personajes.size; i++) {

				if ((personajes.get(i) instanceof Fondo)) {

					personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

			detras.set(-1, -1, -1);
		}

		if (agregar) {

			if (toque) {

				agregarActor(x, y);

			}

			toque = true;

		} else

		{

			toque = false;

		}
		return true;

	}

	public boolean toquePresionado(InputEvent ev, float x, float y, int puntero, int boton) {

		if (!terminar) {

			if (actualizar) {

				float ancho = 0, alto = 0;

				String numeroNvel = "Nivel " + dato.getNumeroNivel();

				for (int i = 0; i < personajes.size; i++) {

					if (cursor.getBoundingRectangle().overlaps(personajes.get(i).getBoundingRectangle())) {

						if (!(personajes.get(i) instanceof Fondo) && !(personajes.get(i) instanceof Jugador)
								&& !(personajes.get(i) instanceof Terreno)) {

							if (tipo.contentEquals(personajes.get(i).getClass().getName().toString())) {

								ancho = personajes.get(i).getWidth();

								alto = personajes.get(i).getHeight();

								personajes.removeIndex(i);

							}

						}

					}

				}

				for (int i = 0; i < dato.getTamanoArray(numeroNvel).size; i++) {

					Rectangle r = new Rectangle(dato.getTamanoArray(numeroNvel).get(i).x,
							dato.getTamanoArray(numeroNvel).get(i).y, ancho, alto);

					if (cursor.getBoundingRectangle().overlaps(r)) {

						dato.eliminarActor(numeroNvel, tipo, i);

					}

				}

			}

			if (agregar) {

				agregarActorTemporal(x, y);

				personajes.get(personajes.size - 1).setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
						y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			}

		}

		return true;

	}

	private void eventos() {

		actualizarActores.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				actualizar = true;

				actualizarActores.setColor(1, 0, 0, 1);

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

			}
		});

		borrarActores.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				String numeroNvel = "Nivel " + dato.getNumeroNivel();

				dato.eliminarActores(numeroNvel);

				configuracion.escribirDatos(dato);

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				for (int i = 0; i < personajes.size; i++) {

					if (!(personajes.get(i) instanceof Fondo) && !(personajes.get(i) instanceof Jugador)
							&& !(personajes.get(i) instanceof Terreno)) {

						personajes.removeIndex(i);

					}
				}

				actualizar = false;

				actualizarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

			}
		});

		numeroNivel.addListener(new ChangeListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void changed(ChangeEvent event, Actor actor) {

				dato.setNumeroNivel(((SelectBox<String>) actor).getSelectedIndex() + 1);

				for (int i = 0; i < personajes.size; i++) {

					if ((personajes.get(i) instanceof Jugador)) {

						Jugador j = (Jugador) personajes.get(i);

						j.setTerminarNivel(true);

					}
				}

			}
		});

		tipoActor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				super.clicked(event, x, y);
			}
		});

		derecha.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				super.clicked(event, x, y);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				pD = true;

				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				for (int i = 0; i < personajes.size; i++) {

					if ((personajes.get(i) instanceof Fondo)) {

						personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

					}

				}
				pD = false;

				super.touchUp(event, x, y, pointer, button);
			}

		});

		izquierda.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				super.clicked(event, x, y);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				pI = true;

				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				for (int i = 0; i < personajes.size; i++) {

					if ((personajes.get(i) instanceof Fondo)) {

						personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

					}

				}
				pI = false;

				super.touchUp(event, x, y, pointer, button);
			}
		});

		numeroNivel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				moverEsenario.setColor(1, 0, 0, 1);

				moverEsenarioEnY.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				moverEnY = false;

				super.clicked(event, x, y);
			}
		});

		tipoActor.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				@SuppressWarnings("unchecked")
				String nombre = ((SelectBox<String>) actor).getItems()
						.get(((SelectBox<String>) actor).getSelectedIndex());

				tipo = "com.diamon.personaje." + nombre;

			}
		});

		moverEsenario.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				moverEnY = false;

				moverEsenario.setColor(1, 0, 0, 1);

				moverEsenarioEnY.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				super.clicked(event, x, y);
			}
		});

		moverEsenarioEnY.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (agregar) {

					personajes.removeIndex(personajes.size - 1);

				}

				agregar = false;

				moverEnY = true;

				moverEsenario.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				moverEsenarioEnY.setColor(1, 0, 0, 1);

				agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				super.clicked(event, x, y);
			}
		});

		agreagarActores.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				agregar = true;

				moverEsenario.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				moverEsenarioEnY.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				agreagarActores.setColor(1, 0, 0, 1);

				actualizar = false;

				actualizarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				super.clicked(event, x, y);
			}
		});

		zoomCamaraMas.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!terminar) {

					camara.zoom -= 0.1f;

					if (agregar) {

						personajes.removeIndex(personajes.size - 1);

					}

					agregar = false;

					agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				}

				super.clicked(event, x, y);
			}
		});

		zoomCamaraMenos.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!terminar) {

					camara.zoom += 0.1f;

					if (agregar) {

						personajes.removeIndex(personajes.size - 1);

					}

					agregar = false;

					agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

				}

				super.clicked(event, x, y);
			}
		});

		fondoScroll.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!terminar) {

					if (agregar) {

						personajes.removeIndex(personajes.size - 1);

					}

					agregar = false;

					agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

					dato.setFondoScroll(true);

					dato.setFondoParallax(false);

					fondoParallax.setColor(1.0F, 1.0F, 1.0F, 0.7F);

					fondoScroll.setColor(1, 0, 0, 1);

				}

				super.clicked(event, x, y);
			}
		});

		fondoParallax.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!terminar) {

					fondoScroll.setColor(1.0F, 1.0F, 1.0F, 0.7F);

					fondoParallax.setColor(1, 0, 0, 1);

					agreagarActores.setColor(1.0F, 1.0F, 1.0F, 0.7F);

					dato.setFondoScroll(false);

					dato.setFondoParallax(true);

					if (agregar) {

						personajes.removeIndex(personajes.size - 1);

					}

					agregar = false;

				}

				super.clicked(event, x, y);
			}
		});

	}

	private void agregarActorTemporal(float x, float y) {

		if (tipo.equals(Dato.PLATILLO_VOLADOR)) {

			PlatilloVolador actor = new PlatilloVolador(
					recurso.get("textura/platilloVolador.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(64, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_LARGA)) {

			NubeLarga actor = new NubeLarga(new TextureRegion(recurso.get("textura/nube.png", Texture.class)),
					pantalla);

			actor.setSize(194, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_UNO)) {

			NubeUno actor = new NubeUno(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube1"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_DOS)) {

			NubeDos actor = new NubeDos(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube2"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_TRES)) {

			NubeTres actor = new NubeTres(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube3"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_CUATRO)) {

			NubeCuatro actor = new NubeCuatro(
					recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube4"), pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_CINCO)) {

			NubeCinco actor = new NubeCinco(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube5"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_SEIS)) {

			NubeSeis actor = new NubeSeis(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube6"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NUBE_SIETE)) {

			NubeSiete actor = new NubeSiete(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube7"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.ANTI_AEREO)) {

			AntiAereo actor = new AntiAereo(

					recurso.get("textura/antiAereo.atlas", TextureAtlas.class).getRegions().get(2), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_UNO)) {

			NaveFUno actor = new NaveFUno(
					recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DOS)) {

			NaveFDos actor = new NaveFDos(
					recurso.get("textura/aliengris.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_TRES)) {

			NaveFTres actor = new NaveFTres(
					recurso.get("textura/alienpurpura.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_CUATRO)) {

			NaveFCuatro actor = new NaveFCuatro(
					recurso.get("textura/alienrojo.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_CINCO)) {

			NaveFCinco actor = new NaveFCinco(
					recurso.get("textura/alienverde.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_SEIS)) {

			NaveFSeis actor = new NaveFSeis(
					recurso.get("textura/alienverde2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_SIETE)) {

			NaveFSiete actor = new NaveFSiete(
					recurso.get("textura/aliennaranja.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_OCHO)) {

			NaveFOcho actor = new NaveFOcho(
					recurso.get("textura/aliennaranja2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_NUEVE)) {

			NaveFNueve actor = new NaveFNueve(
					recurso.get("textura/tripleazul.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DIEZ)) {

			NaveFDiez actor = new NaveFDiez(
					recurso.get("textura/triplegris.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_ONCE)) {

			NaveFOnce actor = new NaveFOnce(
					recurso.get("textura/triplepurpura.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DOCE)) {

			NaveFDoce actor = new NaveFDoce(
					recurso.get("textura/tripleverde.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_TRECE)) {

			NaveFTrece actor = new NaveFTrece(
					recurso.get("textura/triplerojo.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_CATORCE)) {

			NaveFCatorce actor = new NaveFCatorce(
					recurso.get("textura/tripleverde2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_QUINCE)) {

			NaveFQuince actor = new NaveFQuince(
					recurso.get("textura/triplenaranja.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DIESCICEIS)) {

			NaveFDiesciceis actor = new NaveFDiesciceis(
					recurso.get("textura/triplenaranja2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.SIERRA)) {

			Sierra actor = new Sierra(recurso.get("textura/sierra.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(42, 42);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.PLATILLO_A)) {

			PlatilloA actor = new PlatilloA(recurso.get("textura/ovni.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(64, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.PLATILLO_DE_LUZ)) {

			PlatilloDeLuz actor = new PlatilloDeLuz(
					recurso.get("textura/platilloDeLuz.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(64, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.ROBOT)) {

			Robot actor = new Robot(recurso.get("textura/robot.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(64, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.MAQUINA_PARED_IZQUIERDA)) {

			MaquinaParedIzquierda actor = new MaquinaParedIzquierda(
					recurso.get("textura/maquinaPared.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(32, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.MAQUINA_PARED_DERECHA)) {

			MaquinaParedDerecha actor = new MaquinaParedDerecha(
					recurso.get("textura/maquinaParedD.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(32, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_VIDA)) {

			CajaVida actor = new CajaVida(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja1"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_MISIL)) {

			CajaMisil actor = new CajaMisil(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja4"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_BOMBA)) {

			CajaBomba actor = new CajaBomba(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja3"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_SATELITE)) {

			CajaSatelite actor = new CajaSatelite(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja2"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_VELOCIDAD)) {

			CajaVelocidad actor = new CajaVelocidad(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja5"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_NORMAL)) {

			CajaHelicopteroNormal actor = new CajaHelicopteroNormal(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja6"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_REDONDO)) {

			CajaHelicopteroRedondo actor = new CajaHelicopteroRedondo(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja9"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_NEGRO)) {

			CajaHelicopteroNegro actor = new CajaHelicopteroNegro(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja11"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_MEDICO)) {

			CajaHelicopteroMedico actor = new CajaHelicopteroMedico(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja10"), pantalla);
			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_VERDE)) {

			CajaHelicopteroVerde actor = new CajaHelicopteroVerde(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja8"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_SATELITAL)) {

			CajaHelicopteroSatelital actor = new CajaHelicopteroSatelital(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja7"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.FUEGO)) {

			Fuego actor = new Fuego(recurso.get("textura/fuego.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(32, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CARRO_GRIS)) {

			CarroGris actor = new CarroGris(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("Carro1"), pantalla);

			actor.setSize(64, 23);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CARRO_AMARILLO)) {

			CarroAmarillo actor = new CarroAmarillo(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("Carro2"), pantalla);

			actor.setSize(64, 23);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAMIONETA_CARGA)) {

			CamionetaCarga actor = new CamionetaCarga(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta1"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAMIONETA_GRIS)) {

			CamionetaGris actor = new CamionetaGris(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta2"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.CAMIONETA_VERDE)) {

			CamionetaVerde actor = new CamionetaVerde(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta3"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

		if (tipo.equals(Dato.BARCO_VERDE)) {

			BarcoVerde actor = new BarcoVerde(new TextureRegion(recurso.get("textura/barcoVerde.png", Texture.class)),
					pantalla);

			actor.setSize(256, 98);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			personajes.add(actor);

		}

	}

	private void agregarActor(float x, float y) {

		Array<Personaje> actores = new Array<Personaje>();

		if (tipo.equals(Dato.PLATILLO_VOLADOR)) {

			PlatilloVolador actor = new PlatilloVolador(
					recurso.get("textura/platilloVolador.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(64, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_LARGA)) {

			NubeLarga actor = new NubeLarga(new TextureRegion(recurso.get("textura/nube.png", Texture.class)),
					pantalla);

			actor.setSize(194, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_UNO)) {

			NubeUno actor = new NubeUno(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube1"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_DOS)) {

			NubeDos actor = new NubeDos(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube2"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_TRES)) {

			NubeTres actor = new NubeTres(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube3"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_CUATRO)) {

			NubeCuatro actor = new NubeCuatro(
					recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube4"), pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_CINCO)) {

			NubeCinco actor = new NubeCinco(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube5"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_SEIS)) {

			NubeSeis actor = new NubeSeis(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube6"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NUBE_SIETE)) {

			NubeSiete actor = new NubeSiete(recurso.get("textura/nubes.atlas", TextureAtlas.class).findRegion("nube7"),
					pantalla);

			actor.setSize(64, 32);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.ANTI_AEREO)) {

			AntiAereo actor = new AntiAereo(

					recurso.get("textura/antiAereo.atlas", TextureAtlas.class).getRegions().get(2), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_UNO)) {

			NaveFUno actor = new NaveFUno(
					recurso.get("textura/alienAzul.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DOS)) {

			NaveFDos actor = new NaveFDos(
					recurso.get("textura/aliengris.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_TRES)) {

			NaveFTres actor = new NaveFTres(
					recurso.get("textura/alienpurpura.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_CUATRO)) {

			NaveFCuatro actor = new NaveFCuatro(
					recurso.get("textura/alienrojo.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_CINCO)) {

			NaveFCinco actor = new NaveFCinco(
					recurso.get("textura/alienverde.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_SEIS)) {

			NaveFSeis actor = new NaveFSeis(
					recurso.get("textura/alienverde2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_SIETE)) {

			NaveFSiete actor = new NaveFSiete(
					recurso.get("textura/aliennaranja.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_OCHO)) {

			NaveFOcho actor = new NaveFOcho(
					recurso.get("textura/aliennaranja2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_NUEVE)) {

			NaveFNueve actor = new NaveFNueve(
					recurso.get("textura/tripleazul.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DIEZ)) {

			NaveFDiez actor = new NaveFDiez(
					recurso.get("textura/triplegris.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_ONCE)) {

			NaveFOnce actor = new NaveFOnce(
					recurso.get("textura/triplepurpura.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DOCE)) {

			NaveFDoce actor = new NaveFDoce(
					recurso.get("textura/tripleverde.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_TRECE)) {

			NaveFTrece actor = new NaveFTrece(
					recurso.get("textura/triplerojo.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_CATORCE)) {

			NaveFCatorce actor = new NaveFCatorce(
					recurso.get("textura/tripleverde2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_QUINCE)) {

			NaveFQuince actor = new NaveFQuince(
					recurso.get("textura/triplenaranja.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.NAVE_F_DIESCICEIS)) {

			NaveFDiesciceis actor = new NaveFDiesciceis(
					recurso.get("textura/triplenaranja2.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(48, 48);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.SIERRA)) {

			Sierra actor = new Sierra(recurso.get("textura/sierra.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(42, 42);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.PLATILLO_A)) {

			PlatilloA actor = new PlatilloA(recurso.get("textura/ovni.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(64, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.PLATILLO_DE_LUZ)) {

			PlatilloDeLuz actor = new PlatilloDeLuz(
					recurso.get("textura/platilloDeLuz.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(64, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.ROBOT)) {

			Robot actor = new Robot(recurso.get("textura/robot.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(64, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.MAQUINA_PARED_IZQUIERDA)) {

			MaquinaParedIzquierda actor = new MaquinaParedIzquierda(
					recurso.get("textura/maquinaPared.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(32, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.MAQUINA_PARED_DERECHA)) {

			MaquinaParedDerecha actor = new MaquinaParedDerecha(
					recurso.get("textura/maquinaParedD.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

			actor.setSize(32, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_VIDA)) {

			CajaVida actor = new CajaVida(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja1"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_MISIL)) {

			CajaMisil actor = new CajaMisil(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja4"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_BOMBA)) {

			CajaBomba actor = new CajaBomba(recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja3"),
					pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_SATELITE)) {

			CajaSatelite actor = new CajaSatelite(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja2"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_VELOCIDAD)) {

			CajaVelocidad actor = new CajaVelocidad(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja5"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_NORMAL)) {

			CajaHelicopteroNormal actor = new CajaHelicopteroNormal(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja6"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_REDONDO)) {

			CajaHelicopteroRedondo actor = new CajaHelicopteroRedondo(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja9"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_NEGRO)) {

			CajaHelicopteroNegro actor = new CajaHelicopteroNegro(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja11"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_MEDICO)) {

			CajaHelicopteroMedico actor = new CajaHelicopteroMedico(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja10"), pantalla);
			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_VERDE)) {

			CajaHelicopteroVerde actor = new CajaHelicopteroVerde(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja8"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAJA_HELICOPTERO_SATELITAL)) {

			CajaHelicopteroSatelital actor = new CajaHelicopteroSatelital(
					recurso.get("textura/cajas.atlas", TextureAtlas.class).findRegion("caja7"), pantalla);

			actor.setSize(24, 24);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.FUEGO)) {

			Fuego actor = new Fuego(recurso.get("textura/fuego.atlas", TextureAtlas.class).getRegions().get(0),
					pantalla);

			actor.setSize(32, 64);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CARRO_GRIS)) {

			CarroGris actor = new CarroGris(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("Carro1"), pantalla);

			actor.setSize(64, 23);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CARRO_AMARILLO)) {

			CarroAmarillo actor = new CarroAmarillo(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("Carro2"), pantalla);

			actor.setSize(64, 23);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAMIONETA_CARGA)) {

			CamionetaCarga actor = new CamionetaCarga(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta1"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAMIONETA_GRIS)) {

			CamionetaGris actor = new CamionetaGris(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta2"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.CAMIONETA_VERDE)) {

			CamionetaVerde actor = new CamionetaVerde(
					recurso.get("textura/carros.atlas", TextureAtlas.class).findRegion("camioneta3"), pantalla);

			actor.setSize(56, 35);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		if (tipo.equals(Dato.BARCO_VERDE)) {

			BarcoVerde actor = new BarcoVerde(new TextureRegion(recurso.get("textura/barcoVerde.png", Texture.class)),
					pantalla);

			actor.setSize(256, 98);

			actor.setPosition(x + (camara.position.x - (Juego.ANCHO_PANTALLA / 2)),
					y + (camara.position.y - (Juego.ALTO_PANTALLA / 2)));

			actores.add(actor);

		}

		agregarActor(actores);

	}

	public void actualizar() {

		if (pD) {

			for (int i = 0; i < personajes.size; i++) {

				if ((personajes.get(i) instanceof Fondo)) {

					personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

			camara.position.x += velocidadCamara;

		}

		if (pI) {

			for (int i = 0; i < personajes.size; i++) {

				if ((personajes.get(i) instanceof Fondo)) {

					personajes.get(i).setPosition((camara.position.x - Juego.ANCHO_PANTALLA / 2), 0);

				}

			}

			camara.position.x -= velocidadCamara;

		}

	}

	public void agregarActor(Array<Personaje> actores) {

		String nivel = "Nivel " + dato.getNumeroNivel();

		dato.gurdarActores(actores, tipo, nivel);

	}

}
