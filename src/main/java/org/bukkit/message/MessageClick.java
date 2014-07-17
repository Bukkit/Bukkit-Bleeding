package org.bukkit.message;

import java.util.regex.Pattern;
import org.apache.commons.lang.Validate;

/**
 * Represents a click action
 */
public final class MessageClick implements Cloneable {
    private static final Pattern HTTP_REGEX = Pattern.compile("^https?://.*", Pattern.CASE_INSENSITIVE);

    public static MessageClick ofOpenURL(String url) {
        Validate.isTrue(HTTP_REGEX.matcher(url).matches(), "Valid url is required");
        return forType(Type.OPEN_URL, url);
    }

    public static MessageClick ofSendText(String text) {
        return forType(Type.SEND_TEXT, text);
    }

    public static MessageClick ofSetText(String text) {
        return forType(Type.SET_TEXT, text);
    }

    private static MessageClick forType(Type type, String action) {
        Validate.notEmpty(action);
        return new MessageClick(type, action);
    }

    /**
     * An enum for the various ways text can be clicked
     */
    public enum Type {
        /**
         * Opens specified URL
         */
        OPEN_URL,
        /**
         * Sends provided text to the server (as if the player sent it)
         */
        SEND_TEXT,
        /**
         * Sets the players text box with the provided text
         */
        SET_TEXT,
        ;
    }

    private final Type type;
    private final String action;

    private MessageClick(Type type, String action) {
        this.type = type;
        this.action = action;
    }

    public Type getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    @Override
    public MessageClick clone(){
        try {
            return (MessageClick) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new Error(ex);
        }
    }
}
