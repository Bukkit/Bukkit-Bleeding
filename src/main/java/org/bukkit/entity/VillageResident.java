package org.bukkit.entity;

import org.bukkit.village.Village;

/**
 * Represents an entity that has a village associated with it.
 */
public interface VillageResident {

    /**
     * Get the current village of this entity
     *
     * @return the entity's current village
     */
    public Village getVillage();

    /**
     * Set the current village of this entity
     *
     * @param village the entity's new village
     */
    public void setVillage(Village village);
}
