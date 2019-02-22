package zenonia.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import zenonia.view.ScreenController;
import zenonia.view.graphics.WorldGraphics;

public class GameApplication {
    protected GameController gameController;
    protected ScreenController screenController;
    protected LwjglApplicationConfiguration config;
    protected LwjglApplication app;
    protected WorldGraphics graphics;

    public GameApplication() {
        config = new LwjglApplicationConfiguration();
        config.x = 1920 + 1200;
        config.y = 600;
        graphics = new WorldGraphics(this);
        screenController = new ScreenController(this);
        app = new LwjglApplication(screenController, config);
        loadGame();
        graphics.allowDrawing(true);
        Gdx.input.setInputProcessor(new InputListener(gameController));
    }

    protected void loadGame() {
        gameController = new GameController(this);
        screenController.setScreen(graphics);
    }

    public WorldGraphics getBoardGraphics() {
        return graphics;
    }

    public LwjglApplicationConfiguration getConfig() {
        return config;
    }

    public GameController getGameController() {
        return gameController;
    }
    public ScreenController getScreenController() {
        return screenController;
    }
}
