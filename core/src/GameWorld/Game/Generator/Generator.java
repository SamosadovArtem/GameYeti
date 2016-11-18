/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Generator;

import GameWorld.Game.GameWorld;
import GameWorld.Game.Objects.Antelope;
import GameWorld.Game.Objects.Coin;
import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Giraffe;
import GameWorld.Game.Objects.Snake;
import GameWorld.Game.Objects.StopObj;
import GameWorld.Game.Objects.Tree;
import Helper.AssetLoader;
import Helper.Constants;
import LocationGenerator.Barrier;
import LocationGenerator.BarrierTypes;
import LocationGenerator.Location;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author qw
 */
public class Generator {

    private List<GameActor> objects = new ArrayList<GameActor>();
    private List<GameActor> mapObjects = new ArrayList<GameActor>();
    private List<Barrier> barr;

    public Generator(World world, int y, int startPosition, int barriersCount, GameWorld gameWorld) {
        barr = Location.GetBarrierList(startPosition, barriersCount, true);
        int width = Constants.APP_WIDTH;
        int height = Constants.APP_HEIGHT;

        for (Barrier b : barr) {
            switch (b.GetType()) {
                case STOP:
                    StopObj s = new StopObj(world, b.GetX(), y, width / 13, height / 4,
                            AssetLoader.elephantTexture);
                    StopObj ms = new StopObj(s.getBody(), b.GetX(), -Constants.GROUND_HEIGHT / 2,
                            width / 13, height / 4,
                            AssetLoader.elephantTexture);
                    objects.add(s);
                    mapObjects.add(ms);
                    break;
                case SNAKE:
                    Snake snake = new Snake(AssetLoader.btn, b.GetX(), Constants.GROUND_Y + 20f, width / 8, height / 24, world);
                    Snake mapSnake = new Snake(snake.getBody(), AssetLoader.btn, b.GetX(), Constants.GROUND_Y + 20f, width / 8, height / 24);
                    objects.add(snake);
                    mapObjects.add(mapSnake);
                    break;
                case ANTELOPHE:
                    Antelope a = new Antelope(AssetLoader.btn, b.GetX(), Constants.GROUND_Y, width / 8, height / 16, world, gameWorld);
                    Antelope ma = new Antelope(a.getBody(), AssetLoader.btn, b.GetX(), Constants.GROUND_Y, width / 8, height / 16);
                    objects.add(a);
                    mapObjects.add(ma);
                    break;
                case TREE:
                    Tree t = new Tree(world, b.GetX(), Constants.GROUND_Y + Constants.GROUND_HEIGHT / 2,
                            width / 80, height / 7, width / 11, height / 48, AssetLoader.btn);
                    Tree mt = new Tree(t.getBody(), b.GetX(), Constants.GROUND_Y + Constants.GROUND_HEIGHT / 2,
                            width / 80, height / 7, width / 11, height / 48, AssetLoader.btn);
                    objects.add(t);
                    mapObjects.add(mt);
                    break;
                case GIRAFFE:
                    Giraffe g = new Giraffe(world, b.GetX(), Constants.GROUND_Y + Constants.GROUND_HEIGHT / 2 + 40,
                            width / 8, height / 12, width / 80, height / 6, width / 20, height / 24, AssetLoader.btn);
                    Giraffe mg = new Giraffe(g.getBody(), b.GetX(), Constants.GROUND_Y + Constants.GROUND_HEIGHT / 2 + 40,
                            width / 8, height / 12, width / 80, height / 6, width / 20, height / 24, AssetLoader.btn);
                    objects.add(g);
                    mapObjects.add(mg);
                    break;
                case COIN:
                    Coin c = new Coin(AssetLoader.ctr, b.GetX(), b.GetY() + 40f, width / 20, height / 12, world);
                    Coin mc = new Coin(c.getBody(), AssetLoader.btn, b.GetX(), b.GetY() + 40f, width / 20, height / 12);
                    objects.add(c);
                    mapObjects.add(mc);
                    break;
                default:
                    break;
            }
        }

    }

    public List<GameActor> getObj() {
        return objects;
    }

    public List<GameActor> getMapObj() {
        return mapObjects;
    }

    public List<Barrier> getList() {
        return barr;
    }
}
