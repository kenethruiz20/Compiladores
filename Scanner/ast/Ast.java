package ast;

import java.util.List;

// Define the abstract syntax tree (AST) node class
public abstract class AstNode {
    // Common properties for AST nodes
    private int lineNumber; // Line number where the node is located in the source code

    public AstNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    // Define methods for visiting and traversing the AST
    public abstract void accept(AstVisitor visitor);
}

// Example of an AST node for a binary expression
public class BinaryExpressionNode extends AstNode {
    private String operator;
    private AstNode leftOperand;
    private AstNode rightOperand;

    public BinaryExpressionNode(int lineNumber, String operator, AstNode leftOperand, AstNode rightOperand) {
        super(lineNumber);
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public String getOperator() {
        return operator;
    }

    public AstNode getLeftOperand() {
        return leftOperand;
    }

    public AstNode getRightOperand() {
        return rightOperand;
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}

// Define a visitor interface for traversing the AST
public interface AstVisitor {
    void visit(BinaryExpressionNode node);
    // Add methods for visiting other types of AST nodes as needed
}

// Example of an AST node for a variable reference
public class VariableNode extends AstNode {
    private String name;

    public VariableNode(int lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}

// Example of an AST node for a function call
public class FunctionCallNode extends AstNode {
    private String functionName;
    private List<AstNode> arguments;

    public FunctionCallNode(int lineNumber, String functionName, List<AstNode> arguments) {
        super(lineNumber);
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<AstNode> getArguments() {
        return arguments;
    }

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }
}

