package Enums;

/**
 * Created by User on 24.11.2016.
 */

public enum TutorialType {
    PLAY(0),
    TICKET(1),
    BUFF(2),
    PURCHASES(3),
    SKIN(4),
    DONE(5);

    private int lvl;

    TutorialType(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }
}

