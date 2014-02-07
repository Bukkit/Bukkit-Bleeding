package org.bukkit.command;

import static org.bukkit.util.Java15Compat.Arrays_copyOfRange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;
import org.bukkit.Server;
import org.bukkit.command.defaults.*;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class SimpleCommandMap implements CommandMap {
    private static final Pattern PATTERN_ON_SPACE = Pattern.compile(" ", Pattern.LITERAL);
    // Commands in this map have first priority and are specified by the aliases.yml
    protected final Map<String, Command> serverAliases = new HashMap<String, Command>();
    // Commands in this map have second priority and should contain only commands that we don't want overridden by plugins
    protected final Map<String, Command> priorityCommands = new HashMap<String, Command>();
    // Commands in this map have third priority and are uniquely addressed
    protected final Map<String, Command> addressedCommands = new HashMap<String, Command>();
    // Commands in this map have fourth priority and are stored by regular command names/plugin aliases
    protected final Map<String, Command> knownCommands = new HashMap<String, Command>();
    // Commands in this map have no priority and are fallen back to if no other command is found
    protected final Map<String, VanillaCommand> vanillaCommands = new HashMap<String, VanillaCommand>();
    private final Server server;


    public SimpleCommandMap(final Server server) {
        this.server = server;
        setDefaultCommands();
    }

    protected void setDefaultCommands() {
        setDefaultBukkitCommand(new ListCommand());
        setDefaultBukkitCommand(new OpCommand());
        setDefaultBukkitCommand(new DeopCommand());
        setDefaultBukkitCommand(new BanIpCommand());
        setDefaultBukkitCommand(new PardonIpCommand());
        setDefaultBukkitCommand(new BanCommand());
        setDefaultBukkitCommand(new PardonCommand());
        setDefaultBukkitCommand(new KickCommand());
        setDefaultBukkitCommand(new TeleportCommand());
        setDefaultBukkitCommand(new GiveCommand());
        setDefaultBukkitCommand(new TimeCommand());
        setDefaultBukkitCommand(new SayCommand());
        setDefaultBukkitCommand(new WhitelistCommand());
        setDefaultBukkitCommand(new TellCommand());
        setDefaultBukkitCommand(new MeCommand());
        setDefaultBukkitCommand(new KillCommand());
        setDefaultBukkitCommand(new GameModeCommand());
        setDefaultBukkitCommand(new HelpCommand());
        setDefaultBukkitCommand(new ExpCommand());
        setDefaultBukkitCommand(new ToggleDownfallCommand());
        setDefaultBukkitCommand(new BanListCommand());
        setDefaultBukkitCommand(new DefaultGameModeCommand());
        setDefaultBukkitCommand(new SeedCommand());
        setDefaultBukkitCommand(new DifficultyCommand());
        setDefaultBukkitCommand(new WeatherCommand());
        setDefaultBukkitCommand(new SpawnpointCommand());
        setDefaultBukkitCommand(new ClearCommand());
        setDefaultBukkitCommand(new GameRuleCommand());
        setDefaultBukkitCommand(new EnchantCommand());
        setDefaultBukkitCommand(new TestForCommand());
        setDefaultBukkitCommand(new EffectCommand());
        setDefaultBukkitCommand(new ScoreboardCommand());
        setDefaultBukkitCommand(new PlaySoundCommand());
        setDefaultBukkitCommand(new SpreadPlayersCommand());
        setDefaultBukkitCommand(new SetWorldSpawnCommand());
        setDefaultBukkitCommand(new SetIdleTimeoutCommand());
        setDefaultBukkitCommand(new AchievementCommand());
        setBukkitPriorityCommand(new SaveCommand());
        setBukkitPriorityCommand(new SaveOnCommand());
        setBukkitPriorityCommand(new SaveOffCommand());
        setBukkitPriorityCommand(new StopCommand());
        setBukkitPriorityCommand(new VersionCommand("version"));
        setBukkitPriorityCommand(new ReloadCommand("reload"));
        setBukkitPriorityCommand(new PluginsCommand("plugins"));
        setBukkitPriorityCommand(new TimingsCommand("timings"));
    }

    private void setDefaultBukkitCommand(VanillaCommand command) {
        String lowerLabel = command.getName().toLowerCase();
        vanillaCommands.put(lowerLabel, command);
        addressedCommands.put("bukkit:" + lowerLabel, command);
    }

    protected void setBukkitPriorityCommand(Command command) {
        String lowerLabel = command.getName().toLowerCase();
        addressedCommands.put("bukkit:" + lowerLabel, command);
        knownCommands.put(lowerLabel, command);
        priorityCommands.put(lowerLabel, command);
    }

    /**
     * {@inheritDoc}
     */
    public void registerAll(String fallbackPrefix, List<Command> commands) {
        if (commands != null) {
            for (Command c : commands) {
                register(fallbackPrefix, c);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean register(String fallbackPrefix, Command command) {
        return register(command.getName(), fallbackPrefix, command);
    }

    /**
     * {@inheritDoc}
     */
    public boolean register(String label, String address, Command command) {
        boolean registeredSuccessfully = register(label, command, false);

        Iterator<String> iterator = command.getAliases().iterator();
        while (iterator.hasNext()) {
            String lowerLabel = iterator.next().toLowerCase();
            if (!register(lowerLabel, command, registeredSuccessfully)) {
                iterator.remove();
                continue;
            }
            // Use an available alias as label if possible
            if (!registeredSuccessfully) {
                command.setLabel(lowerLabel);
                registeredSuccessfully = true;
            }
        }

        // Register prefixed alias
        addressedCommands.put(address + ":" + label, command);
        if (!registeredSuccessfully) {
            command.setLabel(address + ":" + label);
        }

        // Register to us so further updates of the commands label and aliases are postponed until its reregistered
        command.register(this);

        return registeredSuccessfully;
    }

    /**
     * Registers a command with the given name is possible
     *
     * @param label the name of the command, without the '/'-prefix.
     * @param command the command to register
     * @return true if command was registered, false
     *     otherwise. If isAlias was true a return of false indicates no
     *     command was registered.
     */
    private synchronized boolean register(String label, Command command, boolean isAlias) {
        String lowerLabel = label.trim().toLowerCase();

        if (isAlias && knownCommands.containsKey(lowerLabel)) {
            // Request is for an alias and it conflicts with a existing command or previous alias ignore it
            // Note: This will mean it gets removed from the commands list of active aliases
            return false;
        }

        // If the command exists but is an alias we overwrite it
        Command possibleAlias = knownCommands.get(lowerLabel);
        if (possibleAlias.getLabel().equals(lowerLabel)) {
            return false;
        }

        knownCommands.put(lowerLabel, command);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean dispatch(CommandSender sender, String commandLine) throws CommandException {
        String[] args = PATTERN_ON_SPACE.split(commandLine);

        if (args.length == 0) {
            return false;
        }

        String sentCommandLabel = args[0].toLowerCase();
        Command target = getCommand(sentCommandLabel);

        if (target == null) {
            return false;
        }

        try {
            // Note: we don't return the result of target.execute as thats success / failure, we return handled (true) or not handled (false)
            target.execute(sender, sentCommandLabel, Arrays_copyOfRange(args, 1, args.length));
        } catch (CommandException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing '" + commandLine + "' in " + target, ex);
        }

        // return true as command was handled
        return true;
    }

    public synchronized void clearCommands() {
        for (Map.Entry<String, Command> entry : knownCommands.entrySet()) {
            entry.getValue().unregister(this);
        }
        serverAliases.clear();
        priorityCommands.clear();
        addressedCommands.clear();
        knownCommands.clear();
        vanillaCommands.clear();
        setDefaultCommands();
    }

    public Command getCommand(String name) {
        String lowerName = name.toLowerCase();
        Command target = serverAliases.get(lowerName);
        if (target == null) {
            target = priorityCommands.get(lowerName);
        }
        if (target == null) {
            target = addressedCommands.get(lowerName);
        }
        if (target == null) {
            target = knownCommands.get(lowerName);
        }
        if (target == null) {
            target = vanillaCommands.get(lowerName);
        }
        return target;
    }

    public List<String> tabComplete(CommandSender sender, String cmdLine) {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(cmdLine, "Command line cannot null");

        int spaceIndex = cmdLine.indexOf(' ');

        if (spaceIndex == -1) {
            ArrayList<String> completions = new ArrayList<String>();
            Map<String, Command> knownCommands = this.knownCommands;

            final String prefix = (sender instanceof Player ? "/" : "");

            for (VanillaCommand command : vanillaCommands.values()) {
                String name = command.getName();

                if (!command.testPermissionSilent(sender)) {
                    continue;
                }
                if (knownCommands.containsKey(name)) {
                    // Don't let a vanilla command override a command added below
                    // This has to do with the way aliases work
                    continue;
                }
                if (!StringUtil.startsWithIgnoreCase(name, cmdLine)) {
                    continue;
                }

                completions.add(prefix + name);
            }

            for (Map.Entry<String, Command> commandEntry : knownCommands.entrySet()) {
                Command command = commandEntry.getValue();

                if (!command.testPermissionSilent(sender)) {
                    continue;
                }

                String name = commandEntry.getKey(); // Use the alias, not command name

                if (StringUtil.startsWithIgnoreCase(name, cmdLine)) {
                    completions.add(prefix + name);
                }
            }

            Collections.sort(completions, String.CASE_INSENSITIVE_ORDER);
            return completions;
        }

        String commandName = cmdLine.substring(0, spaceIndex);
        Command target = getCommand(commandName);

        if (target == null) {
            return null;
        }

        if (!target.testPermissionSilent(sender)) {
            return null;
        }

        String argLine = cmdLine.substring(spaceIndex + 1, cmdLine.length());
        String[] args = PATTERN_ON_SPACE.split(argLine, -1);

        try {
            return target.tabComplete(sender, commandName, args);
        } catch (CommandException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing tab-completer for '" + cmdLine + "' in " + target, ex);
        }
    }

    public Collection<Command> getCommands() {
        return Collections.unmodifiableCollection(knownCommands.values());
    }

    public Collection<VanillaCommand> getFallbackCommands() {
        return Collections.unmodifiableCollection(vanillaCommands.values());
    }

    public void registerServerAliases() {
        Map<String, String[]> values = server.getCommandAliases();

        for (String alias : values.keySet()) {
            String[] commandStrings = values.get(alias);
            List<FormattedCommandAlias> targets = new ArrayList<FormattedCommandAlias>();
            StringBuilder bad = new StringBuilder();

            for (String commandString : commandStrings) {
                String[] commandArgs = commandString.split(" ");
                Command command = getCommand(commandArgs[0]);

                if (command == null) {
                    if (bad.length() > 0) {
                        bad.append(", ");
                    }
                    bad.append(commandString);
                } else {
                    targets.add(new FormattedCommandAlias(alias, command, subArray(commandArgs, 1, commandArgs.length)));
                }
            }

            // We register these as commands so they have absolute priority.

            if (targets.size() > 0) {
                serverAliases.put(alias.toLowerCase(), new MultipleCommandAlias(alias.toLowerCase(), targets.toArray(new FormattedCommandAlias[targets.size()])));
            } else {
                serverAliases.remove(alias.toLowerCase());
            }

            if (bad.length() > 0) {
                server.getLogger().warning("The following command(s) could not be aliased under '" + alias + "' because they do not exist: " + bad);
            }
        }
    }

    private String[] subArray(String[] commandArgs, int start, int end) {
        String[] subArray = new String[end - start];
        for (int i = start; i < end; i++) {
            subArray[i - start] = commandArgs[i];
        }
        return subArray;
    }
}
