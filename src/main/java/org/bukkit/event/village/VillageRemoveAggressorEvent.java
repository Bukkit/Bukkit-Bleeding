package org.bukkit.event.village;

import org.bukkit.event.HandlerList;
import org.bukkit.village.Village;
import org.bukkit.village.VillageAggressor;

public class VillageRemoveAggressorEvent extends VillageAggressorEvent {
    private static final HandlerList handlers = new HandlerList();

    public VillageRemoveAggressorEvent(final VillageAggressor aggressor) {
        super(aggressor.getVillage(), aggressor);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
