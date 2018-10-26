package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

public class BodyComponent implements Component, Pool.Poolable {

    private Body body;
    private boolean dead = false;


    public Body getBody() {

        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
    public void setActive(boolean active){
        body.setActive(active);
    }

    public boolean isActive(){
        return body.isActive();
    }

    @Override
    public void reset() {
        body = null;
        dead = false;
    }
}
