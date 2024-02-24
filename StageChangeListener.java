import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class StageChangeListener implements ChangeListener<Number> {
    private Pane root;
    private double width;
    private double height;
    public StageChangeListener(Pane root) {
        this.root = root;
        width = root.getWidth();
        height = root.getHeight();
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        double a = root.getHeight() / height;
        double b = root.getWidth() / width;
        double x = a > b ? b : a;
        Scale scale = new Scale(x, x);
        scale.setPivotX(0); // 设置X轴缩放起点为左上角
        scale.setPivotY(0); // 设置Y轴缩放起点为左上角
        root.getTransforms().clear();
        root.getTransforms().add(scale);
    }
}
