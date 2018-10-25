package Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyGenerator {
    private World world;

    public BodyGenerator(World world) {
        this.world = world;
    }


    public Body createBody(Vector2 position, float size, float force, BodyDef.BodyType type, int bodyType, short self, short interaction) {
        Body body;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

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
        bdef.gravityScale = force;
        body = world.createBody(bdef);
        Shape shape;

        switch (bodyType) {
            case 0:
                shape = new CircleShape();
                shape.setRadius(size / 2);
                Gdx.gl.glEnable(2);


                break;
            case 1:
                shape = new PolygonShape();
                ((PolygonShape) shape).setAsBox(size / 2, size / 2);
                break;
            default:
                shape = new CircleShape();
                shape.setRadius(size / 2);
                break;

        }


        fdef.shape = shape;
        fdef.density = 1f;
        fdef.restitution = 1f;
        fdef.filter.categoryBits = self;
        fdef.filter.maskBits = interaction;
        fdef.isSensor = false;
        body.createFixture(fdef);

        return body;

    }
}

