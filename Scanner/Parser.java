public class Parser {
    private MyScanner scanner;

    public Parser(MyScanner scanner) {
        this.scanner = scanner;
    }

    public void parse() throws Exception {
        // Start parsing by calling the first production rule
        start();
    }

    // Production rule: start -> statement
    private void start() throws Exception {
        statement();
    }

    // Production rule: statement -> NAME COLON expr
    private void statement() throws Exception {
        match(MyScanner.NAME);   // Expect a NAME token
        match(MyScanner.COLON);  // Expect a COLON token
        expr();                  // Parse the expression
    }

    // Production rule: expr -> NUMBER
    private void expr() throws Exception {
        match(MyScanner.NUMBER); // Expect a NUMBER token
    }

    // Helper method to match a token type
    private void match(int expectedTokenType) throws Exception {
        int tokenType = scanner.advance() ? scanner.token() : MyScanner.YYEOF;

        if (tokenType == expectedTokenType) {
            // Token type matches, proceed
            String token
