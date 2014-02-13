package org.bukkit.event.village;

import org.bukkit.event.HandlerList;
import org.bukkit.village.Village;
import org.bukkit.village.VillageDoor;

public class VillageAddDoorEvent extends VillageDoorEvent {
    private static final HandlerList handlers = new HandlerList();

    public VillageAddDoorEvent(final Village village, final VillageDoor door) {
        super(village, door);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
