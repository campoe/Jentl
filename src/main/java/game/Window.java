package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window extends JFrame {

    private Viewport viewport;
    private WindowHandler windowHandler;

    public Window(String title) {
        this.setTitle(title);
        this.viewport = new Viewport();
        this.getContentPane().add(this.viewport);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.windowHandler = new WindowHandler();
        this.addWindowListener(this.windowHandler);
        this.addWindowFocusListener(this.windowHandler);
        this.addWindowStateListener(this.windowHandler);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                viewport.repaint();
            }
        });
        this.viewport.start();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.viewport.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window win = new Window("Jentl");
            win.setVisible(true);
        });
    }

}
