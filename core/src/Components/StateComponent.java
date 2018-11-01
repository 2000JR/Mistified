package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class StateComponent implements Component, Pool.Poolable {

    public enum DIRECTION{
        UP,DOWN,LEFT,RIGHT,NONE

    }
    public enum STATE {
        IDLE,MOVING,TALKING,ATTACKING,HIT,PREPARING_TO_ATTACK,MISTIFIED,DYING,IMOBILE,DEAD
    }

    private STATE state;
    private DIRECTION direction;

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public STATE getState() {

        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    @Override
    public void reset() {
    state =  null;
    direction = null;
    }
}
