package Helpers;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelCollisionGenerator {

    //        body = createBody(new Vector2(camera.viewportWidth/2, camera.viewportHeight), random, 1, BodyDef.BodyType.DynamicBody, 0, PLAYER, GROUND);


    public static final String TAG = LevelCollisionGenerator.class.getSimpleName();
    private World world;
    private PooledEngine engine;


    public LevelCollisionGenerator (World world, PooledEngine engine){
        this.world = world;
        this.engine = engine;

    }
    public void createCollisionLevel(){
        Body body;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        Entitu

            switch (type) {
                case StaticBody:
                    bdef.type = BodyDef.BodyType.StaticBody;
                    break;
                case DynamicBody:
                    bdef.type = BodyDef.BodyType.DynamicBody;
                    break;
                case KinematicBody:
                    bdef.type = BodyDef.BodyType.KinematicBody;
                    break;

            }

            bdef.position.set(position.x, position.y);


            bdef.gravityScale = 1;
            Shape shape;

            switch (bodyType) {
                case 0:
                default:
                    shape = new CircleShape();
                    shape.setRadius(dimensions.x / 2);
                    bdef.position.set(position.x + dimensions.x/2,position.y + dimensions.y/2);
                    Gdx.gl.glEnable(2);


                    break;
                case 1:
                    shape = new PolygonShape();
                    ((PolygonShape) shape).setAsBox(dimensions.x / 2, dimensions.y / 2);
                    bdef.position.set(position.x + dimensions.x/2,position.y + dimensions.y/2);
                    break;


            }
            body = world.createBody(bdef);

            fdef.shape = shape;
            fdef.density = 1f;
            fdef.restitution = 1f;
            fdef.friction = 0;
            fdef.isSensor = false;
            body.createFixture(fdef).setUserData(entity);

            shape.dispose();

        }


}
