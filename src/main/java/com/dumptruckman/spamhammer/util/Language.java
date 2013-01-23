package com.dumptruckman.spamhammer.util;

import com.dumptruckman.spamhammer.api.SpamHammer;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Language {
    
    CFG_LOAD_ERROR("config.load.error", "Error while loading config! Plugin will be disabled!"),
    PERM_MISSING("perm.missing", "You don't have permission! (%1)"),
    COMMAND_ARG_MIN("command.arg.min",
            "Not enough arguments! Expected min: %1"),
    COMMAND_ARG_MAX("command.arg.max",
            "Too many arguments! Expected max: %1"),
    MUTE("mute.message.mute",
            "You will be muted for %1 second(s) for spamming.  Keep it up and you'll be kicked."),
    UNMUTE("mute.message.unmute", "You are no longer muted."),
    MUTED("mute.message.muted", "You are muted!"),
    KICK_MESSAGE("kick.message",
            "You have been kicked for spamming.  Keep it up and you'll be banned."),
    BAN_MESSAGE("ban.message", "You have been banned for spamming."),
    COOL_OFF_MESSAGE("cooloff.message",
            "Spamming punishment reset.  Be nice!"),

    SPAMMING_MESSAGE("spamming.message", "You are spamming! Chill out!"),
    UNMUTE_COMMAND_MESSAGE_SUCCESS("command.unmute.success",
            "%1 has been unmuted."),
    UNMUTE_COMMAND_MESSAGE_FAILURE("command.unmute.failure",
            "%1 is not muted."),
    //public static final Message UNBAN_COMMAND_MESSAGE_SUCCESS = new Message("command.unban.success",
    //        "%1 has been unbanned");
    //public static final Message UNBAN_COMMAND_MESSAGE_FAILURE = new Message("command.unban.failure",
    //        "%1 is not banned by SpamHammer.");
    RELOAD_COMMAND_MESSAGE_SUCCESS("command.reload.success",
            "Config reloaded!"),
    RESET_COMMAND_MESSAGE_SUCCESS("command.reset.success",
            "%1's punishment level reset."),
    
    VALID_GREATER_ZERO("validation.greater_than_zero",
            "Must be a number greater than zero!");
    
    String node;
    String msg;
    
    static YamlConfiguration cfgFile = null;
    
    Language(final String node, final String msg) {
        this.node = node;
        this.msg = msg;
    }
    
    public static void init(final SpamHammer plugin, final String file) throws IOException {
        final File cfg = new File(plugin.getDataFolder(), file);
        cfgFile = YamlConfiguration.loadConfiguration(cfg);
        if (!cfg.exists()) {
            cfg.createNewFile();
        }
        cfgFile = new YamlConfiguration();
        
        cfgFile.options().copyDefaults(true);
        for (Language l : Language.values()) {
            cfgFile.addDefault(l.node, l.msg);
        }
        cfgFile.save(cfg);
    }
    
    @Override
    public String toString() {
        return cfgFile.getString(node);
    }
}