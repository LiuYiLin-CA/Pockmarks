package com.pockmarks.HEX.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomActionListener implements ActionListener {
    private HexUI UI;

    public CustomActionListener(HexUI UI) {
        this.UI = UI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 改变对象的属性
        UI.currentPlayer = 2;
    }
}
