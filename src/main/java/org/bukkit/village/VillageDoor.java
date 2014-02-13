package org.bukkit.village;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * Represents a door in a village
 */
public interface VillageDoor {

    public Location getLocation();

    public Location getLocation(Location location);

    public Village getVillage();

    public World getWorld();

    public int getAge();

    public void setAge(int ticks);
}
