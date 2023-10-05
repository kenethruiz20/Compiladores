public class Token {
    // Define your token types as constants here
    public static final int EOF = -1;
    public static final int NUMBER = 1;
    public static final int NAME = 2;
    public static final int DASH = 3;
    public static final int COLON = 4;
    public static final int ERROR = 5; // Add more token types as needed

    private int type;
    private String value;

    public Token(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}

