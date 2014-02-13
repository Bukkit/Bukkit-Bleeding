package org.bukkit.village;

import org.bukkit.Location;

import java.util.List;

/**
 * Represents a world's village manager
 */
public interface VillageManager {
    /**
     * Get a list of villages in this world
     * <p>
     * This list is a new copy of the villages - any changes will not affect the list
     *
     * @return List of villages in the world
     */
    public List<Village> getVillages();

    /**
     * Get the closest village within 16 blocks
     * <p>
     * Will return null if no village is found
     *
     * @param location Location to search from
     * @return Closest village to location, can be null
     */
    public Village getVillage(Location location);

    /**
     * Get the closest village within a specified range
     * <p>
     * Will return null if no village is found
     *
     * @param location Location to search from
     * @param range Range of blocks, between 1 and 64
     * @return Closest village to location, can be null
     */
    public Village getVillage(Location location, int range);

    /**
     * Get the current village under siege
     * <p>
     * Will return null if there is no siege
     *
     * @return The village currently under siege
     */
    public Village getVillageUnderSiege();
}
