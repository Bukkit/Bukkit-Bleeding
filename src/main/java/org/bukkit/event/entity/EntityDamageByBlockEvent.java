package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

/**
 * Called when an entity is damaged by a block
 */
public class EntityDamageByBlockEvent extends EntityDamageEvent {
    private final Block damager;

    @Deprecated
    public EntityDamageByBlockEvent(final Block damager, final Entity damagee, final DamageCause cause, final int damage) {
        this(damager, damagee, cause, (double) damage);
    }

    public EntityDamageByBlockEvent(final Block damager, final Entity damagee, final DamageCause cause, final double damage) {
        this(damager, damagee, cause, damage, 0D, 0D, 0D, 0D);
    }

    public EntityDamageByBlockEvent(final Block damager, final Entity damagee, final DamageCause cause, final double rawDamage, final double blockingReduction, final double armorReduction, final double magicReduction, final double absorptionReduction) {
        super(damagee, cause, rawDamage, blockingReduction, armorReduction, magicReduction, absorptionReduction);
        this.damager = damager;
    }

    /**
     * Returns the block that damaged the player.
     *
     * @return Block that damaged the player
     */
    public Block getDamager() {
        return damager;
    }
}
