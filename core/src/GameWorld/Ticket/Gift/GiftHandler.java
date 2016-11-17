/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Ticket.Gift;

import Enums.BuffType;
import Enums.GiftType;
import GameObjects.Picture;
import Helper.AssetLoader;
import Helper.BuffsInfo;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

/**
 *
 * @author Pablo
 */
public class GiftHandler {

    private static BuffType buffType;

    public static void takeGift(GiftType type) {
        if (null != type) {
            switch (type) {
                case COIN:
                    takeCoins();
                    break;
                case TICKET:
                    takeTicket();
                    break;
                case BUFF:
                    takeBuff();
                    break;
                case SKIN:
                    takeSkin();
                case MAP:
                    takeMap();
                default:
                    break;
            }
        }
    }

    private static void takeCoins() {
        Random rnd = new Random();
        int count = rnd.nextInt(3);
        int value = 0;
        switch (count) {
            case 0:
                value = 10;
                break;
            case 1:
                value = 25;
                break;
            case 2:
                value = 50;
                break;
            case 3:
                value = 100;
                break;
            default:
                break;
        }
        Statistic.addCoins(value);
    }

    private static void takeTicket() {
        Statistic.addTicket();
    }

    private static void takeBuff() {
        Random rnd = new Random();
        switch (rnd.nextInt()) {
            case 0:
                buffType = BuffType.JUMPCOUNT;
                BuffsInfo.getJumpCountBuff().update();
                break;
            case 1:
                buffType = BuffType.JUMPPOWER;
                BuffsInfo.getJumpPowerBuff().update();
                break;
            case 2:
                buffType = BuffType.DIRECTION_COFF;
                BuffsInfo.getDirectionCoffBuff().update();
                break;
            case 3:
                buffType = BuffType.FRICTION;
                BuffsInfo.getFrictionBuff().update();
                break;
            case 4:
                buffType = BuffType.HEIGHT_SKY_COFF;
                BuffsInfo.getHeightSkyCoffBuff().update();
                break;
            case 5:
                buffType = BuffType.POWER_COFF;
                BuffsInfo.getPowerCoffBuff().update();
                break;
            case 6:
                buffType = BuffType.COINS;
                BuffsInfo.getCoinsBuff().update();
                break;
            case 7:
                buffType = BuffType.GRAVITY;
                BuffsInfo.getGravityBuff().update();
                break;
            default:
                break;
        }
    }

    private static void takeSkin() {
    }

    private static void takeMap() {
    }

    public static Texture getPicture(GiftType type) {
        if (type != GiftType.BUFF) {
            return AssetLoader.loadTicketGift(type);
        } else {
            return AssetLoader.loadTicketGift(buffType);
        }
    }

    public static GiftType getGiftType() {
        Random rnd = new Random();
        int i = rnd.nextInt(100);
        if (i < 40) {
            return GiftType.COIN;
        } else if (i < 60) {
            return GiftType.TICKET;
        } else if (i < 80) {
            return GiftType.BUFF;
        } else if (i < 95) {
            return GiftType.SKIN;
        } else {
            return GiftType.MAP;
        }
    }
}
