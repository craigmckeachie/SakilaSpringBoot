package com.pluralsight.sakila.ui;

import com.pluralsight.sakila.data.ActorDao;
import com.pluralsight.sakila.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserInterface implements CommandLineRunner {
    @Autowired
    private ActorDao actorDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running User Interface");
//          actorDao.add(new Actor(0, "Ryan", "Gosling", LocalDateTime.now()));


          List<Actor> actors = actorDao.getAll();
          for (Actor actor: actors){
              System.out.println(actor);
          }

    }
}
