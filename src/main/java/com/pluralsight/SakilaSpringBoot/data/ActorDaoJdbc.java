package com.pluralsight.SakilaSpringBoot.data;

import com.pluralsight.SakilaSpringBoot.model.Actor;

import java.util.List;

public class ActorDaoJdbc implements ActorDao {
    @Override
    public List<Actor> getAll() {
        return List.of();
    }

    @Override
    public Actor add(Actor actor) {
        return null;
    }
}
