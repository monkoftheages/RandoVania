package zenonia.model;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class Wall {
    protected Rectangle rect;
    protected boolean matrixSet = false;
    protected Texture wallTexture;

    public Wall() {
//        shapeRenderer = new ShapeRenderer();
    }

    public Wall(int x, int y, int width, int height) {
        shapeRenderer = new ShapeRenderer();
        rect = new Rectangle(x, y, width, height);
        wallTexture = createTexture();
    }

    public Wall(float x, float y, float width, float height) {
        shapeRenderer = new ShapeRenderer();
        rect = new Rectangle(x, y, width, height);
        wallTexture = createTexture();
    }

    protected Texture createTexture() {
        Pixmap p = new Pixmap((int)rect.width, (int)rect.height, Pixmap.Format.RGBA8888);
        p.setColor(Color.BLUE);
        p.fillRectangle(0, 0, (int)rect.width, (int)rect.height);
        Texture t = new Texture(p);
        p.dispose();
        return t;
    }

    protected ShapeRenderer shapeRenderer;

    public void paintComponent(SpriteBatch batch) {
//        batch.setColor(Color.BLUE);
//        batch.draw(rect.x, rect.y, rect.width, rect.height);
//        batch.end();
//        if(!matrixSet)
//            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.BLUE);
//        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
//        shapeRenderer.end();
//        batch.begin();

//        batch.setColor(Color.BLUE);
        batch.draw(wallTexture, rect.x, rect.y);

    }

    public Rectangle getBoundingBox() {
        return rect;
    }

    public void dispose() {
        wallTexture.dispose();
    }
}
