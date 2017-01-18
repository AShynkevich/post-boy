package com.alex.post.boy.frame.util;

import java.awt.*;
import javax.swing.*;

/**
 * Date: Jan 11, 2017
 *
 * @author Aliaksandr_Shynkevich
 */
public final class UiUtils {
    private UiUtils() {
    }

    private static final Font DEFAULT_FONT = new Font("Source code pro", Font.PLAIN, 12);

    public static final void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("MenuBar.background", Color.DARK_GRAY);

            UIManager.put("Menu.foreground", Color.LIGHT_GRAY);
            UIManager.put("Menu.selectionBackground", Color.BLACK);
            UIManager.put("Menu.selectionForeground", Color.LIGHT_GRAY);
            UIManager.put("Menu.font", DEFAULT_FONT);

            UIManager.put("MenuItem.foreground", Color.LIGHT_GRAY);
            UIManager.put("MenuItem.background", Color.DARK_GRAY);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class FillPainter implements Painter<JComponent> {

        private final Color color;

        FillPainter(Color c) {
            color = c;
        }

        @Override
        public void paint(Graphics2D g, JComponent object, int width, int height) {
            g.setColor(color);
            g.fillRect(0, 0, width, height);
        }
    }
}
