package opt;

import irt.*;

public class Algebraic {
    public static IRTNode optimize(IRTNode root) {
        return simplifyAlgebra(root);
    }

    private static IRTNode simplifyAlgebra(IRTNode node) {
        if (node instanceof BinaryOpNode) {
            BinaryOpNode binaryNode = (BinaryOpNode) node;
            IRTNode left = binaryNode.getLeftOperand();
            IRTNode right = binaryNode.getRightOperand();

            if (left instanceof IntLiteralNode && right instanceof IntLiteralNode) {
                int leftValue = ((IntLiteralNode) left).getValue();
                int rightValue = ((IntLiteralNode) right).getValue();
                int result = performAlgebraicOperation(leftValue, rightValue, binaryNode.getOperator());

                // Replace the BinaryOpNode with a new IntLiteralNode containing the result
                return new IntLiteralNode(result);
            }
        }

        // Recursively apply algebraic simplification to children nodes
        if (node instanceof BinaryOpNode) {
            BinaryOpNode binaryNode = (BinaryOpNode) node;
            binaryNode.setLeftOperand(simplifyAlgebra(binaryNode.getLeftOperand()));
            binaryNode.setRightOperand(simplifyAlgebra(binaryNode.getRightOperand()));
        }

        // For other nodes, recursively apply algebraic simplification to their children
        if (node instanceof IRSequentialNode) {
            IRSequentialNode sequentialNode = (IRSequentialNode) node;
            for (int i = 0; i < sequentialNode.getNumChildren(); i++) {
                sequentialNode.setChild(i, simplifyAlgebra(sequentialNode.getChild(i)));
            }
        }

        // If no algebraic simplification is performed, return the original node
        return node;
    }

    private static int performAlgebraicOperation(int left, int right, String operator) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if (right != 0) {
                    return left / right;
                } else {
                    // Handle division by zero
                    return 0; // You may want to throw an error here in a real compiler
                }
            default:
                // Handle unsupported operators
                return 0; // You may want to throw an error here in a real compiler
        }
    }
}
