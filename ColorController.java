import javafx.scene.control.ColorPicker;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.List;

public class ColorController {
    private ColorPicker colorPickerForRectangle;
    private ColorPicker colorPickerForCurve;

    public ColorController() {
        colorPickerForRectangle = new ColorPicker();
        colorPickerForCurve = new ColorPicker();
    }

    public ColorPicker getColorPickerForRectangle() {
        return colorPickerForRectangle;
    }

    public ColorPicker getColorPickerForCurve() {
        return colorPickerForCurve;
    }

    public void addColorControl(Link link) {
        colorPickerForRectangle.setOnAction(event -> {
            // 当用户选择一个新的颜色时，改变矩形的颜色
            Color newColor = colorPickerForRectangle.getValue();
            List<Node> nodes = link.getNode();
            for (Node node : nodes) {
                if (node instanceof Rectangle) {
                    ((Rectangle) node).setFill(newColor);
                }
            }
        });

        colorPickerForCurve.setOnAction(event -> {
            // 当用户选择一个新的颜色时，改变曲线的颜色
            Color newColor = colorPickerForCurve.getValue();
            link.setColor(newColor);
        });
    }
}

