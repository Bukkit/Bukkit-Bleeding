package org.bukkit.village;

import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.List;

public interface Village {
    public Location getLocation();

    public Location getLocation(Location location);

    public int getVillagerPopulation();

    public int getIronGolemPopulation();

    public int getRadius();

    public int getMaxAggressionTicks();

    public void setMaxAggressionTicks(int ticks);

    public boolean isAggressor(LivingEntity entity);

    public void addAggressor(LivingEntity entity);

    public void removeAggressor(LivingEntity entity);

    public List<LivingEntity> getAggressors();

    public int getAggressorCount();

    public int getPopularity(Player player);

    public void setPopularity(Player player, int popularity);

    public int getBreedWaitTime();

    public void setBreedWaitTime(int waitTime);

    public int getNoBreedTicks();

    public void setNoBreedTicks(int noBreedTicks);

    public boolean canBreed();

    public boolean isAbandoned();

    public void abandon();

    public int getDoorIdleTime();

    public void setDoorIdleTime(int idleTime);

    public List<VillageDoor> getDoors();

    public int getDoorCount();

    public List<Villager> getVillagers();

    public List<Villager> getVillagersByProfession(Villager.Profession profession);

    public Villager spawnVillager();

    public Villager spawnVillager(Villager.Profession profession);

    public Villager spawnVillager(Villager.Profession profession, boolean isBaby);

    public List<IronGolem> getIronGolems();

    public IronGolem spawnIronGolem();

    public boolean isUnderSiege();
}
