package com.pockmarks.HEX.UI;

import com.pockmarks.HEX.AI.*;
import com.pockmarks.HEX.action.*;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class HexUI {
    public static int player1Type = 0;
    public static int player2Type = 0;
    public final static int SIDE = 11;
    public static JFrame frame;
    public static JPanel panel;
    public static JMenuBar menuBar;
    public final AnyShapeButton[][] gamePiece;
    public final int gridSize;
    public final boolean swap;
    public int moveNumber;
    public MoveList moveList;
    public int currentPlayer;
    public PlayingEntity player2;
    public boolean gameOver = false;
    public PlayingEntity player1;
    private boolean game = true;

    HexUI(int gridSize, boolean swap) {
        this.gridSize = gridSize;
        gamePiece = new AnyShapeButton[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gamePiece[i][j] = new AnyShapeButton();
                gamePiece[i][j].x1 = i;
                gamePiece[i][j].y1 = j;
                gamePiece[i][j].addActionListener(new MouseAction(i, j, this));
            }
        }
        this.swap = swap;
        moveNumber = 1;
        moveList = new MoveList();
        currentPlayer = 1;
        game = true;
        gameOver = false;
        frame = new JFrame("HEX");
        panel = new JPanel();
        panel.setLayout(null);
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
//				setBounds(坐标x，坐标y，矩形长，矩形宽)j为横向计数，i为纵向计数
                gamePiece[i][j].setBounds(30 + 38 * j + 19 * i, 30 + 34 * i, 34, 40);
                panel.add(gamePiece[i][j]);
            }
        }
        JLabel[] letterText = new JLabel[SIDE];
        for (int i = 0; i < SIDE; i++) {
            letterText[i] = new JLabel(String.valueOf((char)('A' + i)));
            letterText[i].setForeground(Color.BLUE);
            letterText[i].setBounds(40 + 38 * i, 5, 34, 40);
            panel.add(letterText[i]);
        }
        JLabel[] numberText = new JLabel[11];
        for (int i = 0; i < SIDE; i++) {
            numberText[i] = new JLabel(String.valueOf(i + 1));
            numberText[i].setForeground(Color.RED);
            numberText[i].setBounds(10 + 19 * i, 30 + 34 * i, 34, 40);
            panel.add(numberText[i]);
        }
        //菜单***************************************
//        menuBar = new JMenuBar();
//        JMenu Operate = new JMenu("操作");
//        JMenuItem buildTree = new JMenuItem("后手");
//        buildTree.addActionListener(new Menu(this));
//        Operate.add(buildTree);
//        menuBar.add(Operate);
//        frame.setJMenuBar(menuBar);
        //*******************************************
        frame.add(panel);
        frame.setSize(80 + 38 * SIDE + 19 * SIDE, 110 + 34 * SIDE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static void setPlayer1(HexUI game) {
        if (game.player1Type == 0) game.player1 = new PlayerObject(1, game);
        else if (game.player1Type == 1) game.player1 = new PockmarksAI(1, game);
    }

    public static void setPlayer2(HexUI game) {
        if (game.player2Type == 0) game.player2 = new PlayerObject(2, game);
        else if (game.player2Type == 1) game.player2 = new PockmarksAI(2, game);
    }

    public static void main(String[] args) {
        System.out.println("1:玩家先手 电脑后手\n2:电脑先手 玩家后手\n3:双人博弈\nOther:机机大战");
        Scanner sc = new Scanner(System.in);
        int mode = sc.nextInt();
        if (mode == 1 || mode == 3) {
            player1Type = 0;
        } else player1Type = 1;
        if (mode == 2 || mode == 3) {
            player2Type = 0;
        } else player2Type = 1;
        HexUI hex_ui = new HexUI(11, true);
        setPlayer1(hex_ui);
        setPlayer2(hex_ui);
        hex_ui.run();

    }

    public void run() {
        while (game) {//Loop the game
            if (!checkForWinner()) {
                System.out.println("当前玩家："+GameAction.getPlayer((currentPlayer % 2) + 1, this));
                if (GameAction.getPlayer(currentPlayer, this) == null) {
                    System.out.println("为空");
                }
                GameAction.getPlayer(currentPlayer, this).getPlayerTurn();
            }
            currentPlayer = (currentPlayer % 2) + 1;
        }
        System.out.println("Thread died");
    }

    private boolean checkForWinner() {
        GameAction.checkedFlagReset(this);
        if (GameAction.checkWinPlayer(1, this)) {
            game = false;
            gameOver = true;
            player1.win();
            player2.lose();

        } else if (GameAction.checkWinPlayer(2, this)) {
            game = false;
            gameOver = true;
            player1.lose();
            player2.win();
        }
        return gameOver;
    }
}


