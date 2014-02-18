package org.bukkit.event.entity;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a Creeper is struck by lightning.
 * <p>
 * If a Creeper Power event is cancelled, the Creeper will not be powered.
 */
public class CreeperPowerEvent extends EntityZapEvent {
    private static final HandlerList handlers = new HandlerList();
    private final PowerCause cause;

    public CreeperPowerEvent(final Creeper creeper, final LightningStrike bolt, final PowerCause cause) {
        super(creeper, bolt);
        this.cause = cause;
    }

    public CreeperPowerEvent(final Creeper creeper, final PowerCause cause) {
        super(creeper, null);
        this.cause = cause;
    }

    @Override
    public Creeper getEntity() {
        return (Creeper) entity;
    }

    /**
     * Gets the cause of the creeper being (un)powered.
     *
     * @return A PowerCause value detailing the cause of change in power.
     */
    public PowerCause getCause() {
        return cause;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * An enum to specify the cause of the change in power
     */
    public enum PowerCause {

        /**
         * Power change caused by a lightning bolt
         * <p>
         * Powered state: true
         */
        LIGHTNING,
        /**
         * Power change caused by something else (probably a plugin)
         * <p>
         * Powered state: true
         */
        SET_ON,
        /**
         * Power change caused by something else (probably a plugin)
         * <p>
         * Powered state: false
         */
        SET_OFF
    }
}
