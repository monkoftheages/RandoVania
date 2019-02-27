package randovania.model.objects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import randovania.model.objects.GameObject;


public class Wall extends GameObject {
    protected Rectangle rect;
    protected boolean matrixSet = false;
    protected Texture wallTexture;

    public Wall() {
//        shapeRenderer = new ShapeRenderer();
    }

    public Wall(int x, int y, int width, int height) {
        rect = new Rectangle(x, y, width, height);
    }

    public Wall(float x, float y, float width, float height) {
        rect = new Rectangle(x, y, width, height);
    }

    public Wall(double x, double y, double width, double height) {
        rect = new Rectangle((float)x, (float)y, (float)width, (float)height);
    }

    protected Texture createTexture() {
        Pixmap p = new Pixmap((int)rect.width, (int)rect.height, Pixmap.Format.RGBA8888);
        p.setColor(Color.BLUE);
        p.fillRectangle(0, 0, (int)rect.width, (int)rect.height);
        Texture t = new Texture(p);
        p.dispose();
        return t;
    }

    public void moveWall(float x, float y) {
        rect.x += x;
        rect.y += y;
    }

    public void resize(float x, float y) {
        rect.width += x;
        rect.height += y;
    }

    public void printInfo() {
        System.out.println("createWall(" + rect.x + ", " + rect.y + ", " + rect.width + ", " + rect.height + "),");
    }

    public void paintComponent(SpriteBatch batch) {
        batch.draw(wallTexture, rect.x, rect.y, rect.width, rect.height);
    }

    public Rectangle getBoundingBox() {
        return rect;
    }

    public void createTextures() {
        wallTexture = createTexture();
    }

    public void disposeTextures() {
        wallTexture.dispose();
    }
}
