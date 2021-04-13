package jb.games.tickettoride.entities;

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
        selected = false;
        texture = new Texture("rail.png");
        textureSelected = new Texture("rail_selected.png");
        image = new Image(texture);
        image.setRotation(rotation);
        image.setPosition(x,y);
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
    }


    public void dispose() {

    }


}
