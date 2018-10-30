package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class CollisionComponent implements Component, Pool.Poolable {

    private Entity collidedEntity;

    public Entity getCollidedEntity() {
        return collidedEntity;
    }

    public void setCollidedEntity(Entity collidedEntity) {
        this.collidedEntity = collidedEntity;
    }

    @Override
    public void reset() {
        collidedEntity = null;
    }
}
