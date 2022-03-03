package com.diamon.helicoptero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.diamon.nucleo.Juego;
import com.diamon.pantalla.PantallaPrecentacion;

public class Helicoptero extends Juego {

	public Helicoptero(Publicidad publicidad) {
		super(publicidad);

	}

	@SuppressWarnings("static-access")
	@Override
	public void create() {
		super.create();

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

		if (dato.isSincronizacionVertical()) {

			Gdx.graphics.setVSync(true);

		}

		if (!dato.isSincronizacionVertical()) {

			Gdx.graphics.setVSync(false);

		}

		if (Gdx.app.getType() == Gdx.app.getType().Desktop) {

			Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(32, 32, Pixmap.Format.RGBA8888), 0, 0));

		}

		recurso.load("textura/balaExplosiva.png", Texture.class);

		recurso.load("textura/pausa.png", Texture.class);

		recurso.load("textura/invisible.png", Texture.class);

		recurso.load("textura/fondoNivel1.png", Texture.class);

		recurso.load("textura/fondoNivel2.png", Texture.class);

		recurso.load("textura/fondoNivel3.png", Texture.class);

		recurso.load("textura/fondoNivel4.png", Texture.class);

		recurso.load("textura/fondo1.png", Texture.class);

		recurso.load("textura/menuPausa.png", Texture.class);

		recurso.load("textura/badlogic.jpg", Texture.class);

		recurso.load("textura/titulo.png", Texture.class);

		recurso.load("textura/cursor.png", Texture.class);

		recurso.load("textura/bala.png", Texture.class);

		recurso.load("textura/paracaisdista.png", Texture.class);

		recurso.load("textura/paracaisdista1.png", Texture.class);

		recurso.load("textura/paracaisdista2.png", Texture.class);

		recurso.load("textura/paracaisdista3.png", Texture.class);

		recurso.load("textura/barcoVerde.png", Texture.class);

		recurso.load("textura/nube.png", Texture.class);

		recurso.load("textura/vidaJefe.png", Texture.class);

		recurso.load("textura/catidadVidaJefe.png", Texture.class);

		recurso.load("textura/item.atlas", TextureAtlas.class);

		recurso.load("textura/barrasHelicoptero.atlas", TextureAtlas.class);

		recurso.load("textura/satelite.atlas", TextureAtlas.class);

		recurso.load("textura/iconos.atlas", TextureAtlas.class);

		recurso.load("textura/controles.atlas", TextureAtlas.class);

		recurso.load("textura/cajas.atlas", TextureAtlas.class);

		recurso.load("textura/carros.atlas", TextureAtlas.class);

		recurso.load("textura/sierra.atlas", TextureAtlas.class);

		recurso.load("textura/antiAereo.atlas", TextureAtlas.class);

		recurso.load("textura/robot.atlas", TextureAtlas.class);

		recurso.load("textura/ovni.atlas", TextureAtlas.class);

		recurso.load("textura/platilloVolador.atlas", TextureAtlas.class);

		recurso.load("textura/platilloDeLuz.atlas", TextureAtlas.class);

		recurso.load("textura/alienAzul.atlas", TextureAtlas.class);

		recurso.load("textura/aliengris.atlas", TextureAtlas.class);

		recurso.load("textura/alienpurpura.atlas", TextureAtlas.class);

		recurso.load("textura/alienrojo.atlas", TextureAtlas.class);

		recurso.load("textura/alienverde.atlas", TextureAtlas.class);

		recurso.load("textura/alienverde2.atlas", TextureAtlas.class);

		recurso.load("textura/aliennaranja.atlas", TextureAtlas.class);

		recurso.load("textura/aliennaranja2.atlas", TextureAtlas.class);

		recurso.load("textura/tripleazul.atlas", TextureAtlas.class);

		recurso.load("textura/triplegris.atlas", TextureAtlas.class);

		recurso.load("textura/triplepurpura.atlas", TextureAtlas.class);

		recurso.load("textura/tripleverde.atlas", TextureAtlas.class);

		recurso.load("textura/tripleverde2.atlas", TextureAtlas.class);

		recurso.load("textura/triplerojo.atlas", TextureAtlas.class);

		recurso.load("textura/triplenaranja.atlas", TextureAtlas.class);

		recurso.load("textura/triplenaranja2.atlas", TextureAtlas.class);

		recurso.load("textura/helicoptero.atlas", TextureAtlas.class);

		recurso.load("textura/helicopteroRedondo.atlas", TextureAtlas.class);

		recurso.load("textura/helicopteroNegro.atlas", TextureAtlas.class);

		recurso.load("textura/helicopteroMedico.atlas", TextureAtlas.class);

		recurso.load("textura/helicopteroVerde.atlas", TextureAtlas.class);

		recurso.load("textura/elicoptero1.atlas", TextureAtlas.class);

		recurso.load("textura/bomba.atlas", TextureAtlas.class);

		recurso.load("textura/bombaAmarilla.atlas", TextureAtlas.class);

		recurso.load("textura/bombaRoja.atlas", TextureAtlas.class);

		recurso.load("textura/bombaVerde.atlas", TextureAtlas.class);

		recurso.load("textura/bolaPlasma.atlas", TextureAtlas.class);

		recurso.load("textura/maquinaPared.atlas", TextureAtlas.class);

		recurso.load("textura/maquinaParedD.atlas", TextureAtlas.class);

		recurso.load("textura/misil.atlas", TextureAtlas.class);

		recurso.load("textura/fuego.atlas", TextureAtlas.class);

		recurso.load("textura/humo.atlas", TextureAtlas.class);

		recurso.load("textura/explosion1.atlas", TextureAtlas.class);

		recurso.load("textura/explosion.atlas", TextureAtlas.class);

		recurso.load("textura/nubes.atlas", TextureAtlas.class);

		recurso.load("ui/uieli.json", Skin.class);

		recurso.load("ui/uiskin.json", Skin.class);

		recurso.load("ui/creditos.json", Skin.class);

		recurso.load("ui/skin/neon-ui.json", Skin.class);

		recurso.load("audio/explosion.ogg", Sound.class);

		recurso.load("audio/menu.ogg", Sound.class);

		recurso.load("audio/bala.ogg", Sound.class);

		recurso.load("audio/misil.ogg", Sound.class);

		recurso.load("audio/helicoptero.ogg", Sound.class);

		recurso.load("audio/bomba.ogg", Sound.class);

		recurso.load("audio/poder.ogg", Sound.class);

		recurso.load("audio/disparoEnemigo.ogg", Sound.class);

		recurso.load("audio/disparoRobot.ogg", Sound.class);

		recurso.load("audio/Battle Theme 1.ogg", Music.class);

		recurso.load("audio/Battle in the winter.ogg", Music.class);

		recurso.load("audio/The Outer Forest.ogg", Music.class);

		recurso.load("audio/Orbital Colossus.ogg", Music.class);

		recurso.load("audio/bellgrave.ogg", Music.class);

		recurso.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

		recurso.load("mapa/nivel1/nivel1.tmx", TiledMap.class);
		recurso.load("mapa/nivel2/nivel2.tmx", TiledMap.class);
		recurso.load("mapa/nivel3/nivel3.tmx", TiledMap.class);
		recurso.load("mapa/nivel4/nivel4.tmx", TiledMap.class);
		recurso.load("mapa/nivel5/nivel5.tmx", TiledMap.class);
		recurso.load("mapa/nivel6/nivel6.tmx", TiledMap.class);
		recurso.load("mapa/nivel7/nivel7.tmx", TiledMap.class);
		recurso.load("mapa/nivel8/nivel8.tmx", TiledMap.class);
		recurso.load("mapa/nivel9/nivel9.tmx", TiledMap.class);
		recurso.load("mapa/nivel10/nivel10.tmx", TiledMap.class);
		recurso.load("mapa/nivel11/nivel11.tmx", TiledMap.class);
		recurso.load("mapa/nivel12/nivel12.tmx", TiledMap.class);
		recurso.load("mapa/nivel13/nivel13.tmx", TiledMap.class);
		recurso.load("mapa/nivel14/nivel14.tmx", TiledMap.class);
		recurso.load("mapa/nivel15/nivel15.tmx", TiledMap.class);
		recurso.load("mapa/nivel16/nivel16.tmx", TiledMap.class);
		recurso.load("mapa/nivel17/nivel17.tmx", TiledMap.class);
		recurso.load("mapa/nivel18/nivel18.tmx", TiledMap.class);
		recurso.load("mapa/nivel19/nivel19.tmx", TiledMap.class);
		recurso.load("mapa/nivel20/nivel20.tmx", TiledMap.class);
		recurso.load("mapa/nivel21/nivel21.tmx", TiledMap.class);
		recurso.load("mapa/nivel22/nivel22.tmx", TiledMap.class);
		recurso.load("mapa/nivel23/nivel23.tmx", TiledMap.class);
		recurso.load("mapa/nivel24/nivel24.tmx", TiledMap.class);
		recurso.load("mapa/nivel25/nivel25.tmx", TiledMap.class);
		recurso.load("mapa/nivel26/nivel26.tmx", TiledMap.class);
		recurso.load("mapa/nivel27/nivel27.tmx", TiledMap.class);
		recurso.load("mapa/nivel28/nivel28.tmx", TiledMap.class);
		recurso.load("mapa/nivel29/nivel29.tmx", TiledMap.class);
		recurso.load("mapa/nivel30/nivel30.tmx", TiledMap.class);
		recurso.load("mapa/nivel31/nivel31.tmx", TiledMap.class);
		recurso.load("mapa/nivel32/nivel32.tmx", TiledMap.class);
		recurso.load("mapa/nivel33/nivel33.tmx", TiledMap.class);
		recurso.load("mapa/nivel34/nivel34.tmx", TiledMap.class);
		recurso.load("mapa/nivel35/nivel35.tmx", TiledMap.class);
		recurso.load("mapa/nivel36/nivel36.tmx", TiledMap.class);
		recurso.load("mapa/nivel37/nivel37.tmx", TiledMap.class);
		recurso.load("mapa/nivel38/nivel38.tmx", TiledMap.class);
		recurso.load("mapa/nivel39/nivel39.tmx", TiledMap.class);
		recurso.load("mapa/nivel40/nivel40.tmx", TiledMap.class);

		setScreen(new PantallaPrecentacion(this));

	}
}
