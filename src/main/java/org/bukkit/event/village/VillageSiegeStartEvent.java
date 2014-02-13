package org.bukkit.event.village;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.village.Village;

public class VillageSiegeStartEvent extends VillageEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    public VillageSiegeStartEvent(final Village village) {
        super(village);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
