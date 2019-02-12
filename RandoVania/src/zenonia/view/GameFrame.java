package zenonia.view;

import zenonia.control.GameController;
import zenonia.control.MSKeyListener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.beans.EventHandler;

public class GameFrame extends JFrame {
    protected GameController controller;
    protected Preferences preferences;
    protected BoardGraphics boardGraphics;

    public GameFrame() {
        super("Game Frame");
        initializePreferences();
        addWindowListener(EventHandler.create(WindowListener.class, this, "onWindowClosing", null, "windowClosing"));
        createMenuBar();
        controller = new GameController(this);
        boardGraphics = new BoardGraphics(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(boardGraphics);
        MSKeyListener listener = new MSKeyListener(controller, this);
        addKeyListener(listener);
        getContentPane().addMouseListener(listener);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem eMenuItem = new JMenuItem("Settings");
        eMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                KeyBindingsFrame frame = new KeyBindingsFrame();
            }
        });
        file.add(eMenuItem);
        menuBar.add(file);
        setJMenuBar(menuBar);
    }

//    public Board getBoard() {
//        return controller.getBoard();
//    }

//    public GameController getController() {
//        return controller;
//    }

    protected void initializePreferences() {
        preferences = Preferences.userNodeForPackage(getClass());
//        MSKeyListener.RIGHT = preferences.getInt("key.right", MSKeyListener.RIGHT);
//        MSKeyListener.LEFT = preferences.getInt("key.left", MSKeyListener.LEFT);
//        MSKeyListener.UP = preferences.getInt("key.up", MSKeyListener.UP);
//        MSKeyListener.DOWN = preferences.getInt("key.down", MSKeyListener.DOWN);
//        MSKeyListener.FLAG = preferences.getInt("key.flag", MSKeyListener.FLAG);
//        MSKeyListener.RIGHT_ALT = preferences.getInt("key.rightalt", MSKeyListener.RIGHT_ALT);
//        MSKeyListener.LEFT_ALT = preferences.getInt("key.leftalt", MSKeyListener.LEFT_ALT);
//        MSKeyListener.UP_ALT = preferences.getInt("key.upalt", MSKeyListener.UP_ALT);
//        MSKeyListener.DOWN_ALT = preferences.getInt("key.downalt", MSKeyListener.DOWN_ALT);
//        MSKeyListener.FLAG_ALT = preferences.getInt("key.flagalt", MSKeyListener.FLAG_ALT);
//        MSKeyListener.FAST_CLEAR = preferences.getInt("key.fastclear", MSKeyListener.FAST_CLEAR);
//        MSKeyListener.CLEAR = preferences.getInt("key.clear", MSKeyListener.CLEAR);
//        MSKeyListener.RESTART = preferences.getInt("key.restart", MSKeyListener.RESTART);
    }

    public void onWindowClosing() {
        try {
//            preferences.putInt("key.right", MSKeyListener.RIGHT);
//            preferences.putInt("key.left", MSKeyListener.LEFT);
//            preferences.putInt("key.up", MSKeyListener.UP);
//            preferences.putInt("key.down", MSKeyListener.DOWN);
//            preferences.putInt("key.flag", MSKeyListener.FLAG);
//            preferences.putInt("key.rightalt", MSKeyListener.RIGHT_ALT);
//            preferences.putInt("key.leftalt", MSKeyListener.LEFT_ALT);
//            preferences.putInt("key.upalt", MSKeyListener.UP_ALT);
//            preferences.putInt("key.downalt", MSKeyListener.DOWN_ALT);
//            preferences.putInt("key.flagalt", MSKeyListener.FLAG_ALT);
//            preferences.putInt("key.fastclear", MSKeyListener.FAST_CLEAR);
//            preferences.putInt("key.clear", MSKeyListener.CLEAR);
//            preferences.putInt("key.restart", MSKeyListener.RESTART);
            preferences.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public GameController getController() {
        return controller;
    }

    public BoardGraphics getBoardGraphics() {
        return boardGraphics;
    }
}
