package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import Components.BodyComponent;
import Components.TransformComponent;
import Helpers.Mappers;

public class PhysicsSystem extends IteratingSystem {

    private final float MAX_TIME_STEP = 1/30F;
    private float accumulator = 0f;
    private World world;
    private Array<Entity> bodlegQueue;

    public PhysicsSystem(World world) {
        super(Family.all(BodyComponent.class, TransformComponent.class).get());
        this.world = world;
        bodlegQueue = new Array<Entity>();
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        float frameTime = Math.min(deltaTime,0.25f);
        accumulator += frameTime;
        if (accumulator >= MAX_TIME_STEP){

            world.step(MAX_TIME_STEP,6,2);
            accumulator += MAX_TIME_STEP;

            for(Entity entity: bodlegQueue){
            TransformComponent transformComponent = Mappers.transformComponent.get(entity);
            BodyComponent bodyComponent = Mappers.bodyComponent.get(entity);
                Vector2 position = bodyComponent.getBody().getPosition();

                transformComponent.setPosition(position);
                transformComponent.setRotation(bodyComponent.getBody().getAngle() * MathUtils.radDeg);



            }


        bodlegQueue.clear();


        }
       




    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodlegQueue.add(entity);

    }
}
