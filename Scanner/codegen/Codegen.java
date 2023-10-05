package codegen;

import ast.*;
import irt.*;

public class Codegen {
    public static String generate(IRTNode root) {
        StringBuilder code = new StringBuilder();
        generateCode(root, code);
        return code.toString();
    }

    private static void generateCode(IRTNode node, StringBuilder code) {
        if (node instanceof IRSequentialNode) {
            IRSequentialNode sequentialNode = (IRSequentialNode) node;
            for (IRTNode child : sequentialNode.getChildren()) {
                generateCode(child, code);
            }
        } else if (node instanceof BinaryOpNode) {
            BinaryOpNode binaryNode = (BinaryOpNode) node;
            generateBinaryOperation(binaryNode, code);
        } else if (node instanceof AssignmentNode) {
            AssignmentNode assignmentNode = (AssignmentNode) node;
            generateAssignment(assignmentNode, code);
        }
        // Handle other types of IR nodes as needed
    }

    private static void generateBinaryOperation(BinaryOpNode node, StringBuilder code) {
        String operator = node.getOperator();
        IRTNode leftOperand = node.getLeftOperand();
        IRTNode rightOperand = node.getRightOperand();

        // Generate code for left and right operands
        generateCode(leftOperand, code);
        generateCode(rightOperand, code);

        // Generate code for the binary operation based on the operator
        if (operator.equals("+")) {
            code.append("ADD\n");
        } else if (operator.equals("-")) {
            code.append("SUB\n");
        } else if (operator.equals("*")) {
            code.append("MUL\n");
        } else if (operator.equals("/")) {
            code.append("DIV\n");
        }
        // Handle other operators as needed
    }

    private static void generateAssignment(AssignmentNode node, StringBuilder code) {
        String variable = node.getVariable();
        IRTNode value = node.getValue();

        // Generate code for the value
        generateCode(value, code);

        // Generate code for storing the value in the variable
        code.append("STORE ").append(variable).append("\n");
    }
}
