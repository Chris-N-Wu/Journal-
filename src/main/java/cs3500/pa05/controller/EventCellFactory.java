package cs3500.pa05.controller;

import cs3500.pa05.model.EventJson;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Allows for updating an ObservableList
 */
public class EventCellFactory implements Callback<ListView<EventJson>, ListCell<EventJson>> {
  @Override
  public ListCell<EventJson> call(ListView<EventJson> param) {
    return new ListCell<>() {
      @Override
      protected void updateItem(EventJson item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
          setText(item.name());
        } else {
          setText(null);
        }
      }
    };
  }
}


