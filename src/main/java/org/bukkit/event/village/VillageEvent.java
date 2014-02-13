package org.bukkit.event.village;

import org.bukkit.event.Event;
import org.bukkit.village.Village;

/**
 * Represents a village-related event.
 */
public abstract class VillageEvent extends Event {
    protected Village village;

    public VillageEvent(final Village village) {
        this.village = village;
    }

    /**
     * Get the village.
     *
     * @return the village
     */
    public final Village getVillage() {
        return village;
    }
}
