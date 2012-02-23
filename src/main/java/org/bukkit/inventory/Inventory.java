package org.bukkit.inventory;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;

/**
 * Interface to the various inventories
 */
public interface Inventory {

    /**
     * Returns the size of the inventory
     *
     * @return The inventory size
     */
    public int getSize();

    /**
     * Return the name of the inventory
     *
     * @return The inventory name
     */
    public String getName();

    /**
     * Get the ItemStack found in the slot at the given index
     *
     * @param index The index of the Slot's ItemStack to return
     * @return The ItemStack in the slot
     */
    public ItemStack getItem(int index);

    /**
     * Stores the ItemStack at the given index
     *
     * @param index The index where to put the ItemStack
     * @param item The ItemStack to set
     */
    public void setItem(int index, ItemStack item);

    /**
     * Stores the given ItemStacks in the inventory.
     * This will try to fill existing stacks and empty slots as good as it can.
     * It will return a HashMap of what it couldn't fit.
     *
     * @param items The ItemStacks to add
     * @return The items that didn't fit.
     */
    public HashMap<Integer, ItemStack> addItem(ItemStack... items);

    /**
     *
     * @param forceDurability
     * @param items
     * @return
     */
    public HashMap<Integer, ItemStack> addItem(boolean forceDurability, ItemStack... items);

    /**
     *
     * @param forceDurability
     * @param oversizedStacks
     * @param items
     * @return
     */
    public HashMap<Integer, ItemStack> addItem(boolean forceDurability, int oversizedStacks, ItemStack... items);

    /**
     *
     * @param forceDurability
     * @param items
     * @return
     */
    public boolean addAllItems(boolean forceDurability, ItemStack... items);

    /**
     * Removes the given ItemStacks from the inventory.
     * <p />
     * It will try to remove 'as much as possible' from the types and amounts you
     * give as arguments. It will return a HashMap of what it couldn't remove.
     *
     * @param items The ItemStacks to remove
     * @return The items that couldn't be removed.
     */
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items);

    /**
     * Removes the given ItemStacks from the inventory. <p /> It will try to
     * remove 'as much as possible' from the types and amounts you give as
     * arguments. It will return a HashMap of what it couldn't remove.
     *
     * @param forceDurability
     * @param forceEnchantments
     * @param items The ItemStacks to remove
     * @return The items that couldn't be removed.
     */
    public HashMap<Integer, ItemStack> removeItem(boolean forceDurability, boolean forceEnchantments, ItemStack... items);

    /**
     * Get all ItemStacks from the inventory
     *
     * @return All the ItemStacks from all slots
     */
    public ItemStack[] getContents();

    /**
     * Set the inventory's contents
     *
     * @param items A complete replacement for the contents; the length must be equal to {@link #getSize()}.
     */
    public void setContents(ItemStack[] items);

    /**
     * Check if the inventory contains any ItemStacks with the given materialId
     *
     * @param materialId The materialId to check for
     * @return If any ItemStacks were found
     */
    public boolean contains(int materialId);

    /**
     * Check if the inventory contains any ItemStacks with the given material
     *
     * @param material The material to check for
     * @return If any ItemStacks were found
     */
    public boolean contains(Material material);

    /**
     * Check if the inventory contains any ItemStacks matching the given ItemStack
     * This will only match if both the type and the amount of the stack match
     *
     * @param item The ItemStack to match against
     * @return If any matching ItemStacks were found
     */
    public boolean contains(ItemStack item);

    /**
     * Check if the inventory contains any ItemStacks with the given materialId and at least the minimum amount specified
     *
     * @param materialId The materialId to check for
     * @param amount The minimum amount to look for
     * @return If any ItemStacks were found
     */
    public boolean contains(int materialId, int amount);

    /**
     * Check if the inventory contains any ItemStacks with the given material and at least the minimum amount specified
     *
     * @param material The material to check for
     * @param amount The minimum amount
     * @return If any ItemStacks were found
     */
    public boolean contains(Material material, int amount);

    /**
     * Check if the inventory contains any ItemStacks matching the given ItemStack and at least the minimum amount specified
     * This will only match if both the type and the amount of the stack match
     *
     * @param item The ItemStack to match against
     * @param amount The minimum amount
     * @return If any matching ItemStacks were found
     */
    public boolean contains(ItemStack item, int amount);

    /**
     *
     * @param forceDurability
     * @param forceEnchantments
     * @param items
     * @return
     */
    public boolean containsItem(boolean forceDurability, boolean forceEnchantments, ItemStack... items);

/**
     * Find all slots in the inventory containing any ItemStacks with the given materialId
     *
     * @param materialId The materialId to look for
     * @return The Slots found.
     */
    public HashMap<Integer, ? extends ItemStack> all(int materialId);

    /**
     * Find all slots in the inventory containing any ItemStacks with the given material
     *
     * @param material The material to look for
     * @return The Slots found.
     */
    public HashMap<Integer, ? extends ItemStack> all(Material material);

    /**
     * Find all slots in the inventory containing any ItemStacks with the given ItemStack
     * This will only match slots if both the type and the amount of the stack match
     *
     * @param item The ItemStack to match against
     * @return The Slots found.
     */
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item);

    /**
     * Find the first slot in the inventory containing an ItemStack with the given materialId
     *
     * @param materialId The materialId to look for
     * @return The Slot found.
     */
    public int first(int materialId);

    /**
     * Find the first slot in the inventory containing an ItemStack with the given material
     *
     * @param material The material to look for
     * @return The Slot found.
     */
    public int first(Material material);

    /**
     * Find the first slot in the inventory containing an ItemStack with the given stack
     * This will only match a slot if both the type and the amount of the stack match
     *
     * @param item The ItemStack to match against
     * @return The Slot found.
     */
    public int first(ItemStack item);

    /**
     *
     * @param inventory
     * @param item The ItemStack to match against
     * @param forceDurability
     * @param forceAmount
     * @param forceEnchantments
     * @return The Slot found.
     */
    public int first(Inventory inventory, ItemStack item, boolean forceDurability, boolean forceAmount, boolean forceEnchantments);

    /**
     * Find the first empty Slot.
     *
     * @return The first empty Slot found.
     */
    public int firstEmpty();

    /**
     *
     * @param item
     * @param forceDurability
     * @return
     */
    public int firstPartial(ItemStack item, boolean forceDurability);

    /**
     *
     * @param item
     * @param forceDurability
     * @param maxAmount
     * @return
     */
    public int firstPartial(ItemStack item, boolean forceDurability, int maxAmount);

    /**
     * Remove all stacks in the inventory matching the given materialId.
     *
     * @param materialId The material to remove
     */
    public void remove(int materialId);

    /**
     * Remove all stacks in the inventory matching the given material.
     *
     * @param material The material to remove
     */
    public void remove(Material material);

    /**
     * Remove all stacks in the inventory matching the given stack.
     * This will only match a slot if both the type and the amount of the stack match
     *
     * @param item The ItemStack to match against
     */
    public void remove(ItemStack item);

    /**
     * Clear out a particular slot in the index
     *
     * @param index The index to empty.
     */
    public void clear(int index);

    /**
     * Clear out the whole index
     */
    public void clear();

    /**
     *
     * @param location where to drop the itemstack
     * @param item the itemstack to drop
     * @return an array of item entities which were dropped
     */
    public Item[] drop(Location location, ItemStack item);

    /**
     *
     * @param item
     * @param start
     * @param forceDurability
     * @param forceAmount
     * @param forceEnchantments
     * @return
     */
    public int next(ItemStack item, int start, boolean forceDurability, boolean forceAmount, boolean forceEnchantments);
}
