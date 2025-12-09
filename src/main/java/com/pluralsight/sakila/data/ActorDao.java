package com.pluralsight.sakila.data;

import com.pluralsight.sakila.model.Actor;

import java.util.List;

public interface ActorDao {
    List<Actor> getAll();
    Actor add(Actor actor);
}
