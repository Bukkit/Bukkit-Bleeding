package org.bukkit.entity;

/**
 * Represents a villager NPC
 */
public interface Villager extends Ageable, NPC, VillageResident {

    /**
     * Gets the current profession of this villager.
     *
     * @return Current profession.
     */
    public Profession getProfession();

    /**
     * Sets the new profession of this villager.
     *
     * @param profession New profession.
     */
    public void setProfession(Profession profession);

    /**
     * Represents the various different Villager professions there may be.
     */
    public enum Profession {
        FARMER,
        LIBRARIAN,
        PRIEST,
        BLACKSMITH,
        BUTCHER;
    }
}
