package org.bukkit.event.village;

import org.bukkit.event.Cancellable;
import org.bukkit.village.Village;
import org.bukkit.village.VillageDoor;

public abstract class VillageDoorEvent extends VillageEvent implements Cancellable {
    private final VillageDoor door;
    private boolean cancelled;

    public VillageDoorEvent(final Village village, final VillageDoor door) {
        super(village);
        this.door = door;
    }

    public VillageDoor getDoor() {
        return this.door;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
