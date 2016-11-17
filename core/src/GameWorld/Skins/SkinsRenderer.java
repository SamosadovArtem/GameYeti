package GameWorld.Skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import GameObjects.Interface;
import GameWorld.Renderer;
import GameWorld.Settings.SettingsWorld;

/**
 * Created by broff on 15.04.2016.
 */
public class SkinsRenderer extends Renderer {

    private SkinsWorld world;
    private Interface ui;

    public SkinsRenderer(Interface ui, SkinsWorld world) {
        super();
        this.world = world;
        this.ui = ui;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ui.draw();
    }

}