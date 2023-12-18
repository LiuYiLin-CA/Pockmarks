package com.pockmarks.HEX.action;

import java.awt.*;


public interface PlayingEntity {

    void getPlayerTurn();

    boolean supportsUndo();

    void undoCalled();

    boolean supportsNewgame();

    void newgameCalled();

    boolean supportsSave();

    void quit();

    void win();

    void lose();

    void endMove();

    String getName();

    void setName(String name);

    Color getColor();

    void setColor(Color color);

    /**
     * Store a point to play when its your turn
     */
    void setMove(Object o, Point hex);

    /**
     * Return true to announce defeat mid-game
     * */
}
