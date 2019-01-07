package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.ashley.core.Entity;


import Components.CollisionComponent;

public class CollisionManager implements ContactListener {






    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Contact", "touched");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();


        if (fa.getBody().getUserData() instanceof Entity){
            Entity entity = (Entity) fa.getBody().getUserData();
            entityCollision(entity, fb);

        }
        else if (fa.getBody().getUserData() instanceof  Entity){
            Entity entity = (Entity) fb.getBody().getUserData();
            entityCollision(entity, fa);
        }
    }
    private void entityCollision(Entity entity, Fixture fixture){
        if(fixture.getBody().getUserData() instanceof Entity){
            Entity collisionEntity = (Entity) fixture.getBody().getUserData();
            CollisionComponent collisionA = entity.getComponent (CollisionComponent.class);
            CollisionComponent collisionB = collisionEntity.getComponent (CollisionComponent.class);

            if(collisionA != null){
                collisionA.setCollidedEntity(collisionEntity);
            }
            else if (collisionB != null){
                collisionB.setCollidedEntity(entity);

            }

        }


    }
    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
