package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {
    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteListWithSuccess() {
        service.getNeighbours().get(0).setFavorite(true);
        List<Neighbour> favneighbours = service.getFavNeighbours();
        Neighbour user = service.getNeighbours().get(0);
        assertThat(favneighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(user));
    }

    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        service.getFavNeighbours().clear();
        service.getNeighbours().get(0).setFavorite(true);
        List<Neighbour> favneighbours = service.getFavNeighbours();
        Neighbour user0 = service.getNeighbours().get(0);
        assertThat(favneighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(user0));
        service.toggleNeighbourFavorite(user0);
        assertNotSame(favneighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(user0));
    }

    @Test
    public void addFavorite() {
        Neighbour neighbour0 = service.getNeighbours().get(0);
        neighbour0.setFavorite(false);
        service.toggleNeighbourFavorite(neighbour0);
        assertTrue(service.getFavNeighbours().contains(neighbour0));
    }

    @Test
    public void deleteFavorite() {
        Neighbour favorite = service.getNeighbours().get(0);
        favorite.setFavorite(true);
        service.toggleNeighbourFavorite(favorite);
        assertFalse(service.getFavNeighbours().contains(favorite));
    }

}
