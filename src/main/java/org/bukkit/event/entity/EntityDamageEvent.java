package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.util.NumberConversions;

/**
 * Stores data for damage events
 */
public class EntityDamageEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private double damage;
    private boolean cancelled;
    private final DamageCause cause;
    private double blockingReduction;
    private double armorReduction;
    private double magicReduction;
    private double absorptionReduction;

    @Deprecated
    public EntityDamageEvent(final Entity damagee, final DamageCause cause, final int damage) {
        this(damagee, cause, (double) damage);
    }

    public EntityDamageEvent(final Entity damagee, final DamageCause cause, final double damage) {
        this(damagee, cause, damage, 0D, 0D, 0D, 0D);
    }

    public EntityDamageEvent(final Entity damagee, final DamageCause cause, final double rawDamage, final double blockingReduction, final double armorReduction, final double magicReduction, final double absorptionReduction) {
        super(damagee);
        this.cause = cause;
        this.damage = rawDamage;
        this.blockingReduction = blockingReduction;
        this.armorReduction = armorReduction;
        this.magicReduction = magicReduction;
        this.absorptionReduction = absorptionReduction;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Gets the raw amount of damage caused by the event
     *
     * @return The raw amount of damage caused by the event
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Gets the amount of damage caused by the event after all damage
     * reduction is applied.
     *
     * @return the amount of damage caused by the event
     */
    public double getFinalDamage() {
        if (!(getEntity() instanceof LivingEntity)) {
            return damage;
        }
        return damage - blockingReduction - armorReduction - magicReduction - absorptionReduction;
    }

    /**
     * This method exists for legacy reasons to provide backwards
     * compatibility. It will not exist at runtime and should not be used
     * under any circumstances.
     */
    @Deprecated
    public int _INVALID_getDamage() {
        return NumberConversions.ceil(getDamage());
    }

    /**
     * Sets the raw amount of damage caused by the event
     *
     * @param damage The raw amount of damage caused by the event
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * This method exists for legacy reasons to provide backwards
     * compatibility. It will not exist at runtime and should not be used
     * under any circumstances.
     */
    @Deprecated
    public void _INVALID_setDamage(int damage) {
        setDamage(damage);
    }

    /**
     * Gets the cause of the damage.
     *
     * @return A DamageCause value detailing the cause of the damage.
     */
    public DamageCause getCause() {
        return cause;
    }

    /**
     * Gets the damage reduction caused by blocking, typically only present
     * for Players.
     *
     * @return the damage reduction from blocking
     */
    public double getBlockingReduction() {
        return blockingReduction;
    }

    /**
     * Sets the damage reduction caused by blocking.
     *
     * @param blockingReduction the damage reduction from blocking
     */
    public void setBlockingReduction(double blockingReduction) {
        this.blockingReduction = blockingReduction;
    }

    /**
     * Gets the damage reduction caused by wearing armor.
     *
     * @return the damage reduction from wearing armor
     */
    public double getArmorReduction() {
        return armorReduction;
    }

    /**
     * Sets the damage reduction caused by wearing armor.
     *
     * @param armorReduction the damage reduction from wearing armor
     */
    public void setArmorReduction(double armorReduction) {
        this.armorReduction = armorReduction;
    }

    /**
     * Gets the damage reduction caused by:
     * <ul>
     * <li>
     * Armor enchantments.
     * </li>
     * <li>
     * Resistance potion effect.
     * </li>
     * <li>
     * Witch's magic resistance.
     * </li>
     *
     * @return the damage reduction from magic sources
     */
    public double getMagicReduction() {
        return magicReduction;
    }

    /**
     * Sets the damage reduction caused by magic sources.
     *
     * @param magicReduction the damage reduction from magic sources
     */
    public void setMagicReduction(double magicReduction) {
        this.magicReduction = magicReduction;
    }

    /**
     * Gets the damage reduction caused by the absorption potion effect.
     *
     * @return the damage reduction from absorption potion effect
     */
    public double getAbsorptionReduction() {
        return absorptionReduction;
    }

    /**
     * Sets the damage reduction caused by the absorption potion effect.
     *
     * @param absorptionReduction the damage reduction from absorption potion effect
     */
    public void setAbsorptionReduction(double absorptionReduction) {
        this.absorptionReduction = absorptionReduction;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * An enum to specify the cause of the damage
     */
    public enum DamageCause {

        /**
         * Damage caused when an entity contacts a block such as a Cactus.
         * <p>
         * Damage: 1 (Cactus)
         */
        CONTACT,
        /**
         * Damage caused when an entity attacks another entity.
         * <p>
         * Damage: variable
         */
        ENTITY_ATTACK,
        /**
         * Damage caused when attacked by a projectile.
         * <p>
         * Damage: variable
         */
        PROJECTILE,
        /**
         * Damage caused by being put in a block
         * <p>
         * Damage: 1
         */
        SUFFOCATION,
        /**
         * Damage caused when an entity falls a distance greater than 3 blocks
         * <p>
         * Damage: fall height - 3.0
         */
        FALL,
        /**
         * Damage caused by direct exposure to fire
         * <p>
         * Damage: 1
         */
        FIRE,
        /**
         * Damage caused due to burns caused by fire
         * <p>
         * Damage: 1
         */
        FIRE_TICK,
        /**
         * Damage caused due to a snowman melting
         * <p>
         * Damage: 1
         */
        MELTING,
        /**
         * Damage caused by direct exposure to lava
         * <p>
         * Damage: 4
         */
        LAVA,
        /**
         * Damage caused by running out of air while in water
         * <p>
         * Damage: 2
         */
        DROWNING,
        /**
         * Damage caused by being in the area when a block explodes.
         * <p>
         * Damage: variable
         */
        BLOCK_EXPLOSION,
        /**
         * Damage caused by being in the area when an entity, such as a
         * Creeper, explodes.
         * <p>
         * Damage: variable
         */
        ENTITY_EXPLOSION,
        /**
         * Damage caused by falling into the void
         * <p>
         * Damage: 4 for players
         */
        VOID,
        /**
         * Damage caused by being struck by lightning
         * <p>
         * Damage: 5
         */
        LIGHTNING,
        /**
         * Damage caused by committing suicide using the command "/kill"
         * <p>
         * Damage: 1000
         */
        SUICIDE,
        /**
         * Damage caused by starving due to having an empty hunger bar
         * <p>
         * Damage: 1
         */
        STARVATION,
        /**
         * Damage caused due to an ongoing poison effect
         * <p>
         * Damage: 1
         */
        POISON,
        /**
         * Damage caused by being hit by a damage potion or spell
         * <p>
         * Damage: variable
         */
        MAGIC,
        /**
         * Damage caused by Wither potion effect
         */
        WITHER,
        /**
         * Damage caused by being hit by a falling block which deals damage
         * <p>
         * <b>Note:</b> Not every block deals damage
         * <p>
         * Damage: variable
         */
        FALLING_BLOCK,
        /**
         * Damage caused in retaliation to another attack by the Thorns
         * enchantment.
         * <p>
         * Damage: 1-4 (Thorns)
         */
        THORNS,
        /**
         * Custom damage.
         * <p>
         * Damage: variable
         */
        CUSTOM
    }
}
