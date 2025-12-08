package com.pluralsight.SakilaSpringBoot.data;

import com.pluralsight.SakilaSpringBoot.model.Actor;

import java.util.List;

public interface ActorDao {
    List<Actor> getAll();
    Actor add(Actor actor);
}
