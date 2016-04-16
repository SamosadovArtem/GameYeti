package GameWorld.Skins;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.BuySkinsScreen;

import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.Scroll;
import GameWorld.AbstractWorld;
import GameWorld.Skins.Elements.SkinList;
import GameWorld.Skins.Elements.SkinsContainer;
import Helper.AssetLoader;

/**
 * Created by broff on 15.04.2016.
 */
public class SkinsWorld extends AbstractWorld {

    private SkinsContainer box;

    public SkinsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        ui.addBack(game);
        initAllSkins();
        initBuyButton();
    }

    @Override
    public void update(float delta) {

    }

    private void initAllSkins(){
        SkinList skinList = new SkinList();
        box = new SkinsContainer(skinList.getSkins(), ui.getStage().getCamera().position.x, ui.getStage());
    }

    public SkinsContainer getSkins(){
        return box;
    }

    private void initBuyButton(){
        Button buyButton = new Button("BuyButton", AssetLoader.btn, AssetLoader.btnPress, "", new BitmapFont())
        {
            public void action() {

            }
        };
        buyButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        buyButton.setPosition(ui.getStage().getWidth() /2 - buyButton.getWidth() / 2,
                ui.getStage().getHeight() / 4 - buyButton.getHeight()/2);

        ui.getGuiStage().addActor(buyButton);
    }

}