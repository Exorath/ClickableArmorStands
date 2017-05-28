package com.exorath.clickents.impl;

import com.exorath.clickents.api.ClickableEntity;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

/**
 * Created by toonsev on 3/18/2017.
 */
public class SimpleClickableEntity<T extends Entity> implements ClickableEntity {
    private T entity;

    private PublishSubject<PlayerInteractAtEntityEvent> interactObservable = PublishSubject.create();

    public SimpleClickableEntity(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public void emitClick(PlayerInteractAtEntityEvent event) {
        interactObservable.onNext(event);
    }

    public Observable<PlayerInteractAtEntityEvent> getInteractObservable() {
        return interactObservable;
    }

    @Override
    public void terminate() {
        interactObservable.onComplete();
    }
}
