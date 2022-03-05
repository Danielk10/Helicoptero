package com.diamon.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaCarga extends Pantalla {

	private Image fondo;

	private ProgressBar barra;

	public PantallaCarga(Juego juego) {
		super(juego);

	}

	@Override
	public void mostrar() {

		barra = new ProgressBar(0.0F, 100.0F, 1.0F, false,
				new Skin(Gdx.files.internal("ui/skin/neon-ui.json"), new TextureAtlas("ui/skin/neon-ui.atlas")));

		barra.setSize(400, 32);

		barra.setPosition(128, 80);

		fondo = new Image(new TextureRegion(new Texture(Gdx.files.internal("textura/fondo1.png"))));

		fondo.setSize(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

		fondo.setPosition(0, 0);

		nivel.addActor(fondo);

		nivel.addActor(barra);

	}

	@Override
	public void eventos() {

	}

	@Override
	public void colisiones() {

	}

	@Override
	public void actualizar(float delta) {

		if (recurso.update()) {

			nivel.addAction(Actions.sequence(Actions.delay(0.2f), Actions.run(new Runnable() {

				public void run() {

					filtradoBilineal();

					sonido();

					juego.setScreen(new PantallaMenu(juego));

				}
			})));

		} else {

			int progreso = (int) (recurso.getProgress() * 100);

			barra.setValue(progreso);
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
			recurso.get("textura/vidaJefe.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
			recurso.get("textura/catidadVidaJefe.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);

			recurso.get("textura/iconos.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);

			recurso.get("textura/paracaisdista.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
			recurso.get("textura/paracaisdista1.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);

			recurso.get("textura/paracaisdista2.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);

			recurso.get("textura/paracaisdista3.png", Texture.class).setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
			
			
			for (Texture tetura : recurso.get("textura/dedos.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			}

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

			recurso.get("textura/vidaJefe.png", Texture.class).setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
			recurso.get("textura/catidadVidaJefe.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);

			recurso.get("textura/paracaisdista.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			recurso.get("textura/paracaisdista1.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);

			recurso.get("textura/paracaisdista2.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);

			recurso.get("textura/paracaisdista3.png", Texture.class).setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
			
			
			for (Texture tetura : recurso.get("textura/dedos.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

			for (Texture tetura : recurso.get("textura/item.atlas", TextureAtlas.class).getTextures()) {

				tetura.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

			}

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

		}

	}

	private void sonido() {

		recurso.get("audio/Battle Theme 1.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/The Outer Forest.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/Battle in the winter.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/Orbital Colossus.ogg", Music.class).setVolume(dato.getVolumenMusica());

		recurso.get("audio/bellgrave.ogg", Music.class).setVolume(dato.getVolumenMusica());

	}

}
