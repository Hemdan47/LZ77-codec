import java.io.Serializable;

public class Tag implements Serializable{
    private int pos , length;
    private char nextChar;

    public Tag(int pos, int length, char nextChar) {
        this.pos = pos;
        this.length = length;
        this.nextChar = nextChar;
    }

    public int getPos() {
        return pos;
    }

    public int getLength() {
        return length;
    }

    public char getNextChar() {
        return nextChar;
    }

    @Override
    public String toString() {
        return "<" + pos + "," + length + "," + nextChar + ">";
    }
}
