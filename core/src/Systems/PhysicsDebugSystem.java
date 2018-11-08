package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import Components.BodyComponent;

public class PhysicsDebugSystem extends IteratingSystem {

    private Box2DDebugRenderer box2DDebugRenderer;
    private World world;
    private OrthographicCamera camera;


    public PhysicsDebugSystem(World world, OrthographicCamera camera) {
        super(Family.all(BodyComponent.class).get());
        box2DDebugRenderer = new Box2DDebugRenderer();
        this.world = world;
        this.camera = camera;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {





    }
}
