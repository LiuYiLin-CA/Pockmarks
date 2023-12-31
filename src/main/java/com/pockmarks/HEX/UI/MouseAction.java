package com.pockmarks.HEX.UI;

import com.pockmarks.HEX.action.GameAction;

import java.awt.*;
import java.awt.event.*;

public class MouseAction implements ActionListener {
    int x, y;
    HexUI hexUI;

    public MouseAction(int x, int y, HexUI hexUI) {
        this.x = x;
        this.y = y;
        this.hexUI = hexUI;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        GameAction.setPiece(new Point(x, y), hexUI);
    }
}
