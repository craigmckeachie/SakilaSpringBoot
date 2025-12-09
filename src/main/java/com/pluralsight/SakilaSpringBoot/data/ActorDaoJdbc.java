package com.pluralsight.SakilaSpringBoot.data;

import com.pluralsight.SakilaSpringBoot.model.Actor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
