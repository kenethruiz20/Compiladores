%%

%public
%class Scanner
%integer
%unicode

%{
private int token;
private Object value;
private MyScanner scanner; // Replace MyScanner with your custom scanner class name

public Scanner(MyScanner scanner) {
    this.scanner = scanner;
}

// The next 3 methods are required to implement the yyInput interface
// These methods are used for communication between the parser and the lexer.

public boolean advance() throws java.io.IOException {
    value = new String("");
    token = yylex();
    return (token != YYEOF);
}

public int token() {
    return token;
}

public Object value() {
    return value;
}
%}

nl =     [\n\r]+
ws =     [ \t\b\015]+
number = [0-9]+
name =   [a-zA-Z]+
dash =   "-"
colon =  ":"

%%

{nl}      { /* do nothing */ }
{ws}      { /* do nothing */ }
{name}    { value = yytext(); return scanner.NAME(); } // Replace scanner.NAME() with the appropriate method in your scanner
{dash}    { return scanner.DASH(); } // Replace scanner.DASH() with the appropriate method in your scanner
{colon}   { return scanner.COLON(); } // Replace scanner.COLON() with the appropriate method in your scanner
{number}  {
    try {
        value = Integer.parseInt(yytext());
    } catch (NumberFormatException nfe) {
        // This code is used to handle exceptions, but it's recommended to avoid throwing Errors.
        // In practice, you should handle exceptions more gracefully.
        throw new Error();
    }
    return scanner.NUMBER(); // Replace scanner.NUMBER() with the appropriate method in your scanner
}
