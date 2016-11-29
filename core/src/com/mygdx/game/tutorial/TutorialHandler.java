package com.mygdx.game.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import Enums.TutorialType;
import Helper.Statistic;

/**
 * Created by User on 24.11.2016.
 */

public class TutorialHandler {

    private static Tutorial tutorial;

    public static void initTutorial() {
        Preferences prefs = Gdx.app.getPreferences("YetiGame");

        /*
            0 - Ни одно обучение не было пройдено.
            1 - Было пройдено обучение игре.
            2 - Было пройдено обучение билетам.
            3 - Было пройдено обучение бафам.
            4 - Было пройдено обучение внутриигровым покупкам.
            5 - Было пройдено обучение покупке скинов.
         */
        int tutorialLvl;

        if (!prefs.contains("tutorialLvl")) {
            prefs.putInteger("tutorialLvl", 0);
            prefs.flush();
        }
        tutorialLvl = prefs.getInteger("tutorialLvl");

        if(tutorialLvl == 1 && Statistic.getTickets() == 0){
            Statistic.addTicket();
        }

        tutorial = new Tutorial(5);
    }

    public static void increaseTutorialLvl() {
        Preferences prefs = Gdx.app.getPreferences("YetiGame");

        prefs.putInteger("tutorialLvl", tutorial.getType().getLvl() + 1);
        prefs.flush();

        tutorial = new Tutorial(tutorial.getType().getLvl()+1);
    }

    public static TutorialType getType() {
        return tutorial.getType();
    }
}
