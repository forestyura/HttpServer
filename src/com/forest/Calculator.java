package com.forest;

public class Calculator {
    private int operand1;
    private int operand2;
    private String reguest;

    public Calculator (String uri) {
        reguest = uri.split("/?", 2)[1];
        getOperand(reguest);
    }

    public double calc() {
        switch (getOperation(reguest)) {
            case  "add":
                return operand1 + operand2;
            case "sub":
                return operand1 - operand2;
            case "mul":
                return operand1 * operand2;
            case "div":
                return operand1 / operand2;
            default:
                return 0;
        }
    }

    public String getOperation (String request) {
        return request.split("=", 2)[1].split("&" ,2)[0];
    }
    public void getOperand(String reguest) {
        String operandRequest = reguest.split("&", 2)[1];
        operand1 = Integer.parseInt(operandRequest.split("=",2)[1].split("&", 2)[0]);
        operand2 = Integer.parseInt(operandRequest.split("=",3)[2]);
    }
}
