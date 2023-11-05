package cs3500.pa05.controller;

import cs3500.pa05.model.TaskJson;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

/**
 * Creation of cells and alignment to tasks ObservableLists
 */
public class TaskCellFactory implements Callback<ListView<TaskJson>, ListCell<TaskJson>> {
  @Override
  public ListCell<TaskJson> call(ListView<TaskJson> param) {
    return new ListCell<>() {
      @Override
      protected void updateItem(TaskJson item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
          setText(item.name());
          Paint color;
          if (item.completed()) {
            color = Paint.valueOf("GREEN");
          } else {
            color = Paint.valueOf("RED");
          }
          setTextFill(color);
        } else {
          setText(null);
        }
      }
    };
  }
}


