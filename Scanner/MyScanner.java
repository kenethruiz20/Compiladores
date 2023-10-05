import java.io.IOException;
import java.io.Reader;

public class MyScanner {
    private Reader reader;
    private int currentChar;

    public MyScanner(Reader reader) {
        this.reader = reader;
        try {
            currentChar = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextToken() {
        // Implement your token recognition logic here
        // This is just a placeholder, you should replace it with your actual logic.
        if (currentChar == -1) {
            return Token.EOF;
        } else if (Character.isDigit(currentChar)) {
            return Token.NUMBER;
        } else if (Character.isLetter(currentChar)) {
            return Token.NAME;
        } else if (currentChar == '-') {
            return Token.DASH;
        } else if (currentChar == ':') {
            return Token.COLON;
        } else {
            // Handle other characters or return an error token
            return Token.ERROR;
        }
    }

    public void consume() {
        try {
            currentChar = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
