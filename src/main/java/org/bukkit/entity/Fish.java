package org.bukkit.entity;

/**
 * Represents a fishing hook.
 */
public interface Fish extends Projectile {

    /**
     * Gets the chance of a fish biting.
     * <p>
     * 0.0 = No Chance.<br>
     * 1.0 = Instant catch.
     *
     * @return the bite chance
     * @deprecated No longer functions due to logic change in 1.7
     */
    @Deprecated
    public double getBiteChance();

    /**
     * Sets the chance of a fish biting.
     * <p>
     * 0.0 = No Chance.<br>
     * 1.0 = Instant catch.
     *
     * @param chance the bite chance
     * @throws IllegalArgumentException if the bite chance is not between 0
     *     and 1
     * @deprecated No longer functions due to logic change in 1.7
     */
    @Deprecated
    public void setBiteChance(double chance) throws IllegalArgumentException;

    /**
     * Get the number of ticks remaining before a fish will approach the hook.
     *
     * @return the number of ticks remaining before a fish approaches the hook.
     */
    public int getBiteTicks();

    /**
     * Set the number of ticks remaining before a fish approaches the hook.
     *
     * @param ticks the bite ticks
     */
    public void setBiteTicks(int ticks);
}
