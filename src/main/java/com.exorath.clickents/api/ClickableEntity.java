package com.exorath.clickents.api;

import io.reactivex.Observable;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

/**
 * Created by toonsev on 10/9/2016.
 */
public interface ClickableEntity<T extends Entity> {
    /**
     * Gets the entity this ClickableEntity encloses.
     * @return the entity this ClickableEntity encloses
     */
    T getEntity();

    void emitClick(PlayerInteractAtEntityEvent event);
    /**
     * Gets an observable that emits an event whenever this entity is clicked.
     * @return an observable that emits an event whenever this entity is clicked
     */
    Observable<PlayerInteractAtEntityEvent> getInteractObservable();
}
