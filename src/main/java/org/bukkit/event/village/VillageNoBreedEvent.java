package org.bukkit.event.village;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.village.Village;

/**
 * Raised when a village stops breeding.
 */
public class VillageNoBreedEvent extends VillageEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Villager villager;
    private final LivingEntity entity;
    private boolean cancelled = false;

    public VillageNoBreedEvent(final Village village, final Villager villager, final LivingEntity entity) {
        super(village);
        this.villager = villager;
        this.entity = entity;
    }

    public Villager getVillager() {
        return villager;
    }

    public LivingEntity getEntity() {
        return entity;
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
