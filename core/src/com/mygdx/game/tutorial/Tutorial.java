package com.mygdx.game.tutorial;

import Enums.TutorialType;

/**
 * Created by User on 24.11.2016.
 */

public class Tutorial {

    private TutorialType type;

    public Tutorial(int tutorialLvl) {
        switch (tutorialLvl) {
            case 0:
                type = TutorialType.PLAY;
                break;
            case 1:
                type = TutorialType.TICKET;
                break;
            case 2:
                type = TutorialType.BUFF;
                break;
            case 3:
                type = TutorialType.PURCHASES;
                break;
            case 4:
                type = TutorialType.SKIN;
                break;
            case 5:
                type = TutorialType.DONE;
                break;
            default:
                type = null;
                break;
        }
    }

    public TutorialType getType() {
        return type;
    }
}
