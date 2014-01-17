package org.bukkit.command.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class SummonCommand extends VanillaCommand {

    public SummonCommand() {
        super("summon");
        this.description = "Summons an entity with the specified data.";
        this.usageMessage = "/summon <EntityName> [x] [y] [z] [dataTag]";
        this.setPermission("bukkit.command.summon");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        if ((args.length == 0)) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        double x;
        double y;
        double z;

        Location loc;
        if (sender instanceof BlockCommandSender) {
            loc = ((BlockCommandSender) sender).getBlock().getLocation();
        } else if (sender instanceof Player) {
            loc = ((Player) sender).getLocation();
        } else {
            sender.sendMessage("Unable to summon object");
            return true;
        }

        World world = loc.getWorld();

        if (args.length >= 4) {
            x = VanillaCommand.getRelativeDouble(loc.getX(), sender, args[1]);
            y = VanillaCommand.getRelativeDouble(loc.getY(), sender, args[2]);
            z = VanillaCommand.getRelativeDouble(loc.getZ(), sender, args[3]);

            if (x == MIN_COORD_MINUS_ONE || y == MIN_COORD_MINUS_ONE || z == MIN_COORD_MINUS_ONE) {
                sender.sendMessage("Please provide a valid location!");
                return true;
            }
        } else {
            x = loc.getX();
            y = loc.getY();
            z = loc.getZ();
        }

        if (!world.isChunkLoaded(((int) x) >> 4, ((int) z) >> 4)) {
            sender.sendMessage("Cannot summon the object out of the world");
            return true;
        }

        String data = null;
        
        if (args.length >= 5) {
            data = Joiner.on(' ').join(Arrays.asList(args).subList(4, args.length));
        }

        Entity entity = null;

        try {
            entity = Bukkit.getUnsafe().createEntity(args[0], world, x, y, z, data);
        } catch (Exception e) {
            sender.sendMessage(String.format("Data tag parsing failed: %s", e.getMessage()));
        }

        if (entity == null) {
            sender.sendMessage("Unable to summon object");
            return false;
        }

        Command.broadcastCommandMessage(sender, "Object successfully summoned");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 1) {
            return Bukkit.getUnsafe().tabCompleteEntityType(args[0], new ArrayList<String>());
        }
        return ImmutableList.of();
    }
}
