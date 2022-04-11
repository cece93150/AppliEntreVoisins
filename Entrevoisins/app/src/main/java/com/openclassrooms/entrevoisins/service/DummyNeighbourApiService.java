package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * @return list of all neighbour
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * @return new List for favorite neighbour
     */
    @Override
    public List<Neighbour> getFavNeighbours() {
        List<Neighbour> favoriteNeighboursList = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favoriteNeighboursList.add(neighbour);
            }
        }
        return favoriteNeighboursList;
    }

    /**
     * Delete a neighbour
     *
     * @param neighbour
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * Create a neighbour
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * modify a neighbour profile
     *
     * @param neighbour
     */
    @Override
    public void toggleNeighbourFavorite(Neighbour neighbour) {
        for (Neighbour neighbour1 : getNeighbours()) {
            if (neighbour1.getId() == neighbour.getId()) {
                neighbour1.setFavoriteNeighbour(!neighbour1.isFavorite());
            }
        }
    }
}

