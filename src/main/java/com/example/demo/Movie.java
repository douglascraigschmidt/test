package com.example.demo;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Movie title and vector as stored and returned from the database
 * microservice.
 */
@SuppressWarnings("DataFlowIssue")

@NoArgsConstructor(force = true)
@Entity
@Table(name = "movies")
public class Movie
// Implement an interface that enables two Movie objects to be
// compared and checked for equality.
// TODO -- you fill in here
// SOLUTION-START
    implements Comparable<Movie> 
// SOLUTION-END
{
    /**
     * The movie name.
     */
    @Id
    @Column(name = "id", nullable = false)
    public String id;

    /**
     * The encoding of the movie properties.  {@link FetchType#EAGER}
     * ensures this code works with parallel stream.
     */

    @Column(name = "vector")
    @Convert(converter = DoubleListConverter.class)
    List<Double> vector;

    public Movie(String id, List<Double> vector) {
        this.id = id;
        this.vector = vector;
    }

    /**
     * Perform a case-insensitive comparison of this {@link Movie}
     * with the {@code other} {@link Movie} based on their IDs.
     *
     * @param other The {@link Movie} to compare to this {@link Movie}
     * @return A negative integer, zero, or a positive integer as this
     *         movie's ID is less than, equal to, or greater than the
     *         specified movie's ID (ignoring case)
     */
    // TODO -- you fill in here.
    // SOLUTION-START
        @Override
        public int compareTo(Movie other) {
            // Compare the ID of this movie with the ID of the other movie
            // and return the results.
            return this.id
                .compareToIgnoreCase(other.id);
        }
    // SOLUTION-END

    /**
     * Overrides the equals method to compare two {@link Movie}
     * objects based on their id only.
     *
     * @param other The other {@link Object} to compare with this object
     * @return true if the object ids are equal, false otherwise
     */
    // TODO -- you fill in here.
    // SOLUTION-START
    @Override
    public boolean equals(Object other) {
        return Optional
            // Create an Optional (to handle 'null' param values properly).
            .ofNullable(other)

            // Remove self-equality checks and mismatched classes.
            .filter(obj -> this != other && obj.getClass() == getClass())

            // Convert Object to Movie.
            .map(obj -> (Movie) obj)

            // Check for equality.
            .filter(movie -> Objects.equals(id, movie.id))

            // Return true if there was a match and all other
            // checks passed.
            .isPresent();
    }
    // SOLUTION-END
}
