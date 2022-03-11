package net.elytrapvp.repulsortest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class RepulsorTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        // Prevents the code from running twice.
        if(!event.isSneaking()) {
            return;
        }

        Player player = event.getPlayer();

        for(Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if(entity instanceof TNTPrimed) {
                continue;
            }

            Location location = player.getLocation();
            location.setY(player.getLocation().getY() - 2.5);
            Vector direction = entity.getLocation().toVector().subtract(location.toVector()).normalize().multiply(new Vector(0.48, 1.15, 0.48));
            entity.setVelocity(entity.getVelocity().add(direction));
        }
    }
}
