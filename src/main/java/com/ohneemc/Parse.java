package com.ohneemc;

import com.ohneemc.api.Api;
import de.leonhard.storage.Json;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Parse implements Listener {
    private JsonToYaml plugin;
    public Parse(JsonToYaml plugin){
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void parseJson(PlayerJoinEvent event) {
        Api api = new Api();

        Json file = new Json(event.getPlayer().getUniqueId().toString(), plugin.getServer().getWorldContainer()
                + "/plugins/OhneeEssentials/userdata/homes");

        boolean isImported = api.isImported(event.getPlayer());

        if (isImported){
            return;
        }
        for (String i : file.getKeySet()){
            Bukkit.getLogger().info(i);

            double x = file.getDouble(i + ".x");
            double y = file.getDouble(i + ".y");
            double z = file.getDouble(i + ".z");
            float yaw = file.getFloat(i + ".yaw");
            float pitch = file.getFloat(i + ".pitch");
            String world = file.getString(i + ".world");

            Location home = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
            api.setHome(event.getPlayer(), i, x,y,z,yaw,pitch,world);
            Bukkit.getLogger().info("Sent home to OhneeMC " + i);
        }
        api.setImported(event.getPlayer());
    }
}
