package irt;

import java.util.List;

// Define the abstract intermediate representation (IR) node class
public abstract class IRTNode {
    // Common properties for IR nodes
    private int lineNumber; // Line number where the node is located in the source code

    public IRTNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    // Define methods for visiting and traversing the IR
    public abstract void accept(IRVisitor visitor);
}

// Example of an IR node for binary operations
public class BinaryOpNode extends IRTNode {
    private String operator;
    private IRTNode leftOperand;
    private IRTNode rightOperand;

    public BinaryOpNode(int lineNumber, String operator, IRTNode leftOperand, IRTNode rightOperand) {
        super(lineNumber);
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public String getOperator() {
        return operator;
    }

    public IRTNode getLeftOperand() {
        return leftOperand;
    }

    public IRTNode getRightOperand() {
        return rightOperand;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

// Define a visitor interface for traversing the IR
public interface IRVisitor {
    void visit(BinaryOpNode node);
    // Add methods for visiting other types of IR nodes as needed
}

// Example of an IR node for integer literals
public class IntLiteralNode extends IRTNode {
    private int value;

    public IntLiteralNode(int lineNumber, int value) {
        super(lineNumber);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}

// Example of an IR node for assignment statements
public class AssignmentNode extends IRTNode {
    private String variable;
    private IRTNode value;

    public AssignmentNode(int lineNumber, String variable, IRTNode value) {
        super(lineNumber);
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public IRTNode getValue() {
        return value;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
