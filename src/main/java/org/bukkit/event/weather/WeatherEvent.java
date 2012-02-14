package org.bukkit.event.weather;

import org.bukkit.World;
import org.bukkit.event.Event;

/**
 * Represents a Weather-related event
 */
@SuppressWarnings("serial")
public abstract class WeatherEvent extends Event {
    private final World world;

    public WeatherEvent(final World world) {
        super();
        this.world = world;
    }

    /**
     * Returns the World where this event is occurring
     *
     * @return World this event is occurring in
     */
    public final World getWorld() {
        return world;
    }
}
