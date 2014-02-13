package org.bukkit.event.village;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.village.Village;

/**
 * Raised when a player's popularity in a village changes.
 */
public class VillagePopularityChangeEvent extends VillageEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private final LivingEntity villageMember;
    private final Player player;
    private final ChangeReason reason;
    private int popularityChange;

    public VillagePopularityChangeEvent(final Village village, final LivingEntity villageMember, final Player player, int popularityChange) {
        this(village, villageMember, player, ChangeReason.UNKNOWN, popularityChange);
    }

    public VillagePopularityChangeEvent(final Village village, final LivingEntity villageMember, final Player player, final ChangeReason reason, int popularityChange) {
        super(village);
        this.villageMember = villageMember;
        this.player = player;
        this.popularityChange = popularityChange;
        this.reason = reason;
    }

    public LivingEntity getVillageMember() {
        return villageMember;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPopularityChange() {
        return popularityChange;
    }

    public void setPopularityChange(int popularityChange) {
        this.popularityChange = popularityChange;
    }

    public ChangeReason getReason() {
        return reason;
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

    public enum ChangeReason {
        /**
         * When a player's popularity increases because they traded with a villager
         */
        TRADING,
        /**
         * When a player's popularity decreases because they attacked a villager
         */
        ATTACKING_VILLAGER,
        /**
         * When a player's popularity decreases because they killed a villager
         */
        KILLING_VILLAGER,
        /**
         * When a player's popularity decreases because they killed an iron golem
         */
        KILLING_GOLEM,
        /**
         * Any other change in a player's popularity
         */
        UNKNOWN
    }
}
