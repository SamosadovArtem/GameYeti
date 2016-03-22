/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.AssetLoader;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Pablo
 */
public abstract class AbstractWindow {

    protected Stage stage;

    private boolean isVisible;

    protected float xPos, yPos;
    protected float width, height;

    protected TextureRegion normalState = AssetLoader.btn, pressedState = AssetLoader.btnPress;

    public AbstractWindow(Stage stage) {
        isVisible = false;
        this.stage = stage;
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void createWindow(GameLibGDX game) {
        show();
        xPos = stage.getCamera().position.x;
        yPos = stage.getCamera().position.y;
        initText();
        initButtons(game);
    }

    protected abstract void initText();

    protected abstract void initButtons(GameLibGDX game);

}
