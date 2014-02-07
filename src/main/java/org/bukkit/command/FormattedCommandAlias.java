package org.bukkit.command;

import java.util.ArrayList;
import java.util.List;

public class FormattedCommandAlias extends Command {
    private final Command command;
    private final String[] formatArgs;

    public FormattedCommandAlias(String alias, Command command, String[] args) {
        super(alias);
        this.command = command;
        this.formatArgs = args;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        try {
            args = buildArgs(args);
        } catch (Exception ex) {
            return false;
        }
        return command.execute(sender, getName(), args);
    }

    private String[] buildArgs(String[] args) {
        if (formatArgs.length == 0) {
            return args;
        }
        int index = 0;
        List<String> formattedArgs = new ArrayList<String>();
        for (String formatArg : formatArgs) {
            if (formatArg.startsWith("$")) {
                if (formatArg.startsWith("$*")) {
                    for (; index < args.length; index++) {
                        formattedArgs.add(args[index]);
                    }
                } else {
                    index++;
                    formattedArgs.add(args[Integer.valueOf(formatArg.substring(1)) - 1]);;
                }
            } else {
                formattedArgs.add(formatArg);
            }
        }
        return formattedArgs.toArray(new String[formattedArgs.size()]);
    }
}
