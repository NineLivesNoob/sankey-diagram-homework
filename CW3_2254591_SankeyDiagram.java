
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CW3_2254591_SankeyDiagram extends Application {
//替代/d++来进行筛选数字的方法
public static void main(String[] args) {
        launch(args);
        }
        Color value;

//画贝塞尔曲线连接图像的方法
@Override
public void start(Stage primaryStage) {
        //调用方法扫描文档

        Map<String, Integer> data = NumberChecker.scanText("C:/Users/ASUS/Desktop/CW/cw/src/example4.txt");
        List<Map.Entry<String, Integer>> lists = new ArrayList<>();

        int total = 0;//Budget总和
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
        if (entry.getValue() != null) {
        total += entry.getValue();
        lists.add(entry);
        }
        }
        //对键值对进行排序
        for (int i = 0; i < lists.size() - 1; i++) {
        for (int j = 0; j < lists.size() - 1 - i; j++) {
        if (lists.get(j).getValue() < lists.get(j + 1).getValue()) {
        Map.Entry<String, Integer> temp = lists.get(j);
        lists.set(j, lists.get(j + 1));
        lists.set(j + 1, temp);
        }
        }
        }

        //锚点布局
        AnchorPane root = new AnchorPane();
        //创建 CubicCurve pane
        Pane chartPane = new Pane();
        root.getChildren().add(chartPane);
        AnchorPane.setBottomAnchor(chartPane,0.0);
        AnchorPane.setTopAnchor(chartPane,0.0);
        AnchorPane.setLeftAnchor(chartPane,0.0);
        AnchorPane.setRightAnchor(chartPane,0.0);
        root.setPrefSize(600, 402);
        Scene scene = new Scene(root);
        //头部添加颜色选择器
        ColorPicker colorPickerA = new ColorPicker();
        ColorPicker colorPickerB = new ColorPicker();
        HBox hBox = new HBox(new Label("chart color:"),colorPickerA,new Label("BackGroup:"),colorPickerB);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(10);
        root.getChildren().add(hBox);
        colorPickerA.setOnAction(event -> {
        //获取颜色选择器的颜色值
        value = colorPickerA.getValue();
        //取出中间的cubicCurves 和右侧的方块 end 和 CubicCurve 是在创建对象时候进行标记的， Link 39 行和 111 行
        List<Node> rects = new ArrayList<>();
        List<Node> cubicCurves = new ArrayList<>();
        for (Node node : chartPane.getChildren()) {
        if ("end".equals(node.getUserData())) {
        rects.add(node);
        } else if ("CubicCurve".equals(node.getUserData())) {
        cubicCurves.add(node);
        }
        }
        for (int i = 0; i < cubicCurves.size(); i++) {
        CubicCurve cubicCurve = ((CubicCurve) cubicCurves.get(i));
        //设置颜色
        cubicCurve.setStroke(value);
        Rectangle rect = ((Rectangle) rects.get(i));
        //设置方块颜色
        rect.setFill(value.darker());
        //将颜色变浅
        value = value.deriveColor(0, 1.0, 1, 0.7);
        }
        });
        colorPickerB.setOnAction(event -> {
        //设置背景色
        root.setBackground(new Background(new BackgroundFill(colorPickerB.getValue(), null, null)));
        });

        double startX = 70, startY = 100;
        double endX = 440, endY = 30;
        int viewHeight = 380;//设定右边区域最大尺寸为380

        int sum = 0;
        for (Map.Entry<String, Integer> entry :lists) {
        if (entry.getValue() != null) {
        // 创建文本对象，将字符串和数字拼接起来
        String textContent = entry.getKey() + ": " + entry.getValue();
        //创建的曲线宽带为viewHeight的百分比大小 * 0.5
        double strokeWidth = entry.getValue() * 1.0 / total * viewHeight * 0.5;
        startY+=strokeWidth/2;
        endY+=strokeWidth/2 + 20;
        sum+=entry.getValue();
        Link link = new Link(textContent, startX, startY, endX, endY, strokeWidth,
        Color.hsb(entry.getValue() * 1.0 / total * 360.0, sum*1.0/total, sum*1.0/total));
        startY = startY + strokeWidth/2;
        endY = endY + strokeWidth/2;
        chartPane.getChildren().addAll(link.getNode());
        }
        }

        //绘制左端文字
        String[] strings = data.keySet().toArray(new String[0]);
        String textContent = strings[1] + ":" + total;
        Text budgetText = new Text(0, 200, textContent); // 调整 x, y 坐标以适应位置
        budgetText.setFill(Color.BLACK); // 设置文本颜色
        chartPane.getChildren().add(budgetText);

        primaryStage.setScene(scene);
        primaryStage.setTitle(strings[0]);
        primaryStage.show();
        //设置放大缩小监听
        setResize(primaryStage, chartPane);
        }

private static void setResize(Stage primaryStage, Pane root) {
        //宽高变化监听器
        StageChangeListener stageChangeListener = new StageChangeListener(root);
        primaryStage.heightProperty().addListener(stageChangeListener);
        primaryStage.widthProperty().addListener(stageChangeListener);
        primaryStage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
@Override
public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        Platform.runLater(() -> {
        //+0.1只是为了让宽度变化从而通知到宽高变化监听器
        primaryStage.setWidth(primaryStage.getWidth() + 0.1);
        });
        }
        });
        }

        }
