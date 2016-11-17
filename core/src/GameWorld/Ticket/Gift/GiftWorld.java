/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Ticket.Gift;

import Enums.GiftType;
import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.Picture;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.BuffsScreen;
import com.mygdx.game.screen.MainScreen;

/**
 *
 * @author Pablo
 */
public class GiftWorld extends AbstractWorld {

    private GiftType type;
    private Picture gift;
    private Button acceptButton;

    public GiftWorld(Interface ui, GameLibGDX g, GiftType type) {
        super(ui, g);
        this.type = type;
        initWorld();
        //  cycle();
    }

    private void initWorld() {
        gift = new Picture(GiftHandler.getPicture(type));
        gift.setSize(ui.getWidth() / 2, ui.getHeight() / 2);
        gift.setPosition(ui.getWidth() / 5, ui.getHeight() / 5);
        ui.getGuiStage().addActor(gift);
        acceptButton = new Button("Accept", AssetLoader.btn, AssetLoader.btnPress, "ACCEPT", FontLoader.font) {
            public void action() {
                type = GiftHandler.getGiftType();
                Gdx.app.log("Type: ", "" + type.toString());
                GiftHandler.takeGift(type);
                game.setScreen(new MainScreen(game));
            }
        };
        acceptButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        acceptButton.setPosition(0, 0);

        ui.getGuiStage().addActor(acceptButton);
    }

    /*  private void cycle() {
        for (int i = 0; i < 100; i++) {
            type = GiftHandler.getGiftType();
            Gdx.app.log("Type: ", "" + type.toString());
        }
    }*/
    @Override
    public void update(float delta) {
    }

}
