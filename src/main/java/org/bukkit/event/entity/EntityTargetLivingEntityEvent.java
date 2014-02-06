package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 * Called when an Entity targets a {@link LivingEntity} and can only target
 * LivingEntities.
 */
public class EntityTargetLivingEntityEvent extends EntityTargetEvent{
    public EntityTargetLivingEntityEvent(final Entity entity, final LivingEntity target, final TargetReason reason) {
        super(entity, target, reason);
    }

    public LivingEntity getTarget() {
        return (LivingEntity) super.getTarget();
    }

    /**
     * This method exists for legacy reasons to provide backwards
     * compatibility. It will not exist at runtime and should not be used
     * under any circumstances.
     */
    @Deprecated
    public void _INVALID_setTarget(Entity target) {
        if ((target == null) || (target instanceof LivingEntity)) {
            super.setTarget(target);
        }
    }

    /**
     * Set the entity that you want the mob to target instead.
     * <p>
     * It is possible to be null, null will cause the entity to be
     * target-less.
     * <p>
     * This is different from cancelling the event. Cancelling the event will
     * cause the entity to keep an original target, while setting to be null
     * will cause the entity to be reset.
     *
     * @param target The LivingEntity to target
     */
    public void setTarget(LivingEntity target) {
        super.setTarget(target);
    }
}
