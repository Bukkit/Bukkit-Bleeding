package org.bukkit.village;

import org.bukkit.entity.LivingEntity;

public interface VillageAggressor {
    public LivingEntity getEntity();

    public Village getVillage();

    public int getAggressionTicks();

    public void setAggressionTicks(int ticks);
}
