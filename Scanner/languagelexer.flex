%%

%public
%class Scanner
%implements MiniParser.yyInput
%integer

%line
%column
%unicode

%{
private int token;
private Object value;

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
{name}    { value = yytext(); return MiniParser.NAME; }
{dash}    { return MiniParser.DASH; }
{colon}   { return MiniParser.COLON; }
{number}  {
  try  {
    value = Integer.valueOf(Integer.parseInt(yytext()));
  } catch (NumberFormatException nfe) {
    // This code is used to handle exceptions, but it's recommended to avoid throwing Errors.
    // In practice, you should handle exceptions more gracefully.
    throw new Error();
  }
  return MiniParser.NUMBER;
}

