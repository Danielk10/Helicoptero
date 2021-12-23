package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Bomba extends Personaje {

	private Sound sonido;

	private int puntos;

	public Bomba(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);
		// TODO Auto-generated constructor stub
	}

	public Bomba(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);
		// TODO Auto-generated constructor stub
	}

	public Bomba(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);
		// TODO Auto-generated constructor stub
	}

	public Sound getSonido() {
		return sonido;
	}

	public void setSonido(Sound sonido) {
		this.sonido = sonido;
	}

	@Override
	public void actualizar(float delta) {
		// TODO Auto-generated method stub
		super.actualizar(delta);

		y -= 2 / Juego.DELTA_A_PIXEL * delta;

		if (y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

			remover = true;
		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void explosionTerreno() {

		ExplosionTerreno explosion = new ExplosionTerreno(
				recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(), 0.07f,
				Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(64, 64);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.58f);

		personajes.add(explosion);
	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setDuracionExplosion(0.3f);

		explosion.setSize(64, 64);

		explosion.setPosition(x, y);

		personajes.add(explosion);
	}

	@Override
	public void colision(Personaje actor) {

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
				|| actor instanceof CajaHelicopteroNegro || actor instanceof CajaHelicopteroSatelital
				|| actor instanceof CarroGris || actor instanceof CarroAmarillo || actor instanceof CamionetaVerde
				|| actor instanceof CamionetaGris || actor instanceof CamionetaCarga || actor instanceof BarcoVerde
				|| actor instanceof Sierra) {

			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			puntos = 5;
			remover = true;
		}

		if (actor instanceof JefeUno || actor instanceof JefeDos || actor instanceof JefeTres
				|| actor instanceof JefeCuatro) {

			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosion();

			if (actor.getDureza() <= 0)

			{

				puntos = 1000;

			}

			remover = true;
		}

		if (actor instanceof Terreno) {

			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosionTerreno();

			if (dato.isSonido())

			{

				getSonido().stop();

			}

			remover = true;
		}

	}

}
