package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class JefeCuatro extends Personaje {

	private float duracionDisparo;

	private float tiempoCuadro;

	private float tiempoCuadroBalaExplosiva;

	private float tiemo;

	private float velocidadY;

	private float velocidadX;

	private float distanciaMovimientoY;

	private float pocicionY;

	private Jugador jugador;

	private SateliteEnemigo satelite;

	public JefeCuatro(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		obtenerJugador();

		agregarSatelite();

	}

	public JefeCuatro(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		obtenerJugador();

		agregarSatelite();

	}

	public JefeCuatro(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		obtenerJugador();

		agregarSatelite();

	}

	private void agregarSatelite() {

		satelite = new SateliteEnemigo(recurso.get("textura/bolaPlasma.atlas", TextureAtlas.class).getRegions(), 0.05f,
				Animation.PlayMode.LOOP, pantalla);

		satelite.setPersonaje(this);

		satelite.setSize(64, 64);

		satelite.setVelocidadY(400);

		satelite.setDistanciaMovimiento(150);

		personajes.add(satelite);

	}

	private void obtenerJugador() {

		for (int i = 0; i < personajes.size; i++) {

			if (personajes.get(i) instanceof Jugador) {

				jugador = (Jugador) personajes.get(i);

			}

		}

	}

	@Override
	public void actualizar(float delta) {

		if (x <= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

			super.actualizar(delta);

			tiemo += delta;

			y = pocicionY + distanciaMovimientoY + (distanciaMovimientoY * MathUtils.sinDeg(tiemo * velocidadY));

			x += (int) velocidadX / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())
					|| x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 - 200)) {

				velocidadX = -velocidadX;

			}

			tiempoCuadro += delta;

			tiempoCuadroBalaExplosiva += delta;

			if (tiempoCuadroBalaExplosiva / 2 >= 1) {

				balaExplosiva();

				tiempoCuadroBalaExplosiva = 0;
			}

			if (tiempoCuadro / duracionDisparo >= 1) {

				if (jugador.getY() <= y + getHeight() && jugador.getY() + jugador.getHeight() >= y
						&& jugador.getX() <= x)

				{

					disparar(BalaEnemigo.IZQUIERDO);

				}

				if (jugador.getY() <= y + getHeight() && jugador.getY() + jugador.getHeight() >= y
						&& jugador.getX() >= x + getWidth())

				{

					disparar(BalaEnemigo.DERECHO);

				}

				tiempoCuadro = 0;
			}

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public void balaExplosiva() {

		BalaExplosivaEnemiga bala1 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala1.setSize(16, 16);

		bala1.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala1.setLado(BalaExplosiva.ABAJO);

		BalaExplosivaEnemiga bala2 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala2.setSize(16, 16);

		bala2.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala2.setLado(BalaExplosiva.ARRIBA);

		BalaExplosivaEnemiga bala3 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala3.setSize(16, 16);

		bala3.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala3.setLado(BalaExplosiva.DERECHO);

		BalaExplosivaEnemiga bala4 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala4.setSize(16, 16);

		bala4.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala4.setLado(BalaExplosiva.DERECHO_ABAJO);

		BalaExplosivaEnemiga bala5 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala5.setSize(16, 16);

		bala5.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala5.setLado(BalaExplosiva.DERECHO_ARRIBA);

		BalaExplosivaEnemiga bala6 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala6.setSize(16, 16);

		bala6.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala6.setLado(BalaExplosiva.IZQUIERDO);

		BalaExplosivaEnemiga bala7 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala7.setSize(16, 16);

		bala7.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala7.setLado(BalaExplosiva.IZQUIERDO_ABAJO);

		BalaExplosivaEnemiga bala8 = new BalaExplosivaEnemiga(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala8.setSize(16, 16);

		bala8.setPosition(getX() + getWidth() / 2 - 8, getY() + getHeight() / 2 - 8);

		bala8.setLado(BalaExplosiva.IZQUIERDO_ARRIBA);

		personajes.add(bala1);
		personajes.add(bala2);
		personajes.add(bala3);
		personajes.add(bala4);
		personajes.add(bala5);
		personajes.add(bala6);
		personajes.add(bala7);
		personajes.add(bala8);

	}

	public void disparar(int lado) {

		BolaPlasma plasma = new BolaPlasma(recurso.get("textura/bolaPlasma.atlas", TextureAtlas.class).getRegions(),
				0.05f, Animation.PlayMode.LOOP, pantalla);

		plasma.setSize(64, 64);

		if (BalaEnemigo.IZQUIERDO == lado) {

			plasma.setPosition(x - 64, y + this.getHeight() / 2 - 32);

		}

		if (BalaEnemigo.DERECHO == lado) {

			plasma.setPosition(x + this.getWidth(), y + this.getHeight() / 2 - 32);

		}

		plasma.setLado(lado);

		plasma.setVelocidad(8);

		personajes.add(plasma);

		if (dato.isSonido())

		{

			recurso.get("audio/disparoEnemigo.ogg", Sound.class).play(dato.getVolumenSonido());

		}

	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(128, 128);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.58f);

		personajes.add(explosion);

	}

	public float getDuracionDisparo() {
		return duracionDisparo;
	}

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
	}

	@Override
	public Rectangle getBoundingRectangle() {

		Rectangle r = super.getBoundingRectangle();

		return r;
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof Jugador) {

			if (dureza <= 0) {

				if (dato.isSonido())

				{

					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();

				remover = true;

			}

		}

		if (actor instanceof Bala || actor instanceof BalaExplosiva) {

			dureza--;

			if (dureza <= 0) {

				if (dato.isSonido())

				{
					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();

				remover = true;

			}
		}

		if (actor instanceof Misil || actor instanceof Satelite) {

			dureza -= 5;

			if (dureza <= 0) {

				if (dato.isSonido())

				{
					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();

				remover = true;

			}

		}

		if (actor instanceof Bomba) {

			dureza -= 10;

			if (dureza <= 0) {

				if (dato.isSonido())

				{
					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();

				remover = true;

			}

		}

		if (actor instanceof ExplosionTerreno) {

			if (actor.getWidth() >= 64) {

				dureza -= 5;

				if (dureza <= 0) {

					if (dato.isSonido())

					{
						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

					remover = true;

				}

			}

		}

	}

	@Override
	public void setPosition(float x, float y) {

		pocicionY = y;

		super.setPosition(x, y);
	}

	public float getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(float velocidadY) {
		this.velocidadY = velocidadY;
	}

	public float getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(float velocidadX) {
		this.velocidadX = velocidadX;
	}

	public float getDistanciaMovimientoY() {
		return distanciaMovimientoY;
	}

	public void setDistanciaMovimientoY(float distanciaMovimientoY) {
		this.distanciaMovimientoY = distanciaMovimientoY;
	}

}
