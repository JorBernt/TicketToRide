package jb.games.tickettoride.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;


public class Rail {

    private final Texture texture;
    private final Texture textureSelected;
    private final Image image;

    private Vector2 pos;

    private boolean selected;


    public Rail(float x, float y, float rotation) {
        pos = new Vector2(x,y);
        selected = false;
        texture = new Texture("rail.png");
        textureSelected = new Texture("rail_selected.png");
        image = new Image(texture);
        image.setRotation(rotation);
        image.setPosition(x,y);
    }

    public void update(float delta) {

        float mX = Gdx.input.getX();
        float mY = Gdx.graphics.getHeight()-Gdx.input.getY();
        double deltaX = Math.abs(pos.x+8-mX);
        double deltaY = Math.abs(pos.y+8-mY);
        double dist = Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
        selected = dist < 20;


        if(selected) image.setDrawable(new SpriteDrawable(new Sprite(textureSelected)));
        else image.setDrawable(new SpriteDrawable(new Sprite(texture)));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void render(SpriteBatch batch) {
        image.draw(batch,1);
    }

    public boolean isSelected() {
        return selected;
    }


    public void dispose() {

    }


}
