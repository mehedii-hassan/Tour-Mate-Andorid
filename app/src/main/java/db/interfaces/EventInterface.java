package db.interfaces;

import db.models.CreateEventModel;

public interface EventInterface {

    void onEventCreate(CreateEventModel createEventModel);
}
