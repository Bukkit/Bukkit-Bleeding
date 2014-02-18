package org.bukkit.event.entity;

import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Stores data for pigs being zapped
 */
public class PigZapEvent extends EntityZapEvent {
    private static final HandlerList handlers = new HandlerList();
    private final PigZombie pigzombie;

    public PigZapEvent(final Pig pig, final LightningStrike bolt, final PigZombie pigzombie) {
        super(pig, bolt);
        this.pigzombie = pigzombie;
    }

    @Override
    public Pig getEntity() {
        return (Pig) entity;
    }

    /**
     * Gets the zombie pig that will replace the pig, provided the event is
     * not cancelled first.
     *
     * @return resulting entity
     */
    public PigZombie getPigZombie() {
        return pigzombie;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
