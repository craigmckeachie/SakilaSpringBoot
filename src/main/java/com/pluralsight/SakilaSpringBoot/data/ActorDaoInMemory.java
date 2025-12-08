package com.pluralsight.SakilaSpringBoot.data;

import com.pluralsight.SakilaSpringBoot.model.Actor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// This is a "Component" — a Spring-managed bean.
// The @Component annotation tells Spring:
// "Please create an object of this class and manage it for me."
@Component
public class ActorDaoInMemory implements ActorDao {
    private List<Actor> actors;


    // This is used to assign a unique ID to each new Actor.
    // Every time we add a Actor, we will increase this number by 1.
    private int nextId = 1;


    public ActorDaoInMemory() {
        this.actors = new ArrayList<>();
    }

    @Override
    public List<Actor> getAll() {
        return actors;
    }

    @Override
    public Actor add(Actor actor) {
        actor.setActorId(nextId++);
        actors.add(actor);
        return actor;
    }
}
