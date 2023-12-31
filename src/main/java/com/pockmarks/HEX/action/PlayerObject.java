package com.pockmarks.HEX.action;

import com.pockmarks.HEX.UI.HexUI;

import java.awt.*;
import java.util.*;

public class PlayerObject implements PlayingEntity {
    private String name;
    private Color color = Color.RED;
    private final int team;
    private LinkedList<Point> hex = new LinkedList<Point>();
    private final HexUI game;

    public PlayerObject(int team, HexUI game) {
        color = Color.red;
        name = "Human";
        this.team = team;
        this.game = game;
    }

    @Override
    public void getPlayerTurn() {
        while (true) {
            while (hex.size() == 0) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (hex.get(0).equals(new Point(-1, -1))) {
                hex.remove(0);
                break;
            }
            if (GameAction.makeMove(this, (byte) team, hex.get(0), game)) {
                hex.remove(0);
                break;
            }
            hex.remove(0);
        }
    }

    @Override
    public void undoCalled() {
    }

    @Override
    public void newgameCalled() {
        endMove();
    }

    @Override
    public boolean supportsUndo() {
        return true;
    }

    @Override
    public boolean supportsNewgame() {
        return true;
    }

    @Override
    public void quit() {
        endMove();
    }

    @Override
    public void win() {
        System.out.println("玩家赢");
    }

    @Override
    public void lose() {
        System.out.println("玩家输");
    }

    @Override
    public boolean supportsSave() {
        return false;
    }

    @Override
    public void endMove() {
        hex.add(new Point(-1, -1));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setMove(Object o, Point hex) {
        if (o instanceof GameAction && game.currentPlayer == team) {
            this.hex = new LinkedList<Point>();
            this.hex.add(hex);
        }
    }

}