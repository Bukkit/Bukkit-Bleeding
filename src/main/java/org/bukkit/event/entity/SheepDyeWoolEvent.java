package org.bukkit.event.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;

/**
 * Called when a sheep's wool is dyed
 *
 * @deprecated Use EntityDyeEvent instead
 */
@Deprecated
public class SheepDyeWoolEvent extends EntityDyeEvent {
    public SheepDyeWoolEvent(final Sheep sheep, final DyeColor color) {
        super(sheep, null, color);
    }

    @Override
    public Sheep getEntity() {
        return (Sheep) entity;
    }
}
