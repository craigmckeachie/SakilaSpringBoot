package com.pluralsight.sakila.data;

import com.pluralsight.sakila.model.Actor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActorDaoJdbc implements ActorDao {

    private final DataSource dataSource;

    // Dependency Injection via Constructor
    public ActorDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Helper method to map a row from the ResultSet to an Actor object.
     */
    private Actor mapRow(ResultSet resultSet) throws SQLException {
        int actorId = resultSet.getInt("actor_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        // Retrieve the SQL Timestamp and convert it to Java LocalDateTime
        Timestamp timestamp = resultSet.getTimestamp("last_update");
        LocalDateTime lastUpdate = timestamp.toLocalDateTime();

        return new Actor(actorId, firstName, lastName, lastUpdate);
    }


    @Override
    public List<Actor> getAll() {
        List<Actor> actors = new ArrayList<>();

        String query = """
                SELECT actor_id, first_name, last_name, last_update
                FROM actor
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Actor actor = mapRow(resultSet);
                    actors.add(actor);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving actor data.");
            e.printStackTrace();
        }

        return actors;
    }


    @Override
    public Actor add(Actor actor) {

        // SQL query: Only send first_name and last_name, let DB handle actor_id and last_update
        String insertQuery = """
                INSERT INTO actor (first_name, last_name)
                VALUES (?, ?)
                """;

        // Specify the columns whose generated values we want the driver to return
        String[] generatedColumns = {"actor_id", "last_update"};

        try (Connection connection = dataSource.getConnection();
             // Instruct the PreparedStatement to return specific generated columns
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery, generatedColumns)) {

            // Set the input parameters
            insertStatement.setString(1, actor.getFirstName());
            insertStatement.setString(2, actor.getLastName());

            insertStatement.executeUpdate();

            // Retrieve both generated columns from the keys result set
            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // 1. Get the auto-generated actor_id
                    int generatedId = generatedKeys.getInt("actor_id");
                    actor.setActorId(generatedId);

                    // 2. Get the DB-generated last_update timestamp
                    Timestamp timestamp = generatedKeys.getTimestamp("last_update");
                    actor.setLastUpdate(timestamp.toLocalDateTime());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error adding actor and retrieving generated columns.");
            e.printStackTrace();
            return null;
        }

        // Return the original actor object, now fully updated with DB-generated values
        return actor;
    }
}