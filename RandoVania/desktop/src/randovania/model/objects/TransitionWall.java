package randovania.model.objects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class TransitionWall extends Wall {
    public float playerStartX = 0;
    public float playerStartY = 0;
    public int connectingRoom = 0;

    public TransitionWall() {
//        shapeRenderer = new ShapeRenderer();
    }

    public TransitionWall(float[] data) {
        this(data[0], data[1], data[2], data[3]);
        playerStartX = data[4];
        playerStartY = data[5];
        connectingRoom = (int)data[6];
    }

    public TransitionWall(float x, float y, float width, float height) {
        rect = new Rectangle(x, y, width, height);
    }

    public TransitionWall(int x, int y, int width, int height) {
        this((float)x, (float)y, (float)width, (float)height);
    }

    public TransitionWall(double x, double y, double width, double height) {
        this((float)x, (float)y, (float)width, (float)height);
    }

    protected Texture createTexture() {
        Pixmap p = new Pixmap((int)rect.width, (int)rect.height, Pixmap.Format.RGBA8888);
        p.setColor(Color.RED);
        p.fillRectangle(0, 0, (int)rect.width, (int)rect.height);
        Texture t = new Texture(p);
        p.dispose();
        return t;
    }

    public void printInfo() {
        System.out.println("createWall(" + rect.x + ", " + rect.y + ", " + rect.width + ", " + rect.height + "),");
    }

    public void paintComponent(SpriteBatch batch) {
        batch.draw(wallTexture, rect.x, rect.y, rect.width, rect.height);
    }

    public void createTextures() {
        wallTexture = createTexture();
    }

    public void disposeTextures() {
        wallTexture.dispose();
    }
}
