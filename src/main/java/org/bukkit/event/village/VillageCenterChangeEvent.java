package org.bukkit.event.village;

import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.village.Village;

public class VillageCenterChangeEvent extends VillageEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Location oldLocation;
    private final Location newLocation;
    private final int oldSize;
    private final int newSize;

    public VillageCenterChangeEvent(final Village village, final Location oldLocation, final Location newLocation, final int oldSize, final int newSize) {
        super(village);
        this.oldLocation = oldLocation;
        this.newLocation = newLocation;
        this.oldSize = oldSize;
        this.newSize = newSize;
    }

    public Location getOldLocation() {
        return oldLocation;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    public int getOldSize() {
        return oldSize;
    }

    public int getNewSize() {
        return newSize;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
