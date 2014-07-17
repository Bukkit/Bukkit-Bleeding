package org.bukkit.message;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a hover action
 */
public final class MessageHover implements Cloneable {
    public static MessageHover of(String string) {
        Validate.notNull(string);
        return of(Message.of(string));
    }

    public static MessageHover of(Message message) {
        Validate.notNull(message);
        return of(Type.SHOW_TEXT, message.clone());
    }

    public static MessageHover of(Achievement achievement) {
        Validate.notNull(achievement);
        return of(Type.SHOW_ACHIEVEMENT, achievement);
    }

    public static MessageHover of(ItemStack item) {
        Validate.notNull(item);
        return of(Type.SHOW_ITEM ,item.clone());
    }

    private static MessageHover of(Type type, Object object) {
        return new MessageHover(type, object);
    }

    public enum Type {
        SHOW_TEXT,
        SHOW_ACHIEVEMENT,
        SHOW_ITEM,
        ;
    }

    private final Type type;
    private final Object object;

    private MessageHover(Type type, Object object) {
        this.type = type;
        this.object = object;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        if (type == Type.SHOW_ITEM) {
            // We need to return a clone, since items are mutable
            return ((ItemStack) object).clone();
        }
        return object;
    }

    @Override
    public MessageHover clone() {
        try {
            // No need to hard clone, internals are "safe"
            return (MessageHover) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
}
