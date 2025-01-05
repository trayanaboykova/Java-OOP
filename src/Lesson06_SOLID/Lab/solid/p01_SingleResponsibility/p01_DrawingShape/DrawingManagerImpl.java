package src.Lesson06_SOLID.Lab.solid.p01_SingleResponsibility.p01_DrawingShape;

import src.Lesson06_SOLID.Lab.solid.p01_SingleResponsibility.p01_DrawingShape.interfaces.DrawingManager;
import src.Lesson06_SOLID.Lab.solid.p01_SingleResponsibility.p01_DrawingShape.interfaces.DrawingRepository;
import src.Lesson06_SOLID.Lab.solid.p01_SingleResponsibility.p01_DrawingShape.interfaces.Rengerer;
import src.Lesson06_SOLID.Lab.solid.p01_SingleResponsibility.p01_DrawingShape.interfaces.Shape;


public class DrawingManagerImpl implements DrawingManager {

    private final DrawingRepository drawingRepository;
    private final Rengerer renderer;

    public DrawingManagerImpl(DrawingRepository drawingRepository, Rengerer renderer) {
        this.drawingRepository = drawingRepository;
        this.renderer = renderer;
    }


    @Override
    public void draw(Shape shape) {
        shape.draw(this.renderer, this.drawingRepository);
    }
}
