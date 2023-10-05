package semantic;

import ast.*;

public class Semantic {
    public static void analyze(AstNode root) throws SemanticException {
        checkSemantics(root);
    }

    private static void checkSemantics(AstNode node) throws SemanticException {
        if (node instanceof BinaryExpressionNode) {
            BinaryExpressionNode binaryNode = (BinaryExpressionNode) node;
            checkSemantics(binaryNode.getLeftOperand());
            checkSemantics(binaryNode.getRightOperand());

            String operator = binaryNode.getOperator();
            if (!isValidBinaryOperator(operator)) {
                throw new SemanticException("Invalid binary operator: " + operator, binaryNode.getLineNumber());
            }
        } else if (node instanceof IntLiteralNode) {
            // No specific semantic checks for integer literals
        }
        // Add more semantic checks for other types of nodes as needed
    }

    private static boolean isValidBinaryOperator(String operator) {
        // Define valid binary operators
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
    }
}

class SemanticException extends Exception {
    private int lineNumber;

    public SemanticException(String message, int lineNumber) {
        super(message);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
