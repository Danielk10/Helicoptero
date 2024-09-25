package com.diamon.personaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Jugador extends Personaje {

	private float deltaXTactil;

	private float deltaYTactil;

	private float x1;

	private float y1;

	private float velocidadX;

	private float velocidadY;

	private float velocidad;

	private static final int VELOCIDAD_JUGADOR = 5;

	private static final int MAXIMA_CANTIDAD_SATELITE = 1;

	private static final int MAXIMA_CANTIDAD_VIDA = 10;

	private static final int MAXIMA_CANTIDAD_MISIL = 80;

	private static final int MAXIMA_CANTIDAD_BOMBA = 50;

	private static final int MAXIMA_CANTIDAD_VELOCIDAD = 4;

	public final static int HELICOPTERO_NORMAL = 1;

	public final static int HELICOPTERO_REDONDO = 2;

	public final static int HELICOPTERO_NEGRO = 3;

	public final static int HELICOPTERO_MEDICO = 4;

	public final static int HELICOPTERO_VERDE = 5;

	public final static int HELICOPTERO_SATELITAL = 6;

	private boolean arriba, abajo, izquierda, derecha, disparar, dispararBomba, dispararMisil;

	private int vida;

	private int misil;

	private int bomba;

	private int numeroDeSatelites;

	private float tiempoCuadroDisparoBala;

	private float tiempoCuadroDisparoBalaExplosiva;

	private float tiempoCuadroDisparoMisil;

	private float tiempoCuadroDisparoBomba;

	private float tiempoCuadroInmune;

	private float tiempoCuadroParpadeo;

	private int tipoItem;

	private boolean finNivel;

	private boolean terminarNivel;

	private boolean inmune;

	private boolean choque;

	private boolean intro;

	private boolean gefe;

	private Explosion explosionBala;

	private Explosion explosionMisil;

	private int puntos;

	private int tipo;

	private boolean cambioTipo;

	private float tiempoItemVelocidad;

	private float tiempoNuevaVida;

	private boolean itemVelocidad;

	private float velocidadCamaraItem;

	private boolean agregarSatelite;

	private Satelite actorSatelite;

	private int dedos;

	private boolean deltaToque;

	public Jugador(Texture texture, Pantalla pantalla) {
		super(texture, pantalla);

		misil = dato.getMisiles();

		bomba = dato.getBombas();

		vida = dato.getVidas();

		deltaToque = false;

		dedos = -1;

		tipo = 1;

		tipoItem = 0;

		x1 = 0;

		y1 = 0;

		velocidadX = 0;

		velocidadY = 0;

		arriba = abajo = izquierda = derecha = disparar = dispararBomba = dispararMisil = false;

		velocidad = VELOCIDAD_JUGADOR;

		choque = false;

		inmune = false;

		finNivel = false;

		terminarNivel = false;

		intro = true;

		cambioTipo = false;

		itemVelocidad = false;

		velocidadCamaraItem = 1;

		numeroDeSatelites = dato.getNumeroSatelite();

	}

	public Jugador(TextureRegion region, Pantalla pantalla) {
		super(region, pantalla);

		misil = dato.getMisiles();

		bomba = dato.getBombas();

		vida = dato.getVidas();

		deltaToque = false;

		dedos = -1;

		tipo = 1;

		tipoItem = 0;

		x1 = 0;

		y1 = 0;

		velocidadX = 0;

		velocidadY = 0;

		arriba = abajo = izquierda = derecha = disparar = dispararBomba = dispararMisil = false;

		velocidad = VELOCIDAD_JUGADOR;

		choque = false;

		inmune = false;

		finNivel = false;

		terminarNivel = false;

		intro = true;

		cambioTipo = false;

		itemVelocidad = false;

		velocidadCamaraItem = 1;

		numeroDeSatelites = dato.getNumeroSatelite();

	}

	public Jugador(Array<TextureAtlas.AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		misil = dato.getMisiles();

		bomba = dato.getBombas();

		vida = dato.getVidas();

		deltaToque = false;

		dedos = -1;

		tipo = 1;

		tipoItem = 0;

		x1 = 0;

		y1 = 0;

		velocidadX = 0;

		velocidadY = 0;

		arriba = abajo = izquierda = derecha = disparar = dispararBomba = dispararMisil = false;

		velocidad = VELOCIDAD_JUGADOR;

		choque = false;

		inmune = false;

		finNivel = false;

		terminarNivel = false;

		intro = true;

		cambioTipo = false;

		itemVelocidad = false;

		velocidadCamaraItem = 1;

		numeroDeSatelites = dato.getNumeroSatelite();

	}

	public void resetearJugador() {

		x1 = 0;

		y1 = 0;

		velocidadX = 0;

		velocidadY = 0;

		deltaXTactil = 0;

		deltaYTactil = 0;

		disparar = false;

		dispararMisil = false;

		dispararBomba = false;

		deltaToque = false;

		inmune = false;

		tiempoNuevaVida = 0;

		velocidadCamaraItem = 1;

		dedos = -1;

		if (tipo == Jugador.HELICOPTERO_NORMAL) {

			dureza = 3;

		}

		if (tipo == Jugador.HELICOPTERO_REDONDO) {

			dureza = 6;

		}

		if (tipo == Jugador.HELICOPTERO_NEGRO) {

			dureza = 9;

		}

		if (tipo == Jugador.HELICOPTERO_MEDICO) {

			dureza = 12;

		}

		if (tipo == Jugador.HELICOPTERO_VERDE) {

			dureza = 15;

		}

		if (tipo == Jugador.HELICOPTERO_SATELITAL) {

			dureza = 18;

		}

		if (actorSatelite != null) {

			if (numeroDeSatelites == 1) {

				agregarSatelite = true;

				actorSatelite.setRemover(false);

			}

		}

	}

	public int getNumeroDeSatelites() {
		return numeroDeSatelites;
	}

	public void setNumeroDeSatelites(int numeroDeSatelites) {
		this.numeroDeSatelites = numeroDeSatelites;
	}

	public boolean isCambioTipo() {
		return cambioTipo;
	}

	public void setCambioTipo(boolean cambioTipo) {
		this.cambioTipo = cambioTipo;
	}

	public boolean isInmune() {
		return inmune;
	}

	public void setInmune(boolean inmune) {
		this.inmune = inmune;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isIntro() {
		return intro;
	}

	public void setIntro(boolean intro) {
		this.intro = intro;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public boolean isTerminarNivel() {

		return terminarNivel;
	}

	public void setTerminarNivel(boolean terminarNivel) {

		this.terminarNivel = terminarNivel;
	}

	public int getVida() {

		return vida;
	}

	public void setVida(int vida) {

		this.vida = vida;
	}

	public int getMisil() {
		return misil;
	}

	public void setMisil(int misil) {
		this.misil = misil;
	}

	public int getBomba() {
		return bomba;
	}

	public void setBomba(int bomba) {
		this.bomba = bomba;
	}

	public boolean teclaPresionada(InputEvent ev, int codigoTecla) {

		switch (ev.getKeyCode()) {

		case Keys.LEFT:

			izquierda = true;

			break;

		case Keys.RIGHT:

			derecha = true;

			break;

		case Keys.UP:

			arriba = true;

			break;

		case Keys.DOWN:

			abajo = true;

			break;

		case Keys.Z:

			disparar = true;

			break;

		case Keys.X:

			dispararMisil = true;

			break;

		case Keys.SPACE:

			dispararBomba = true;

			break;

		default:

			break;

		}

		actualizarVelocidad();

		return true;
	}

	public boolean teclaLevantada(InputEvent ev, int codigoTecla) {

		switch (ev.getKeyCode()) {

		case Keys.LEFT:

			izquierda = false;

			break;

		case Keys.RIGHT:

			derecha = false;

			break;

		case Keys.UP:

			arriba = false;

			break;

		case Keys.DOWN:

			abajo = false;

			break;

		case Keys.Z:

			disparar = false;

			break;

		case Keys.X:

			dispararMisil = false;

			break;

		case Keys.SPACE:

			dispararBomba = false;

			break;

		default:

			break;

		}

		actualizarVelocidad();

		return true;
	}

	public boolean teclaTipo(InputEvent ev, char caracter) {

		return true;

	}

	public boolean ratonMoviendo(InputEvent ev, float x, float y) {

		if (!finNivel) {

			if (!dato.isDiparoAutomatico()) {

				x1 = x - deltaXTactil;

				y1 = y - deltaYTactil;

				if (x1 <= camara.position.x - Juego.ANCHO_PANTALLA / 2) {

					x1 = camara.position.x - Juego.ANCHO_PANTALLA / 2;

				}

				if (x1 >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())) {

					x1 = camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth());
				}

				if (y1 >= camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32)) {

					y1 = camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32);

				}

				if (y1 <= camara.position.y - Juego.ALTO_PANTALLA / 2) {

					y1 = camara.position.y - Juego.ALTO_PANTALLA / 2;

				}

				this.x = x1;

				this.y = y1;

			}

		}

		return true;

	}

	@SuppressWarnings("static-access")
	public void toqueDeslizando(InputEvent ev, float x, float y, int puntero) {

		if (!finNivel) {

			if (Gdx.app.getType() == Gdx.app.getType().Android) {

				if (dedos == 0) {

					if (deltaToque) {

						x1 = this.getX();

						y1 = this.getY();

						deltaXTactil = x - x1;

						deltaYTactil = y - y1;

						deltaToque = false;
					}

					x1 = x - deltaXTactil;

					y1 = y - deltaYTactil;

					if (x1 <= camara.position.x - Juego.ANCHO_PANTALLA / 2) {

						x1 = camara.position.x - Juego.ANCHO_PANTALLA / 2;

					}

					if (x1 >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())) {

						x1 = camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth());
					}

					if (y1 >= camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32)) {

						y1 = camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32);

					}

					if (y1 <= camara.position.y - Juego.ALTO_PANTALLA / 2) {

						y1 = camara.position.y - Juego.ALTO_PANTALLA / 2;

					}

					this.x = x1;

					this.y = y1;

				}

			}

			if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

				x1 = x - deltaXTactil;

				y1 = y - deltaYTactil;

				if (x1 <= camara.position.x - Juego.ANCHO_PANTALLA / 2) {

					x1 = camara.position.x - Juego.ANCHO_PANTALLA / 2;

				}

				if (x1 >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())) {

					x1 = camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth());
				}

				if (y1 >= camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32)) {

					y1 = camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32);

				}

				if (y1 <= camara.position.y - Juego.ALTO_PANTALLA / 2) {

					y1 = camara.position.y - Juego.ALTO_PANTALLA / 2;

				}

				this.x = x1;

				this.y = y1;

			}

		}

	}

	@SuppressWarnings("static-access")
	public boolean toqueLevantado(InputEvent ev, float x, float y, int puntero, int boton) {

		if (!finNivel) {

			if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

				if (boton == 0) {

					disparar = false;

				}

				if (boton == 1) {

					dispararMisil = false;

				}

			}

			if (Gdx.app.getType() == Gdx.app.getType().Android) {

				deltaToque = true;

				dedos--;

				if (dedos == -1) {

					disparar = false;

				}

			}

		}

		return true;

	}

	@SuppressWarnings("static-access")
	public boolean toquePresionado(InputEvent ev, float x, float y, int puntero, int boton) {

		if (!finNivel) {

			if (Gdx.app.getType() == Gdx.app.getType().Android) {

				dedos++;

				if (dedos == 0) {

					x1 = this.getX();

					y1 = this.getY();

					deltaXTactil = x - x1;

					deltaYTactil = y - y1;

					if (x1 <= camara.position.x - Juego.ANCHO_PANTALLA / 2) {

						x1 = camara.position.x - Juego.ANCHO_PANTALLA / 2;

					}

					if (y1 >= camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32)) {

						y1 = camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32);

					}

					if (y1 <= camara.position.y - Juego.ALTO_PANTALLA / 2) {

						y1 = camara.position.y - Juego.ALTO_PANTALLA / 2;

					}
					if (x1 >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())) {

						x1 = camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth());
					}

					if (boton == 0) {

						disparar = true;

					}

				}

			}

			if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

				if (dato.isDiparoAutomatico()) {

					x1 = this.getX();

					y1 = this.getY();

					deltaXTactil = x - x1;

					deltaYTactil = y - y1;

				}

				if (x1 <= camara.position.x - Juego.ANCHO_PANTALLA / 2) {

					x1 = camara.position.x - Juego.ANCHO_PANTALLA / 2;

				}

				if (y1 >= camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32)) {

					y1 = camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32);

				}

				if (y1 <= camara.position.y - Juego.ALTO_PANTALLA / 2) {

					y1 = camara.position.y - Juego.ALTO_PANTALLA / 2;

				}
				if (x1 >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())) {

					x1 = camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth());
				}

				if (boton == 0) {

					disparar = true;

				}

				if (boton == 1) {

					dispararMisil = true;

				}

			}

		}

		return true;

	}

	public void actualizar(float delta) {

		super.actualizar(delta);

		if (!finNivel) {

			if (intro) {

				if (!gefe) {

					if (itemVelocidad) {

						deltaXTactil -= velocidadCamaraItem / Juego.DELTA_A_PIXEL * delta;

					} else {

						deltaXTactil -= Juego.VELOCIDAD_CAMARA / Juego.DELTA_A_PIXEL * delta;

					}

				}

			}

			if (!gefe) {

				if (itemVelocidad) {

					x += velocidadCamaraItem / Juego.DELTA_A_PIXEL * delta;

				} else {

					x += Juego.VELOCIDAD_CAMARA / Juego.DELTA_A_PIXEL * delta;

				}

			}

			tiempoCuadroDisparoMisil += delta;

			tiempoCuadroDisparoBomba += delta;

			if (itemVelocidad) {

				tiempoItemVelocidad += delta;

				if (tiempoItemVelocidad / 10 >= 1) {

					if (velocidadCamaraItem == 2) {

						velocidadCamaraItem = 0;

						itemVelocidad = false;

						tiempoItemVelocidad = 0;

					}

					if (velocidadCamaraItem == 3) {

						velocidadCamaraItem = 0;

						itemVelocidad = false;

						tiempoItemVelocidad = 0;

					}

					if (velocidadCamaraItem == 4) {

						velocidadCamaraItem = 0;

						itemVelocidad = false;

						tiempoItemVelocidad = 0;

					}

				}

			}

			tiempoNuevaVida += delta;

			if (tiempoNuevaVida / 30 >= 1) {

				if (tipo == Jugador.HELICOPTERO_MEDICO) {

					if (vida < Jugador.MAXIMA_CANTIDAD_VIDA)

					{
						vida++;

						if (dato.isSonido())

						{

							recurso.get("audio/poder.ogg", Sound.class).play(dato.getVolumenSonido());

						}

					}

					tiempoNuevaVida = 0;

				}

			}

			if (actorSatelite != null) {

				if (actorSatelite.isRemover()) {

					numeroDeSatelites = 0;

					actorSatelite.setRemover(false);

				}

			}

			if (agregarSatelite) {

				agregarSateliteNuevo();

				agregarSatelite = false;

			}

			if (disparar) {

				if (tipo == Jugador.HELICOPTERO_NORMAL) {

					tiempoCuadroDisparoBala += delta;

					if (tiempoCuadroDisparoBala / 0.16f >= 1) {

						disparar();

						tiempoCuadroDisparoBala = 0;

					}

				}

			}

			if (disparar) {

				if (tipo == Jugador.HELICOPTERO_REDONDO) {

					tiempoCuadroDisparoBala += delta;

					if (tiempoCuadroDisparoBala / 0.16f >= 1) {

						disparar();

						tiempoCuadroDisparoBala = 0;

					}

				}

			}

			if (disparar) {

				if (tipo == Jugador.HELICOPTERO_NEGRO) {

					tiempoCuadroDisparoBala += delta;

					if (tiempoCuadroDisparoBala / 0.16f >= 1) {

						disparar();

						tiempoCuadroDisparoBala = 0;

					}

				}

			}

			if (disparar) {

				if (tipo == Jugador.HELICOPTERO_MEDICO) {

					tiempoCuadroDisparoBala += delta;

					if (tiempoCuadroDisparoBala / 0.16f >= 1) {

						disparar();

						tiempoCuadroDisparoBala = 0;

					}

				}

			}

			if (disparar) {

				if (tipo == Jugador.HELICOPTERO_VERDE) {

					tiempoCuadroDisparoBala += delta;

					if (tiempoCuadroDisparoBala / 0.16f >= 1) {

						disparar();

						tiempoCuadroDisparoBala = 0;

					}

				}

			}

			if (disparar) {

				if (tipo == Jugador.HELICOPTERO_SATELITAL) {

					tiempoCuadroDisparoBalaExplosiva += delta;

					if (tiempoCuadroDisparoBalaExplosiva / 0.20f >= 1) {

						disparar();

						tiempoCuadroDisparoBalaExplosiva = 0;

					}

				}

			}

			if (dato.isDiparoAutomatico()) {

				if (dispararMisil) {

					if (tiempoCuadroDisparoMisil / 0.33f >= 1) {

						tiempoCuadroDisparoMisil = 0;

					}

				}

			}

			if (tiempoCuadroDisparoBomba / 0.16f >= 1) {

				tiempoCuadroDisparoBomba = 0;

			}

			if (dispararBomba) {

				Bombas();

				dispararBomba = false;
			}

			if (dispararMisil) {

				dispararMisil();

				dispararMisil = false;
			}

			x += velocidadX / Juego.DELTA_A_PIXEL * delta;

			y += velocidadY / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())) {

				x = camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth());

			}

			if (x <= camara.position.x - Juego.ANCHO_PANTALLA / 2) {

				if (intro) {

					x = camara.position.x - Juego.ANCHO_PANTALLA / 2;

				}

			}

			if (y >= camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32)) {

				y = camara.position.y + (Juego.ALTO_PANTALLA / 2 - getHeight() - 32);

			}

			if (y <= camara.position.y - Juego.ALTO_PANTALLA / 2) {

				y = camara.position.y - Juego.ALTO_PANTALLA / 2;

			}

			for (int i = 0; i < personajes.size; i++) {

				if (personajes.get(i) instanceof Terreno) {

					if (getBoundingRectangle().overlaps(personajes.get(i).getBoundingRectangle())) {

						if (velocidadX > 0) {

							if (getX() + this.getWidth() - velocidadX - Juego.VELOCIDAD_CAMARA <= personajes.get(i)
									.getX()) {

								x = getX() - velocidadX;

								velocidadX = 0;

								break;

							}

						} else

						{

							if (getX() + this.getWidth() - Juego.VELOCIDAD_CAMARA - velocidadX <= personajes.get(i)
									.getX()) {

								x = getX();

								velocidadX = 0;

							}

						}

						if (getX() - velocidadX >= personajes.get(i).getX() + personajes.get(i).getWidth()) {

							x = getX();

							velocidadX = 0;

							break;

						}

						if (getY() + this.getHeight() - velocidadY <= personajes.get(i).getY()) {

							y = getY();

							velocidadY = 0;

							break;

						}

						if (getY() - velocidadY >= personajes.get(i).getY() + personajes.get(i).getHeight()) {

							y = getY();

							velocidadY = 0;

							break;

						}

					}

				}

			}

			if (explosionBala != null) {

				explosionBala.setPosition(getX() + getWidth() - 15, getY());

			}

			if (explosionMisil != null) {

				explosionMisil.setPosition(getX() + getWidth() - 24, getY() - 10);

			}

			if (choque) {

				tiempoCuadroInmune += delta;

				tiempoCuadroParpadeo += delta;

				if (tiempoCuadroParpadeo / 0.08f >= 1) {

					setAlpha(0);

					tiempoCuadroParpadeo = 0;

				} else {

					setAlpha(1);
				}

				if (tiempoCuadroInmune / 3.33f >= 1) {

					setAlpha(1);

					inmune = false;

					choque = false;

					tiempoCuadroInmune = 0;

				}

			}

		} else {

			x += Juego.VELOCIDAD_CAMARA / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())) {

				vivo = false;

				terminarNivel = true;
			}
		}

		if (vida <= 0) {

			vivo = false;

			agregarParacaisdista();

			remover = true;
		}

	}

	public boolean isItemVelocidad() {
		return itemVelocidad;
	}

	public void setItemVelocidad(boolean itemVelocidad) {
		this.itemVelocidad = itemVelocidad;
	}

	public float getVelocidadCamaraItem() {
		return velocidadCamaraItem;
	}

	public void setVelocidadCamaraItem(float velocidadCamaraItem) {
		this.velocidadCamaraItem = velocidadCamaraItem;
	}

	public boolean isGefe() {
		return gefe;
	}

	public void setGefe(boolean gefe) {
		this.gefe = gefe;
	}

	public boolean isFinNivel() {

		return finNivel;
	}

	public void setFinNivel(boolean finNivel) {

		this.finNivel = finNivel;
	}

	public boolean isDispararBomba() {
		return dispararBomba;
	}

	public void setDispararBomba(boolean dispararBomba) {
		this.dispararBomba = dispararBomba;
	}

	public boolean isDispararMisil() {
		return dispararMisil;
	}

	public void setDispararMisil(boolean dispararMisil) {
		this.dispararMisil = dispararMisil;
	}

	public Explosion getExplosionMisil() {
		return explosionMisil;
	}

	public void setExplosionMisil(Explosion explosionMisil) {
		this.explosionMisil = explosionMisil;
	}

	public void colision(Personaje actor) {

		if (actor instanceof BalaExplosivaEnemiga || actor instanceof BalaEnemigo) {

			if (tipo == Jugador.HELICOPTERO_NORMAL) {

				if (!inmune) {

					dureza--;

					if (dato.isSonido())

					{

						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

				}

				if (dureza <= 0) {

					if (!inmune) {

						choque = true;

						dureza = 3;

						--vida;

						if (vida <= 0) {

							explosionHelicoptero();

						}

					}
					inmune = true;
				}

			}

			if (tipo == Jugador.HELICOPTERO_REDONDO) {

				if (!inmune) {

					dureza--;

					if (dato.isSonido())

					{

						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

				}

				if (dureza <= 0) {

					if (!inmune) {

						choque = true;

						dureza = 6;

						--vida;

						if (vida <= 0) {

							explosionHelicoptero();

						}

					}
					inmune = true;
				}

			}

			if (tipo == Jugador.HELICOPTERO_NEGRO) {

				if (!inmune) {

					dureza--;

					if (dato.isSonido())

					{

						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

				}

				if (dureza <= 0) {

					if (!inmune) {

						choque = true;

						dureza = 9;

						--vida;

						if (vida <= 0) {

							explosionHelicoptero();

						}

					}
					inmune = true;
				}

			}

			if (tipo == Jugador.HELICOPTERO_MEDICO) {

				if (!inmune) {

					dureza--;

					if (dato.isSonido())

					{

						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

				}

				if (dureza <= 0) {

					if (!inmune) {

						choque = true;

						dureza = 12;

						--vida;

						if (vida <= 0) {

							explosionHelicoptero();

						}

					}
					inmune = true;
				}

			}

			if (tipo == Jugador.HELICOPTERO_VERDE) {

				if (!inmune) {

					dureza--;

					if (dato.isSonido())

					{

						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

				}

				if (dureza <= 0) {

					if (!inmune) {

						choque = true;

						dureza = 15;

						--vida;

						if (vida <= 0) {

							explosionHelicoptero();

						}

					}
					inmune = true;
				}

			}

			if (tipo == Jugador.HELICOPTERO_SATELITAL) {

				if (!inmune) {

					dureza--;

					if (dato.isSonido())

					{

						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

				}

				if (dureza <= 0) {

					if (!inmune) {

						choque = true;

						dureza = 18;

						--vida;

						if (vida <= 0) {

							explosionHelicoptero();

						}

					}
					inmune = true;
				}

			}

		}

		if (actor instanceof JefeUno || actor instanceof JefeDos || actor instanceof JefeTres
				|| actor instanceof JefeCuatro) {

			if (!inmune) {

				int durezaJefe = actor.getDureza();

				durezaJefe -= 5;

				actor.setDureza(durezaJefe);

				puntos = 5;

				if (dato.isSonido())

				{

					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());
				}

				explosion();

				setAlpha(0);

				choque = true;

				--vida;

				if (vida <= 0) {

					explosionHelicoptero();

				}

			}

			inmune = true;

		}

		if (actor instanceof Sierra || actor instanceof CarroGris || actor instanceof CarroAmarillo
				|| actor instanceof CamionetaVerde || actor instanceof CamionetaGris || actor instanceof CamionetaCarga
				|| actor instanceof BarcoVerde || actor instanceof SateliteEnemigo || actor instanceof BolaPlasma) {

			puntos = 5;

			if (!inmune) {

				if (dato.isSonido())

				{

					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());
				}

				setAlpha(0);

				choque = true;

				--vida;

				if (vida <= 0) {

					explosionHelicoptero();

				}

			}

			inmune = true;

		}

		if (actor instanceof Terreno) {

			if (!inmune) {

				if (dato.isSonido())

				{

					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());
				}

				explosion();

				setAlpha(0);

				choque = true;

				--vida;

				if (vida <= 0) {

					explosionHelicoptero();

				}

			}

			inmune = true;
		}

		if (actor instanceof PlatilloA || actor instanceof PlatilloDeLuz || actor instanceof PlatilloVolador
				|| actor instanceof NaveFUno || actor instanceof NaveFDos || actor instanceof NaveFTres
				|| actor instanceof NaveFCuatro || actor instanceof NaveFCinco || actor instanceof NaveFSeis
				|| actor instanceof NaveFSiete || actor instanceof NaveFOcho || actor instanceof NaveFNueve
				|| actor instanceof NaveFDiez || actor instanceof NaveFOnce || actor instanceof NaveFDoce
				|| actor instanceof NaveFTrece || actor instanceof NaveFCatorce || actor instanceof NaveFQuince
				|| actor instanceof NaveFDiesciceis || actor instanceof AntiAereo || actor instanceof Robot
				|| actor instanceof MaquinaParedDerecha || actor instanceof MaquinaParedIzquierda
				|| actor instanceof CajaVida || actor instanceof CajaBomba || actor instanceof CajaMisil
				|| actor instanceof CajaVelocidad || actor instanceof CajaSatelite
				|| actor instanceof CajaHelicopteroNormal | actor instanceof CajaHelicopteroVerde
				|| actor instanceof CajaHelicopteroRedondo || actor instanceof CajaHelicopteroMedico
				|| actor instanceof CajaHelicopteroNegro || actor instanceof CajaHelicopteroSatelital) {

			puntos = 5;

			if (!inmune) {

				choque = true;

				--vida;
			}

			inmune = true;
		}

		if (actor instanceof ItemDeJuego) {

			ItemDeJuego item = (ItemDeJuego) actor;

			tipoItem = item.getItem();

			agregarVida(tipoItem);

			agregarMisil(tipoItem);

			agregarBomba(tipoItem);

			agregarSatelite(tipoItem);

			agregarTipo(tipoItem);

			agregarVelocidad(tipoItem);

			if (dato.isSonido())

			{

				recurso.get("audio/poder.ogg", Sound.class).play(dato.getVolumenSonido());

			}

		}

	}

	private void agregarVida(int tipoItem) {

		if (tipoItem == ItemDeJuego.VIDA) {

			if (vida < Jugador.MAXIMA_CANTIDAD_VIDA)

			{
				vida++;

			}

		}

	}

	private void agregarTipo(int tipoItem) {

		if (tipoItem == ItemDeJuego.HELICOPTERO_NORMAL) {

			tipo = Jugador.HELICOPTERO_NORMAL;

			dureza = 3;

			this.animacion = new Animation<TextureRegion>(0.01f,
					recurso.get("textura/helicoptero.atlas", TextureAtlas.class).getRegions());

			this.animacion.setPlayMode(PlayMode.LOOP);

			this.setSize(48, 22);

			cambioTipo = true;

		}

		if (tipoItem == ItemDeJuego.HELICOPTERO_REDONDO) {

			tipo = Jugador.HELICOPTERO_REDONDO;

			dureza = 6;

			this.animacion = new Animation<TextureRegion>(0.01f,
					recurso.get("textura/helicopteroRedondo.atlas", TextureAtlas.class).getRegions());

			this.animacion.setPlayMode(PlayMode.LOOP);

			this.setSize(64, 32);

			cambioTipo = true;

		}

		if (tipoItem == ItemDeJuego.HELICOPTERO_NEGRO) {

			tipo = Jugador.HELICOPTERO_NEGRO;

			dureza = 9;

			this.animacion = new Animation<TextureRegion>(0.01f,
					recurso.get("textura/helicopteroNegro.atlas", TextureAtlas.class).getRegions());

			this.animacion.setPlayMode(PlayMode.LOOP);

			this.setSize(64, 32);

			cambioTipo = true;

		}

		if (tipoItem == ItemDeJuego.HELICOPTERO_MEDICO) {

			tipo = Jugador.HELICOPTERO_MEDICO;

			dureza = 12;

			this.animacion = new Animation<TextureRegion>(0.01f,
					recurso.get("textura/helicopteroMedico.atlas", TextureAtlas.class).getRegions());

			this.animacion.setPlayMode(PlayMode.LOOP);

			this.setSize(64, 32);

			cambioTipo = true;

		}

		if (tipoItem == ItemDeJuego.HELICOPTERO_VERDE) {

			tipo = Jugador.HELICOPTERO_VERDE;

			dureza = 15;

			this.animacion = new Animation<TextureRegion>(0.01f,
					recurso.get("textura/helicopteroVerde.atlas", TextureAtlas.class).getRegions());

			this.animacion.setPlayMode(PlayMode.LOOP);

			this.setSize(64, 32);

			cambioTipo = true;

		}

		if (tipoItem == ItemDeJuego.HELICOPTERO_SATELITAL) {

			tipo = Jugador.HELICOPTERO_SATELITAL;

			dureza = 18;

			this.animacion = new Animation<TextureRegion>(0.01f,
					recurso.get("textura/elicoptero1.atlas", TextureAtlas.class).getRegions());

			this.animacion.setPlayMode(PlayMode.LOOP);

			this.setSize(64, 32);

			cambioTipo = true;

		}

	}

	private void agregarVelocidad(int tipoItem) {

		if (tipoItem == ItemDeJuego.VELOCIDAD) {

			itemVelocidad = true;

			tiempoItemVelocidad = 0;

			if (velocidadCamaraItem < Jugador.MAXIMA_CANTIDAD_VELOCIDAD)

			{

				velocidadCamaraItem += 1;

			}

		}

	}

	private void agregarSateliteNuevo() {

		actorSatelite = new Satelite(recurso.get("textura/satelite.atlas", TextureAtlas.class).getRegions(), 0.01f,
				Animation.PlayMode.LOOP, pantalla);

		actorSatelite.setJugador(this);

		actorSatelite.setSize(24, 24);

		actorSatelite.setVelocidadY(400);

		actorSatelite.setDistanciaMovimiento(100);

		personajes.add(actorSatelite);

	}

	private void agregarSatelite(int tipoItem) {

		if (tipoItem == ItemDeJuego.SATELITE) {

			if (numeroDeSatelites < Jugador.MAXIMA_CANTIDAD_SATELITE)

			{

				agregarSatelite = true;

				numeroDeSatelites += 1;

			}

		}

	}

	private void agregarBomba(int tipoItem) {

		if (tipoItem == ItemDeJuego.BOMBA) {

			if (bomba < Jugador.MAXIMA_CANTIDAD_BOMBA)

			{

				bomba += 5;

				if (bomba >= Jugador.MAXIMA_CANTIDAD_BOMBA) {

					bomba = Jugador.MAXIMA_CANTIDAD_BOMBA;

				}

			}

		}

	}

	private void agregarMisil(int tipoItem) {

		if (tipoItem == ItemDeJuego.MISIL) {

			if (misil < Jugador.MAXIMA_CANTIDAD_MISIL)

			{

				misil += 5;

				if (misil >= Jugador.MAXIMA_CANTIDAD_MISIL) {

					misil = Jugador.MAXIMA_CANTIDAD_MISIL;

				}

			}

		}

	}

	private void actualizarVelocidad() {

		velocidadX = 0;

		velocidadY = 0;

		if (abajo) {

			velocidadY = -velocidad;
		}

		if (arriba) {

			velocidadY = velocidad;
		}

		if (izquierda) {

			velocidadX = -velocidad;

		}

		if (derecha) {

			velocidadX = velocidad;

		}

	}

	public void disparar() {

		if (tipo == Jugador.HELICOPTERO_NORMAL)

		{

			Bala bala = new Bala(recurso.get("textura/bala.png", Texture.class), pantalla);

			bala.setSize(6, 6);

			bala.setPosition(getX() + getWidth(), getY());

			bala.setVelocidad(20);

			bala.setLado(Bala.DERECHO);

			if (dato.isSonido())

			{

				recurso.get("audio/bala.ogg", Sound.class).play(dato.getVolumenSonido());
			}

			explosionBala(bala.getX() - 10, bala.getY());

			personajes.add(bala);
		}

		if (tipo == Jugador.HELICOPTERO_REDONDO)

		{

			Bala bala = new Bala(recurso.get("textura/bala.png", Texture.class), pantalla);

			bala.setSize(6, 6);

			bala.setPosition(getX() + getWidth(), getY());

			bala.setVelocidad(20);

			bala.setLado(Bala.DERECHO);

			if (dato.isSonido())

			{

				recurso.get("audio/bala.ogg", Sound.class).play(dato.getVolumenSonido());
			}

			explosionBala(bala.getX() - 10, bala.getY());

			personajes.add(bala);
		}

		if (tipo == Jugador.HELICOPTERO_NEGRO)

		{

			Bala bala = new Bala(recurso.get("textura/bala.png", Texture.class), pantalla);

			bala.setSize(6, 6);

			bala.setPosition(getX() + getWidth(), getY());

			bala.setVelocidad(20);

			bala.setLado(Bala.DERECHO);

			if (dato.isSonido())

			{

				recurso.get("audio/bala.ogg", Sound.class).play(dato.getVolumenSonido());
			}

			explosionBala(bala.getX() - 10, bala.getY());

			personajes.add(bala);
		}

		if (tipo == Jugador.HELICOPTERO_MEDICO)

		{

			Bala bala = new Bala(recurso.get("textura/bala.png", Texture.class), pantalla);

			bala.setSize(6, 6);

			bala.setPosition(getX() + getWidth(), getY());

			bala.setVelocidad(20);

			bala.setLado(Bala.DERECHO);

			if (dato.isSonido())

			{

				recurso.get("audio/bala.ogg", Sound.class).play(dato.getVolumenSonido());
			}

			explosionBala(bala.getX() - 10, bala.getY());

			personajes.add(bala);
		}

		if (tipo == Jugador.HELICOPTERO_VERDE)

		{

			Bala bala = new Bala(recurso.get("textura/bala.png", Texture.class), pantalla);

			bala.setSize(6, 6);

			bala.setPosition(getX() + getWidth(), getY());

			bala.setVelocidad(20);

			bala.setLado(Bala.DERECHO);

			if (dato.isSonido())

			{

				recurso.get("audio/bala.ogg", Sound.class).play(dato.getVolumenSonido());
			}

			explosionBala(bala.getX() - 10, bala.getY());

			personajes.add(bala);
		}

		if (tipo == Jugador.HELICOPTERO_SATELITAL)

		{

			Bala bala = new Bala(recurso.get("textura/bala.png", Texture.class), pantalla);

			bala.setSize(6, 6);

			bala.setPosition(getX() + getWidth(), getY());

			bala.setVelocidad(10);

			bala.setLado(Bala.DERECHO);

			bala.setTipo(Bala.EXPLOSIVA);

			if (bala.getTipo() == Bala.EXPLOSIVA)

			{

				bala.setxJugador(getX());

			}

			if (dato.isSonido())

			{

				recurso.get("audio/bala.ogg", Sound.class).play(dato.getVolumenSonido());
			}

			explosionBala(bala.getX() - 10, bala.getY());

			personajes.add(bala);

		}

	}

	public void dispararMisil() {

		if (this.misil == 0)

		{

			this.misil = 0;
		}

		if (this.misil > 0)

		{

			Misil misil = new Misil(

					recurso.get("textura/misil.atlas", TextureAtlas.class).getRegions(), 0.07f, Animation.PlayMode.LOOP,
					pantalla);

			misil.setSize(32, 16);

			misil.setPosition(getX() + getWidth(), getY() - misil.getHeight() / 2);

			misil.setLado(Misil.DERECHO);

			misil.setVelocidad(5);

			if (dato.isSonido())

			{

				recurso.get("audio/misil.ogg", Sound.class).play(dato.getVolumenSonido());
			}

			this.misil--;

			explosionMisil(misil.getX() - 10, misil.getY() - 5);

			personajes.add(misil);

		}

	}

	public void Bombas() {

		if (this.bomba == 0)

		{

			this.bomba = 0;
		}

		if (this.bomba > 0)

		{

			Bomba bomba = null;

			if (tipo == Jugador.HELICOPTERO_NORMAL)

			{

				bomba = new Bomba(

						recurso.get("textura/bomba.atlas", TextureAtlas.class).getRegions(), 0.07f,
						Animation.PlayMode.LOOP, pantalla);

			}

			if (tipo == Jugador.HELICOPTERO_REDONDO)

			{

				bomba = new Bomba(

						recurso.get("textura/bomba.atlas", TextureAtlas.class).getRegions(), 0.07f,
						Animation.PlayMode.LOOP, pantalla);

			}

			if (tipo == Jugador.HELICOPTERO_NEGRO)

			{

				bomba = new Bomba(

						recurso.get("textura/bombaAmarilla.atlas", TextureAtlas.class).getRegions(), 0.07f,
						Animation.PlayMode.LOOP, pantalla);

			}

			if (tipo == Jugador.HELICOPTERO_MEDICO)

			{

				bomba = new Bomba(

						recurso.get("textura/bombaAmarilla.atlas", TextureAtlas.class).getRegions(), 0.07f,
						Animation.PlayMode.LOOP, pantalla);

			}

			if (tipo == Jugador.HELICOPTERO_VERDE)

			{

				bomba = new Bomba(

						recurso.get("textura/bombaRoja.atlas", TextureAtlas.class).getRegions(), 0.07f,
						Animation.PlayMode.LOOP, pantalla);

			}

			if (tipo == Jugador.HELICOPTERO_SATELITAL)

			{

				bomba = new Bomba(

						recurso.get("textura/bombaVerde.atlas", TextureAtlas.class).getRegions(), 0.07f,
						Animation.PlayMode.LOOP, pantalla);

			}

			if (bomba != null) {

				bomba.setSize(16, 32);

				bomba.setPosition(getX() + 10, getY() - this.getHeight());

				if (dato.isSonido())

				{

					Sound sonido = recurso.get("audio/bomba.ogg", Sound.class);

					sonido.play(dato.getVolumenSonido());

					bomba.setSonido(sonido);
				}

				this.bomba--;

				personajes.add(bomba);

			}

		}

	}

	public void agregarParacaisdista() {

		Paracaidista paracaidista = null;

		if (tipo == Jugador.HELICOPTERO_NORMAL)

		{

			paracaidista = new Paracaidista(recurso.get("textura/paracaisdista.png", Texture.class), pantalla);

		}

		if (tipo == Jugador.HELICOPTERO_REDONDO)

		{

			paracaidista = new Paracaidista(recurso.get("textura/paracaisdista.png", Texture.class), pantalla);

		}

		if (tipo == Jugador.HELICOPTERO_NEGRO)

		{

			paracaidista = new Paracaidista(recurso.get("textura/paracaisdista2.png", Texture.class), pantalla);

		}

		if (tipo == Jugador.HELICOPTERO_MEDICO)

		{

			paracaidista = new Paracaidista(recurso.get("textura/paracaisdista2.png", Texture.class), pantalla);

		}

		if (tipo == Jugador.HELICOPTERO_VERDE)

		{

			paracaidista = new Paracaidista(recurso.get("textura/paracaisdista3.png", Texture.class), pantalla);

		}

		if (tipo == Jugador.HELICOPTERO_SATELITAL)

		{

			paracaidista = new Paracaidista(recurso.get("textura/paracaisdista1.png", Texture.class), pantalla);

		}

		if (paracaidista != null) {

			paracaidista.setSize(48, 66);

			paracaidista.setPosition(x, y);

			personajes.add(paracaidista);

		}

	}

	public void explosionHelicoptero() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(64, 64);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.58f);

		personajes.add(explosion);
	}

	public void explosionMisil(float pocicionX, float pocicionY) {

		explosionMisil = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(), 0.07f,
				Animation.PlayMode.NORMAL, pantalla);

		explosionMisil.setSize(24, 24);

		explosionMisil.setPosition(pocicionX, pocicionY);

		explosionMisil.setDuracionExplosion(0.58f);

		personajes.add(explosionMisil);
	}

	public void explosionBala(float pocicionX, float pocicionY) {

		explosionBala = new Explosion(recurso.get("textura/explosion.atlas", TextureAtlas.class).getRegions(), 0.07f,
				Animation.PlayMode.NORMAL, pantalla);

		explosionBala.setSize(8, 8);

		explosionBala.setPosition(pocicionX, pocicionY);

		explosionBala.setDuracionExplosion(0.1f);

		personajes.add(explosionBala);
	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(32, 32);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.3f);

		personajes.add(explosion);
	}

}
