package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class HealthComponent implements Component, Pool.Poolable{



    private int health;




    @Override
    public void reset() {

        health = 3;
    }
}
