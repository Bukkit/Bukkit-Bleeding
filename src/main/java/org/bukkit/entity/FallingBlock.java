package org.bukkit.entity;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * Represents a falling block
 */
public interface FallingBlock extends Entity {

    /**
     * Get the Material of the falling block
     *
     * @return Material of the block
     */
    Material getMaterial();

    /**
     * Get the ID of the falling block
     *
     * @return ID type of the block
     * @deprecated Magic value
     */
    @Deprecated
    int getBlockId();

    /**
     * Get the data for the falling block
     *
     * @return data of the block
     * @deprecated Magic value
     */
    @Deprecated
    byte getBlockData();

    /**
     * Get the data for the falling block
     *
     * @return data of the block
     */
    MaterialData getData();

    /**
     * Set the data for the falling block
     *
     * @param data MaterialData of the block
     */
    void setData(MaterialData data);

    /**
     * Get if the falling block will break into an item if it cannot be placed
     *
     * @return true if the block will break into an item when obstructed
     */
    boolean getDropItem();

    /**
     * Set if the falling block will break into an item if it cannot be placed
     *
     * @param drop true to break into an item when obstructed
     */
    void setDropItem(boolean drop);
}
