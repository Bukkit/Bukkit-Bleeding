package org.bukkit.event.village;

import org.bukkit.event.Cancellable;
import org.bukkit.village.Village;
import org.bukkit.village.VillageAggressor;

public abstract class VillageAggressorEvent extends VillageEvent implements Cancellable {
    private final VillageAggressor aggressor;
    private boolean cancelled;

    public VillageAggressorEvent(final Village village, final VillageAggressor aggressor) {
        super(village);
        this.aggressor = aggressor;
    }

    public VillageAggressor getAggressor() {
        return aggressor;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
