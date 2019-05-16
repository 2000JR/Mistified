package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.mistified.Mistified;

import Components.AnimationComponent;
import Components.RenderableComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Helpers.Mappers;

public class AnimationSystem extends IteratingSystem{
    private static final String TAG = Mistified.class.getSimpleName();
    public AnimationSystem() {
            super(Family.all(RenderableComponent.class, TextureComponent.class, AnimationComponent.class).get());

    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    AnimationComponent animationComponent = Mappers.animationComponent.get(entity);
    TextureComponent textureComponent = Mappers.textureComponent.get(entity);
    StateComponent stateComponent = Mappers.stateComponent.get(entity);

    animationComponent.setTime(deltaTime + animationComponent.getTime());
    float stateTime = animationComponent.getTime();

        com.badlogic.gdx.graphics.g2d.Animation currentAnimation;

        switch(stateComponent.getState()){
            case MOVING:
                if(stateComponent.getDirection() == stateComponent.getDirection().DOWN){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded DOWN" );
                        return;
                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().DOWNLEFT){
                currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN); // Intentional
                animationComponent.setLooping(true);

                if(currentAnimation == null){
                    Gdx.app.log(TAG,"Animation not loaded DOWN" );
                    return;
                }
                textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
            }
                if(stateComponent.getDirection() == stateComponent.getDirection().DOWNRIGHT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN); // Intentional
                    animationComponent.setLooping(true);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded DOWN" );
                        return;
                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().UP){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded UP" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().UPLEFT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP); // Intentional
                    animationComponent.setLooping(true);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded UP" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().UPRIGHT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP); // Intentional
                    animationComponent.setLooping(true);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded UP" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().LEFT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.LEFT);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded LEFT" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().RIGHT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT);
                    animationComponent.setLooping(true);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded LEFT" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrame(stateTime ,animationComponent.isLooping()));
                }

               break;
            case IDLE:

                if(stateComponent.getDirection() == stateComponent.getDirection().DOWN){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded DOWN" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().DOWNLEFT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded DOWN" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().DOWNRIGHT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded DOWN" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().UP){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded UP" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().UPLEFT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded UP" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().UPRIGHT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.UP);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded UP" );
                        return;

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().LEFT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.LEFT);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded LEFT" );

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);
                }
                if(stateComponent.getDirection() == stateComponent.getDirection().RIGHT){
                    currentAnimation = animationComponent.getAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT);
                    animationComponent.setLooping(false);

                    if(currentAnimation == null){
                        Gdx.app.log(TAG,"Animation not loaded Right" );

                    }
                    textureComponent.setRegion((TextureRegion) currentAnimation.getKeyFrames()[0]);


                }
                break;

        }

    }
}
