package jb.games.tickettoride.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import jb.games.tickettoride.tools.DevTools;


public class Rail extends Actor {

    private final Texture texture;
    private final Texture textureSelected;
    private final Texture anchorPoint;
    private final Image image;

    private final InputListener inputListener;


    private Circle anchorCircleStart;
    private Circle anchorCircleEnd;
    private final Image imageAnchorStart;
    private final Image imageAnchorEnd;



    private boolean selected;


    public Rail(float x, float y, float rotation) {
        selected = false;
        texture = new Texture("rail.png");
        textureSelected = new Texture("rail_selected.png");
        image = new Image(texture);
        image.setOrigin(Align.left);
        image.setRotation(rotation);
        image.setPosition(x,y);

        inputListener = new InputListener();
        image.addListener(inputListener);


        anchorPoint = new Texture("anchorpoint.png");
        anchorCircleStart = new Circle();
        anchorCircleEnd = new Circle();
        imageAnchorStart = new Image(anchorPoint);
        imageAnchorEnd = new Image(anchorPoint);





    }

    public void setRotation(float angle) {
        image.setRotation(angle);
        imageAnchorEnd.setPosition(image.getRight(), image.getTop());
    }

    public void update(float delta) {
        if(selected) image.setDrawable(new SpriteDrawable(new Sprite(textureSelected)));
        else image.setDrawable(new SpriteDrawable(new Sprite(texture)));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void render(SpriteBatch batch) {
        image.draw(batch,1);
        imageAnchorStart.draw(batch, 1);
        imageAnchorEnd.draw(batch,1);


    }

    public boolean isSelected() {
        return selected;
    }

    public float getX() {
        return image.getX();
    }

    public float getY() {
        return image.getY();
    }

    public Vector2 getPos() {
        return new Vector2(image.getX(), image.getY());
    }

    public void setPos(float x, float y) {
        image.setPosition(x,y);
        anchorCircleStart.setPosition(x,y);
        imageAnchorStart.setPosition(x-4,y+3);

    }

    public float getWidth() {
        return image.getWidth();
    }

    public float getHeight() {
        return image.getHeight();
    }


    public void dispose() {

    }


}
