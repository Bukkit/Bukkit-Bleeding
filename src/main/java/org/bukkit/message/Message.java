package org.bukkit.message;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a chat message.
 */
public final class Message implements Cloneable {
    /**
     * Instantiates a new Message Builder.
     *
     * @return the new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a builder using the provided message as a base
     *
     * @param message message to use as a base
     * @return the new builder
     * @throws IllegalArgumentException if message is null
     */
    public static Builder builder(Message message) {
        Validate.notNull(message);
        return new Builder(message);
    }

    /**
     * Creates and formats a message from the provided string.
     *
     * @param string String to format
     * @return formatted message
     * @throws IllegalArgumentException if string is null
     */
    public static Message format(String string) {
        Validate.notNull(string);
        if (string.indexOf(ChatColor.COLOR_CHAR) == -1) {
            return of(string);
        }
        return new Builder(string).build();
    }

    /**
     * Creates a message with the provided text.
     *
     * @param string The text for the message
     * @return the new message
     * @throws IllegalArgumentException if string is null
     */
    public static Message of(String string) {
        Validate.notNull(string);
        Builder builder = builder();
        builder.setText(string);
        return builder.build();
    }

    /**
     * Creates a message for the provided itemstack.
     *
     * @param itemstack the itemstack to create a message for
     * @return the message
     * @throws IllegalArgumentException if itemstack is null
     */
    public static Message of(ItemStack itemstack) {
        Validate.notNull(itemstack);
        Builder builder = builder();
        // default = WHITE; record = AQUA; goldenapple:0 = AQUA else LIGHT_PURPLE; enchantedBook+enchants = YELLOW; enchanted = AQUA
        // The way for determining colors is horrendous... Lets just leave it white - feildmaster
        builder.setColor(ChatColor.WHITE);
        builder.setHoverAction(MessageHover.of(itemstack));
        // TODO: getDisplayName doesn't return the item name, need a way to get base item name!
        builder.append("[").append(format(itemstack.getItemMeta().getDisplayName())).append("]");
        return builder.build();
    }

    /**
     * The Message Builder.
     */
    public static final class Builder {
        final List<Message> children = new ArrayList<Message>();
        String message = "";
        ChatColor color;
        boolean bold;
        boolean italic;
        boolean underline;
        boolean obfuscate;
        MessageClick click;
        MessageHover hover;

        Builder() {}

        // This constructor attempts to format the provided string
        Builder(String string) {
            // TODO: Parse through string
        }

        Builder(Builder parent) {
            color = parent.color;
            bold = parent.bold;
            italic = parent.italic;
            underline = parent.underline;
            obfuscate = parent.obfuscate;
            click = parent.click == null ? null : parent.click;
            hover = parent.hover == null ? null : parent.hover;
            children.addAll(parent.children);
        }

        // The idea here is to take messages and make them "mutable"
        Builder(Message parent) {
            color = parent.color;
            bold = parent.bold;
            italic = parent.italic;
            underline = parent.underline;
            obfuscate = parent.obfuscate;
        }

        /**
         * Appends the provided itemstack to the message.
         *
         * @param item Item to append
         * @return this builder
         * @throws IllegalArgumentException if item is null
         */
        public Builder append(ItemStack item) {
            Validate.notNull(item);
            return append(of(item));
        }

        /**
         * Appends the provided messages to the message.
         *
         * @param messages The messages to append
         * @return this builder
         * @throws IllegalArgumentException if any message is null
         */
        public Builder append(Message... messages) {
            Validate.noNullElements(messages, "Cannot have null messages");
            for (Message child : messages) {
                children.add(child);
            }
            return this;
        }

        /**
         * Appends the provided string to the message.
         *
         * @param message The message to append
         * @return this builder
         * @throws IllegalArgumentException if the message is null
         */
        public Builder append(String message) {
            Validate.notNull(message);
            append(of(message));
            return this;
        }

        /**
         * Sets the base text for the message.
         *
         * @param message The text to set the message to
         * @return this builder
         * @throws IllegalArgumentException if the message is null
         */
        public Builder setText(String message) {
            Validate.notNull(message);
            this.message = message;
            return this;
        }

        /**
         * Set the color of the message.
         * <br />
         * May be null (no color)
         *
         * @param color The color this message should have
         * @return this builder
         * @throws IllegalArgumentException if the color is not a color
         */
        public Builder setColor(ChatColor color) {
            Validate.isTrue(color == null || color.isColor(), "[" + color + "] is not a valid color!");
            this.color = color;
            return this;
        }

        /**
         * Sets the format to the value provided.
         *
         * @param format The format to set on this message
         * @param value true to have that format present, false to remove it
         * @return this builder
         * @throws IllegalArgumentException if the format is null or not a valid format
         */
        public Builder setFormat(ChatColor format, boolean value) {
            Validate.isTrue(format != null && format.isFormat(), "[" + format + "] is not a valid format!");
            switch (format) {
                case BOLD:
                    return setBold(value);
                case ITALIC:
                    return setItalic(value);
                case UNDERLINE:
                    return setUnderline(value);
                case MAGIC:
                    return setObfuscate(value);
                default:
                    throw new AssertionError(format);
            }
        }

        /**
         * Makes the message bold.
         *
         * @param value true to set the message bold
         * @return this builder
         */
        public Builder setBold(boolean value) {
            bold = value;
            return this;
        }

        /**
         * Makes the message italicized.
         *
         * @param value true to set the message italicized
         * @return this builder
         */
        public Builder setItalic(boolean value) {
            italic = value;
            return this;
        }

        /**
         * Makes the message underlined.
         *
         * @param value true to set the message underlined
         * @return this builder
         */
        public Builder setUnderline(boolean value) {
            underline = value;
            return this;
        }

        /**
         * Makes the message obfuscated.
         *
         * @param value true to set the message obfuscated
         * @return this builder
         */
        public Builder setObfuscate(boolean value) {
            obfuscate = value;
            return this;
        }

        /**
         * Sets the action when hovering over the message.
         * <br />
         * May be null for no action.
         *
         * @param hover the hover action
         * @return this builder
         */
        public Builder setHoverAction(MessageHover hover) {
            this.hover = hover == null ? null : hover.clone();
            return this;
        }

        /**
         * Sets the action when clicking the message.
         * <br />
         * May be null for no action.
         *
         * @param click the click action
         * @return this builder
         */
        public Builder setClickAction(MessageClick click) {
            this.click = click == null ? null : click.clone();
            return this;
        }

        /**
         * Builds the message
         * @return the message
         */
        public Message build() {
            return new Message(this);
        }
    }

    private List<Message> children;

    private final String text;
    private final ChatColor color;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;
    private final boolean obfuscate;
    private final MessageClick click;
    private final MessageHover hover;

    Message(Builder builder) {
        text = builder.message;
        color = builder.color;
        bold = builder.bold;
        italic = builder.italic;
        underline = builder.underline;
        obfuscate = builder.obfuscate;
        click = builder.click.clone();
        hover = builder.hover.clone();
        children = ImmutableList.<Message>copyOf(builder.children);
    }

    /**
     * Gets the base text for this message.
     * <br />
     * May be empty, but not null
     *
     * @return the base message
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the color of the message.
     * <br />
     * May be null
     *
     * @return the color of this message or null
     */
    public ChatColor getColor() {
        return color;
    }

    /**
     * Returns if this message is bold.
     *
     * @return True if the message is bold
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * Returns if this message is italicized.
     *
     * @return True if the message is italicized
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Returns if this message is underlined.
     *
     * @return True if the message is underlined
     */
    public boolean isUnderlined() {
        return underline;
    }

    /**
     * Returns if this message is obfuscated.
     *
     * @return True if the message is obfuscated
     */
    public boolean isObfuscated() {
        return obfuscate;
    }

    /**
     * Returns the children of this message.
     * <br />
     * This list is immutable.
     *
     * @return A list of children
     */
    public List<Message> getChildren() {
        return children;
    }

    public MessageClick getClickAction() {
        return click;
    }

    public MessageHover getHoverAction() {
        return hover;
    }

    @Override
    public Message clone() {
        try {
            Message message = (Message) super.clone();
            return message;
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
}
