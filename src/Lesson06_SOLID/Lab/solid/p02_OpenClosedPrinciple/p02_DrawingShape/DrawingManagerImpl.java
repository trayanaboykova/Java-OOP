package src.Lesson06_SOLID.Lab.solid.p02_OpenClosedPrinciple.p02_DrawingShape;

import src.Lesson06_SOLID.Lab.solid.p01_SingleResponsibility.p01_DrawingShape.interfaces.DrawingManager;
import src.Lesson06_SOLID.Lab.solid.p01_SingleResponsibility.p01_DrawingShape.interfaces.Shape;


public class DrawingManagerImpl implements DrawingManager {


    @Override
    public void draw(Shape shape) {
        if (shape instanceof Circle)
        {
            this.drawCircle((Circle)shape);
        }
            else if (shape instanceof Rectangle)
        {
            this.drawRectangle(((Rectangle)shape));
        }
    }
    public void drawCircle(Circle circle)
    {
        // Draw Circle
    }

    public void drawRectangle(Rectangle rectangle)
    {
        // Draw Rectangle
    }
}
