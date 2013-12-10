package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Thrown when a block physics check is called
 */
public class BlockPhysicsEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    /**
    *
    * @deprecated Magic value
    */
    @Deprecated
    private final int changed;
    private final Material changedMaterial;
    private boolean cancel = false;

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public BlockPhysicsEvent(final Block block, final int changed) {
        this(block, Material.getMaterial(changed));
    }

    public BlockPhysicsEvent(final Block block, final Material changed) {
        super(block);
        this.changed = changed.getId();
        this.changedMaterial = changed;
    }

    /**
     * Gets the type of block that changed, causing this event
     *
     * @return Changed block's type id
     * @deprecated Magic value
     */
    @Deprecated
    public int getChangedTypeId() {
        return changed;
    }

    /**
     * Gets the type of block that changed, causing this event
     *
     * @return Changed block's type
     */
    public Material getChangedType() {
        return changedMaterial;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
