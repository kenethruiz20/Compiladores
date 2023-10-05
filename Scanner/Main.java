import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        try {
            Reader inputReader = new FileReader("C:\\Users\\Alphazero\\Desktop\\GroupNN\\Example.txt"); // Replace with your input file name
            MyScanner scanner = new MyScanner(inputReader);

            int token;
            do {
                token = scanner.getNextToken();
                System.out.println("Token: " + token);
                scanner.consume(); // Move to the next character
            } while (token != Token.EOF);
            
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
