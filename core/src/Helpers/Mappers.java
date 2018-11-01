package Helpers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Components.TransformComponent;
import Components.TypeComponent;

public class Mappers {

    public static final ComponentMapper<BodyComponent> bodyComponent = ComponentMapper.getFor(BodyComponent.class);

    public static final ComponentMapper<CollisionComponent> collisionComponent = ComponentMapper.getFor(CollisionComponent.class);

    public static final ComponentMapper<PlayerComponent> playerComponent = ComponentMapper.getFor(PlayerComponent.class);

    public static final ComponentMapper<StateComponent> stateComponent = ComponentMapper.getFor(StateComponent.class);

    public static final ComponentMapper<TransformComponent> transformComponent = ComponentMapper.getFor(TransformComponent.class);

    public static final ComponentMapper<TypeComponent> typeComponent = ComponentMapper.getFor(TypeComponent.class);





}
