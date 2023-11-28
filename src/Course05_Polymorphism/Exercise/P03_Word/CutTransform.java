package Course05_Polymorphism.Exercise.P03_Word;

public class CutTransform implements TextTransform {

    protected StringBuilder cutText;

    protected StringBuilder getCutText() {
        return cutText;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {

        if (startIndex == endIndex) {
            cutText = new StringBuilder();
        } else {
            cutText = new StringBuilder(text.substring(startIndex, endIndex));
            text.delete(startIndex, endIndex);
        }
    }
}
