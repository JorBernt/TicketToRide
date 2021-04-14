package jb.games.tickettoride.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import jb.games.tickettoride.tools.Anchor;
import jb.games.tickettoride.tools.DevTools;


public class Rail extends Actor {

    private final Texture texture;
    private final Texture textureSelected;
    private final Image image;

    private final Anchor anchor;

    private boolean selected;
    private boolean editMode;


    public Rail(float x, float y, float rotation) {
        selected = false;
        texture = new Texture("rail.png");
        textureSelected = new Texture("rail_selected.png");
        image = new Image(texture);
        image.setOrigin(Align.left);
        image.setRotation(rotation);
        image.setPosition(x,y);
        anchor = new Anchor(x,y, x+getWidth(), y);
        editMode = true;

    }

    public void setRotation(float angle) {
        image.setRotation(angle);
    }

    public void update(float delta) {
        if(editMode) {
            anchor.update(delta);
            image.setPosition(anchor.getX(), anchor.getY());
            image.setRotation(DevTools.getAngle(getPos(), anchor.getEndPos()));
        }
        if(selected) image.setDrawable(new SpriteDrawable(new Sprite(textureSelected)));
        else image.setDrawable(new SpriteDrawable(new Sprite(texture)));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void render(SpriteBatch batch) {
        image.draw(batch,1);
        if(editMode)
        anchor.render(batch);

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
    }

    public float getWidth() {
        return image.getWidth();
    }

    public float getHeight() {
        return image.getHeight();
    }


    public void setEditMode() {
        editMode = !editMode;
    }

    public Vector2 getEndPos() {
        return anchor.getEndPos();
    }

    public void dispose() {

    }

    @Override
    public float getRotation() {
        return image.getRotation();
    }


}
