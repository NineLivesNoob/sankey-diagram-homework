import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.Arrays;
import java.util.List;

public class Link {
    private String content;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double strokeWidth;
    private Color color;

    public Link(String content, double startX, double startY, double endX, double endY, double strokeWidth, Color color) {
        this.content = content;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.strokeWidth = strokeWidth;
        this.color = color;
    }
    public List<Node> getNode() {
        //创建曲线
        Node cubicCurve = createCubicCurve();
        //创建头部方块
        Rectangle start = new Rectangle(startX, startY - strokeWidth / 2, 30, strokeWidth);
        start.setFill(Color.DODGERBLUE);
        //创建取消末尾方块
        Rectangle end = new Rectangle(endX, endY - strokeWidth / 2, 30, strokeWidth);
        end.setUserData("end");
        end.setFill(color.darker());
        //创建方块前面的文本
        Text text = new Text(endX - 200, endY + 4, content); // 调整 x, y 坐标以适应位置
        text.setWrappingWidth(200);
        text.setTextAlignment(TextAlignment.RIGHT);
        text.setFill(Color.BLACK); // 设置文本颜色
        return Arrays.asList(cubicCurve,start, end, text);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;

    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    //画贝塞尔曲线连接图像的方法
    public Node createCubicCurve() {
        double midpointX = (startX + endX) / 2;
        CubicCurve cubicCurve = new CubicCurve();
        cubicCurve.setUserData("CubicCurve");
        cubicCurve.setStartX(startX + strokeWidth / 2);
        cubicCurve.setStartY(startY);
        cubicCurve.setControlX1(midpointX);
        cubicCurve.setControlY1(startY); // For upper curve curving down
        cubicCurve.setControlX2(midpointX);
        cubicCurve.setControlY2(endY); // For lower curve curving up
        cubicCurve.setEndX(endX - strokeWidth / 2);
        cubicCurve.setEndY(endY);
        cubicCurve.setStrokeWidth(strokeWidth);
        cubicCurve.setStroke(color);
        cubicCurve.setFill(null);
        return cubicCurve;
    }
}
