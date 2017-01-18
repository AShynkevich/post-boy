package com.alex.post.boy;

import com.alex.post.boy.frame.MainFrame;
import com.alex.post.boy.frame.util.UiUtils;

/**
 * @author Aliaksandr_Shynkevich
 */
public class PostStarter {
    public static void main(String[] args) {
            UiUtils.initLookAndFeel();
            new MainFrame();
    }
}
