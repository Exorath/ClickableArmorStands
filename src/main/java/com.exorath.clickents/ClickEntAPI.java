package com.exorath.clickents;

import com.exorath.clickents.api.ClickableEntity;
import com.exorath.clickents.impl.SimpleClickableEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;


/**
 * Created by toonsev on 10/9/2016.
 */
public class ClickEntAPI implements Listener {
    private HashMap<Entity, ClickableEntity> entities = new HashMap<>();

    public ClickEntAPI(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    public <T extends Entity> ClickableEntity<T> makeClickable(T entity) {
        entity.setInvulnerable(true);
        entity.setGravity(false);
        entity.setSilent(true);
        if(entity instanceof LivingEntity)
            ((LivingEntity) entity).setAI(false);
        ClickableEntity<T> clickableEntity = new SimpleClickableEntity(entity);
        entities.put(entity, clickableEntity);
        return clickableEntity;
    }

    public void removeEntity(Entity entity){
        ClickableEntity clickableEntity = entities.remove(entity);
        if(clickableEntity != null)
            clickableEntity.terminate();
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(entities.containsKey(event.getEntity()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityClick(PlayerInteractAtEntityEvent playerInteractAtEntityEvent) {
        if (entities.containsKey(playerInteractAtEntityEvent.getRightClicked())) {
            playerInteractAtEntityEvent.setCancelled(true);
            entities.get(playerInteractAtEntityEvent.getRightClicked()).emitClick(playerInteractAtEntityEvent);
        }
    }
}
