package com.ohneemc;

import org.bukkit.plugin.java.JavaPlugin;

public class JsonToYaml extends JavaPlugin {

    public void onEnable(){
        getServer().getPluginManager().registerEvents(new Parse(this), this);
    }

    public void onDisable(){

    }
}
