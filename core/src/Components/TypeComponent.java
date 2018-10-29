package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import static Helpers.Figures.OTHER;

public class TypeComponent implements Component, Pool.Poolable {




    private short type = OTHER;


    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Override
    public void reset() {
        type = OTHER;
    }
}
