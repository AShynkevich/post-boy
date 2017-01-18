package com.alex.post.boy.frame;

import static com.alex.post.boy.frame.util.UiUtils.*;

import com.alex.post.boy.frame.component.RequestPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author Aliaksandr_Shynkevich
 */
public class MainFrame extends JFrame {

    private static final Dimension MINIMUM_SIZE = new Dimension(1024, 768);
    private static final String REQUEST_PANEL_CAPTION = "GET Requester";


    public MainFrame() {
        super("Post boy");
        JMenuBar menuBar = initMenuBar();
        setJMenuBar(menuBar);

        RequestPanel requestPanel = new RequestPanel();
        JTabbedPane tabs = new JTabbedPane();
        tabs.add(REQUEST_PANEL_CAPTION, requestPanel);

        add(tabs);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(MINIMUM_SIZE);
        setLocationRelativeTo(null);
        pack();

        setVisible(true);
    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(initFileMenu());
        return menuBar;
    }

    private JMenu initFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        file.add(new JSeparator());
        file.add(exit);
        return file;
    }
}
