package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.mistified.Mistified;

import Components.CollisionComponent;

public class CollisionSystem extends IteratingSystem{

    private PooledEngine engine;
    private World world;
    private Entity Player;
    private Mistified game;

    public CollisionSystem(PooledEngine engine, World world, Entity player, Mistified game) {
        super(Family.all(CollisionComponent.class).get());
        this.engine = engine;
        this.world = world;
        Player = player;
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
//CollisionComponent =



    }
}
