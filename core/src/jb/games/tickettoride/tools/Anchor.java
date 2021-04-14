package jb.games.tickettoride.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Anchor {

    private Texture texture;
    private Image image;
    private Anchor endPoint;

    private Circle circleEnd;
    private Circle circleStart;

    private boolean selectedEnd;
    private boolean selectedStart;
    private double distToPoint;
    private float constDist;

    public Anchor(){}

    public Anchor(float x, float y, Anchor start) {
        texture = new Texture("anchorpoint.png");
        image = new Image(texture);
        image.setPosition(x, y);
        endPoint = null;
        selectedEnd = false;
        distToPoint = 0f;

    }

    public Anchor(float x, float y, float endX, float endY) {
        texture = new Texture("anchorpoint.png");
        image = new Image(texture);
        image.setPosition(x, y);
        endPoint = new Anchor(endX, endY, this);
        selectedEnd = false;
        distToPoint = 0f;
        constDist = endX-x;
        createSelectCircle();
    }

    public void createSelectCircle() {
        circleStart = new Circle();
        circleStart.setPosition(getX(), getY());
        circleStart.setRadius(8);

        circleEnd = new Circle();
        circleEnd.setPosition(endPoint.getX(), endPoint.getY());
        circleEnd.setRadius(8);
    }

    public void update(float delta) {
        distToPoint = DevTools.getDistance(getPos(), endPoint.getPos());
        selectedEnd = circleEnd.contains(DevTools.getMouse());
        selectedStart = circleStart.contains(DevTools.getMouse());
        if(selectedEnd) {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                move(DevTools.getMouse().x, DevTools.getMouse().y);
            }
        }
        if(selectedStart) {
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                moveAll(DevTools.getMouse().x, DevTools.getMouse().y);
            }
        }
    }


    public void move(float x, float y) {
        circleEnd.setPosition(x, y);
        endPoint.setPosition(x,y);
    }
    public void moveAll(float x, float y) {
        circleEnd.setPosition(x+(float)DevTools.getDeltaX(getPos(), endPoint.getPos()),y+(float)DevTools.getDeltaY(getPos(), endPoint.getPos()));
        endPoint.setPosition(x+(float)DevTools.getDeltaX(getPos(), endPoint.getPos()),y+(float)DevTools.getDeltaY(getPos(), endPoint.getPos()));
        System.out.println(DevTools.getDeltaX(getPos(), endPoint.getPos()));
        circleStart.setPosition(x,y);
        image.setPosition(x,y);
    }

    public void render(SpriteBatch batch) {
        image.draw(batch, 1);
        if(endPoint!=null)
        endPoint.render(batch);
    }

    public void setPosition(float x, float y) {
        image.setPosition(x,y);
    }

    public Vector2 getEndPos() {
        return endPoint.getPos();
    }

    public Vector2 getPos() {
        return new Vector2(image.getX(), image.getY());
    }

    public float getX() {
        return image.getX();
    }

    public float getY() {
        return image.getY();
    }

    public boolean isSelectedEnd() {
        return selectedEnd;
    }

}
