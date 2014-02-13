package org.bukkit.event.village;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Villager;
import org.bukkit.event.HandlerList;
import org.bukkit.village.Village;
import org.bukkit.village.VillageAggressor;

public class VillageAddAggressorEvent extends VillageAggressorEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Villager villager;
    private int ticks;

    public VillageAddAggressorEvent(final Villager villager, final VillageAggressor aggressor) {
        super(aggressor.getVillage(), aggressor);
        this.villager = villager;
        this.ticks = village.getMaxAggressionTicks();
    }

    public Villager getVillager() {
        return villager;
    }

    public int getAggressionTicks() {
        return ticks;
    }

    public void setAggressionTicks(int ticks) {
        Validate.isTrue(ticks >= 0, "AggressionTicks cannot be less than 0!");
        Validate.isTrue(ticks <= getVillage().getMaxAggressionTicks(), "AggressionTicks cannot be greater than " + getVillage().getMaxAggressionTicks() + "!");
        this.ticks = ticks;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
