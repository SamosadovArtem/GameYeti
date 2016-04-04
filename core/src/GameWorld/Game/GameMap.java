/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameWorld.Game.Objects.Antelope;
import GameWorld.Game.Objects.ClearObject;
import GameWorld.Game.Objects.Coin;
import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Giraffe;
import GameWorld.Game.Objects.Pinguin;
import GameWorld.Game.Objects.Snake;
import GameWorld.Game.Objects.StopObj;
import GameWorld.Game.Objects.Tree;
import Helper.AssetLoader;
import Helper.Constants;
import Helper.WorldUtils;
import LocationGenerator.Barrier;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.List;

/**
 *
 * @author qw
 */
public class GameMap {
    
    private Stage mapStage;
    private GameActor player;
    
    public GameMap(){
        mapStage = new Stage(new FitViewport(Constants.APP_WIDTH * 4, Constants.APP_HEIGHT * 4));    
        player = new ClearObject(Constants.RUNNER_X, Constants.RUNNER_Y,
                Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT, AssetLoader.btn);
        mapStage.addActor(player);
    }
    
    public void focusCameraX(Pinguin p) {
        mapStage.getCamera().position.x = p.getX() + Constants.GROUND_WIDTH / 5;
        mapStage.getCamera().update();
        player.setPosition(p.getX() + Constants.RUNNER_WIDTH / 2, p.getY() - Constants.GROUND_Y - Constants.GROUND_HEIGHT / 2);

        player.setVisible(p.isVisible());
    }
    
    public Stage getStage(){
        return mapStage;
    }
    
    public void addUnits(List<Barrier> barr, float y, World world){
        for(Barrier b : barr){
            switch(b.GetType()){
                case STOP:
                    mapStage.addActor(new StopObj(world, b.GetX(),  - Constants.GROUND_HEIGHT / 2,
                        30, 60,
                        AssetLoader.btn));
                    break;
                case SNAKE:
                    mapStage.addActor(new Snake(AssetLoader.btn, b.GetX() ,20f  - Constants.GROUND_HEIGHT / 2,100f,20f,world));
                    break;
                case ANTELOPHE:
                    mapStage.addActor(new Antelope(AssetLoader.btn,b.GetX(), - Constants.GROUND_HEIGHT / 2,100f,30f,world));
                    break;
                case TREE:
                    mapStage.addActor(new Tree(world, b.GetX(),0,
                    10,70,70,10, AssetLoader.btn));
                    break;
                case GIRAFFE:
                    mapStage.addActor(new Giraffe(world, b.GetX(), 40 , 100f
                    , 40f, 10f, 60f
                    , 40f, 20f, AssetLoader.btn));
                    break;
                case COIN:
                    mapStage.addActor(new Coin(AssetLoader.btn, b.GetX() , b.GetY() - Constants.GROUND_HEIGHT / 2 - Constants.GROUND_Y + 40f, 40f, 40f, world));
                    break;
                default:
                    mapStage.addActor(new StopObj(world, b.GetX(), 0,
                        30, 60,
                        AssetLoader.btn));
                    break;
            }
        }
    }
    
    public void draw() {
        mapStage.draw();
    }
}
