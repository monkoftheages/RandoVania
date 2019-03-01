package randovania.view.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import randovania.control.GameController;
import randovania.utilities.Utilities;

/**
 * Created by b3_authors on 2/27/2019.
 */
public class FadeScreen {
    public static final float TRANSITION_SPEED = 3F;
    protected float alpha = 0F;
    protected Texture fadeTexture;
    protected boolean changed = false;
    protected int fading = 0;

    public static final int NOT_FADING = 0;
    public static final int FADING_IN = 1;
    public static final int FADING_OUT = 2;

    protected GameController gameController;

    public FadeScreen(GameController parent) {
        this.gameController = parent;
    }

    public void paintComponent(Batch batch, float delta, float x, float y) {
        if (fading == FADING_OUT)
            increaseFade(delta);
        else if (fading == FADING_IN)
            decreaseFade(delta);
        if (changed) {
            if (fadeTexture != null)
                fadeTexture.dispose();
            fadeTexture = fromScratch();
        }
        if(alpha != 0)
            batch.draw(fadeTexture, x - (Gdx.graphics.getWidth()/2), y - (Gdx.graphics.getHeight()/2), Gdx.graphics.getWidth()+4, Gdx.graphics.getHeight()+4);
        if(alpha == 0 && fading == FADING_IN)
            finishFadingIn();
        if(alpha == 1 && fading == FADING_OUT)
            finishFadingOut();

    }

    protected void finishFadingIn() {
        fading = NOT_FADING;
        gameController.finishedFadingIn();
    }

    protected void finishFadingOut() {
        fading = NOT_FADING;
        gameController.finishedFadingOut();
    }

    public void increaseFade(float delta) {
        if (alpha == 1)
            return;
        alpha += delta * TRANSITION_SPEED;
        if (alpha > 1)
            alpha = 1;
        changed = true;
    }

    public void decreaseFade(float delta) {
        if (alpha == 0)
            return;
        alpha -= delta * TRANSITION_SPEED;
        if (alpha < 0)
            alpha = 0;
        changed = true;
    }

    protected Texture fromScratch() {
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        p.setColor(new Color(0f, 0f, 0f, Utilities.round(alpha, 2)));
        p.fillRectangle(0, 0, 1, 1);
        Texture fadeTexture = new Texture(p);
        p.dispose();
        return fadeTexture;
    }

    public void fadeOut() {
        if(fading != NOT_FADING)
            return;
        fading = FADING_OUT;
    }

    public void fadeIn() {
        if(fading != NOT_FADING)
            return;
        fading = FADING_IN;
    }

    public void createTextures() {
        fadeTexture = fromScratch();
    }

    public void disposeTextures() {
        if (fadeTexture != null)
            fadeTexture.dispose();
    }
}
