package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;

import Components.PlayerComponent;
import Components.StateComponent;
import Helpers.GameInput;
import Helpers.Mappers;

public class PlayerControlSystem extends IteratingSystem {

    private GameInput gameInput;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());
        this.gameInput = gameInput;


    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent stateComponent = Mappers.stateComponent.get(entity);
    }
}
