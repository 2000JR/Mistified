package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;

import Components.BodyComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Helpers.GameInput;
import Helpers.Mappers;

public class PlayerControlSystem extends IteratingSystem {

    private GameInput gameInput;

    public PlayerControlSystem(GameInput gameInput) {
        super(Family.all(PlayerComponent.class).get());
        this.gameInput = gameInput;


    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent stateComponent = Mappers.stateComponent.get(entity);
        BodyComponent bodyComponent = Mappers.bodyComponent.get(entity);



         if (gameInput.isLeft()){
             bodyComponent.getBody().setLinearVelocity(-5f, 0f);
             stateComponent.setDirection(StateComponent.DIRECTION.LEFT);
             stateComponent.setState(StateComponent.STATE.MOVING);
         }
        if (gameInput.isUp()){
            bodyComponent.getBody().setLinearVelocity(0f, 5f);
            stateComponent.setDirection(StateComponent.DIRECTION.UP);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        if (gameInput.isDown()){
            bodyComponent.getBody().setLinearVelocity(0f, -5f);
            stateComponent.setDirection(StateComponent.DIRECTION.DOWN);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        if (gameInput.isRight()){
            bodyComponent.getBody().setLinearVelocity(5f, 0f);
            stateComponent.setDirection(StateComponent.DIRECTION.RIGHT);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        if(!gameInput.isRight() && !gameInput.isLeft() && !gameInput.isUp() && !gameInput.isDown()){
        stateComponent.setState(StateComponent.STATE.IDLE);

        }

    }
}
