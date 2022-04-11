package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my favorite neighbours
     */
    List<Neighbour> getFavNeighbours();

    /**
     * Deletes a neighbour
     *
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     *
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * modify a neighbour in neighbourList whith the favorite status
     */
    void toggleNeighbourFavorite(Neighbour neighbour);
}
