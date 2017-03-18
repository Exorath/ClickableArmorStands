package com.exorath.clickents.impl;

import com.exorath.clickents.api.ClickableEntity;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

/**
 * Created by toonsev on 10/9/2016.
 */
public class IClickableEntity<T extends Entity> implements ClickableEntity {
    private T entity;
    private PublishSubject<PlayerInteractAtEntityEvent> clickSubject = PublishSubject.create();


    public  IClickableEntity(Class<T> entityType, Location location){
        this.entity = location.getWorld().spawn(location, entityType);
    }

    public void emitClick(PlayerInteractAtEntityEvent event) {
        clickSubject.onNext(event);
    }

    public T getEntity(){
        return entity;
    }

    public Observable<PlayerInteractAtEntityEvent> getInteractObservable() {
        return clickSubject;
    }
}
