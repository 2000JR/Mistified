package Helpers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Components.AnimationComponent;
import Components.BodyComponent;
import Components.CollisionComponent;
import Components.InventoryComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Components.TransformComponent;
import Components.TypeComponent;

public class Mappers {

    public static final ComponentMapper<BodyComponent> bodyComponent = ComponentMapper.getFor(BodyComponent.class);

    public static final ComponentMapper<CollisionComponent> collisionComponent = ComponentMapper.getFor(CollisionComponent.class);

    public static final ComponentMapper<PlayerComponent> playerComponent = ComponentMapper.getFor(PlayerComponent.class);

    public static final ComponentMapper<StateComponent> stateComponent = ComponentMapper.getFor(StateComponent.class);

    public static final ComponentMapper<TransformComponent> transformComponent = ComponentMapper.getFor(TransformComponent.class);

    public static final ComponentMapper<TypeComponent> typeComponent = ComponentMapper.getFor(TypeComponent.class);

    public static final ComponentMapper<AnimationComponent> animationComponent = ComponentMapper.getFor(AnimationComponent.class);

    public static final ComponentMapper<TextureComponent> textureComponent = ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<InventoryComponent> inventoryComponent = ComponentMapper.getFor(InventoryComponent.class);

}
