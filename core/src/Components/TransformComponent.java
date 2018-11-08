package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

public class TransformComponent implements Component, Pool.Poolable {

    private Vector3 position = new Vector3();
    private Vector2 scale = new Vector2(11, 11);
    private float rotation = 0.0f;
    private boolean isHidden = false;

    public Vector3 getPosition() {

        return position;
    }

    public void setPosition(Vector3 position) {

        this.position = position;
    }

    public void setPosition(Vector2 position){
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public Vector2 getScale() {

        return scale;
    }

    public void setScale(Vector2 scale) {

        this.scale = scale;
    }

    public float getRotation() {

        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {

        isHidden = hidden;
    }

    @Override
    public void reset() {
        position = Vector3.Zero;
        scale.x = 1f;
        scale.y = 1f;
        rotation = 0f;
        isHidden = false;
    }

}
