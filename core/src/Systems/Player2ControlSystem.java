package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import Components.BodyComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Helpers.GameInput;
import Helpers.Mappers;

public class Player2ControlSystem extends IteratingSystem {

    private GameInput gameInput;
    private float x;
    private float y;

    public Player2ControlSystem(GameInput gameInput) {
        super(Family.all(PlayerComponent.class).get());
        this.gameInput = gameInput;


    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent stateComponent = Mappers.stateComponent.get(entity);
        BodyComponent bodyComponent = Mappers.bodyComponent.get(entity);

        if (gameInput.isUp() & gameInput.isRight()){
            bodyComponent.getBody().setLinearVelocity(5f, 5f);
            stateComponent.setDirection(StateComponent.DIRECTION.UPRIGHT);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        else if (gameInput.isUp() & gameInput.isLeft()){
            bodyComponent.getBody().setLinearVelocity(-5f, 5f);
            stateComponent.setDirection(StateComponent.DIRECTION.UPLEFT);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        else if (gameInput.isDown() & gameInput.isRight()){
            bodyComponent.getBody().setLinearVelocity(5f, -5f);
            stateComponent.setDirection(StateComponent.DIRECTION.DOWNRIGHT);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        else if (gameInput.isDown() & gameInput.isLeft()){
            bodyComponent.getBody().setLinearVelocity(-5f, -5f);
            stateComponent.setDirection(StateComponent.DIRECTION.DOWNLEFT);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }

         else if (gameInput.isLeft()){
             bodyComponent.getBody().setLinearVelocity(-5f, 0f);
             stateComponent.setDirection(StateComponent.DIRECTION.LEFT);
             stateComponent.setState(StateComponent.STATE.MOVING);

         }
       else if (gameInput.isUp()){
            bodyComponent.getBody().setLinearVelocity(0f, 5f);
            stateComponent.setDirection(StateComponent.DIRECTION.UP);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        else if (gameInput.isDown()){
            bodyComponent.getBody().setLinearVelocity(0f, -5f);
            stateComponent.setDirection(StateComponent.DIRECTION.DOWN);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }
        else if (gameInput.isRight()){
            bodyComponent.getBody().setLinearVelocity(5f, 0f);
            stateComponent.setDirection(StateComponent.DIRECTION.RIGHT);
            stateComponent.setState(StateComponent.STATE.MOVING);
        }

        if (gameInput.isAttack()) {


        }
        if(!gameInput.isRight() && !gameInput.isLeft() && !gameInput.isUp() && !gameInput.isDown()){
        stateComponent.setState(StateComponent.STATE.IDLE);

        }

    }
}
