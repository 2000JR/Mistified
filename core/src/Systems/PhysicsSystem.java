package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import Components.BodyComponent;
import Components.TransformComponent;

public class PhysicsSystem extends IteratingSystem {

    private final float MAX_TIME_STEP = 1/30F;
    private float accumulator = 0f;
    private World world;
    private Array<Entity> bodlegQueue;

    public PhysicsSystem(Family family,World world) {
        super(family.all(BodyComponent.class, TransformComponent.class).get());
        this.world = world;
        bodlegQueue = new Array<Entity>();
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        float frameTime = Math.min(deltaTime,0.25f);
       




    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodlegQueue.add(entity);

    }
}
