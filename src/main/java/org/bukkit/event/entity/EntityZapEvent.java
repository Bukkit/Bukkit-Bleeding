package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Cancellable;

/**
 * Called when an entity struck by lightning undergoes a change.
 * <p>
 * Examples:
 * <ul>
 * <li>Creepers becoming powered
 * <li>Pigs transforming into Pig Zombies
 * </ul>
 * <p>
 */
public abstract class EntityZapEvent extends EntityEvent implements Cancellable {
    private final LightningStrike bolt;
    private boolean canceled;

    public EntityZapEvent(final Entity entity, final LightningStrike bolt) {
        super(entity);
        this.bolt = bolt;
    }

    /**
     * Gets the bolt which is striking the entity.
     *
     * @return lightning entity
     */
    public LightningStrike getLightning() {
        return bolt;
    }

    public boolean isCancelled() {
        return canceled;
    }

    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }
}
