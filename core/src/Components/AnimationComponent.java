package Components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Pool;

public class AnimationComponent implements Component, Pool.Poolable {


    public enum ANIMATIONSTATE {
        UP,DOWN,LEFT,RIGHT,TALKING,ATTACKING,HIT,PREPARING_TO_ATTACK,MISTIFIED,DYING,IMOBILE,DEAD

    }

    private float time = 0.0f;
    private boolean looping = false;

    private ArrayMap<ANIMATIONSTATE, Animation> animations = new ArrayMap<>();

    public AnimationComponent addAnimation(ANIMATIONSTATE state, Animation animation){
        this.animations.put(state, animation);
        return this;
    }


    public Animation getAnimation(ANIMATIONSTATE state){
        return animations.get(state);
    }

    public float getTime(){
        return time;
    }

    public void setTime(float time){
        this.time = time;
    }

    public void setLooping(boolean islooping){
        this.looping = islooping;
    }

    @Override
    public void reset() {
        time = 0.0f;
        animations.clear();;
        looping = false;

    }
}
