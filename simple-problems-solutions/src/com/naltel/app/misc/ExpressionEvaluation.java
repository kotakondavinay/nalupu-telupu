/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

import java.util.Stack;

/**
 *
 * @author vinaykk
 */
public class ExpressionEvaluation {
        public static int expressionEval(String exp) {
        char[] tokens = exp.toCharArray();
        Stack<Integer> values = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();
        for(int i=0; i < tokens.length; i++){
            if(tokens[i] == ' '){
                continue;
            }
            if(tokens[i] >= '0' && tokens[i] <= '9'){
                StringBuffer sb = new StringBuffer();
                while(i<tokens.length && tokens[i] >= '0' && tokens[i] <= '9'){
                    sb.append(tokens[i++]);
                }
                values.push(Integer.parseInt(sb.toString()));
            }
            if(i== tokens.length)continue;
            if(tokens[i] == '('){
                ops.push(tokens[i]);
            }
            else if(tokens[i] == ')'){
                while(ops.peek() != '('){
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));   
                }
                ops.pop();
            }
            else if(tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                while(!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(tokens[i], values.pop(), values.pop()));
                    //ops.pop();
                }
                ops.push(tokens[i]);
            }
        }
        while(!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }
        return values.pop();
    }
    
    
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    
    public static int applyOp(char op, int a, int b){
        switch(op){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
                if(b==0){
                    throw new UnsupportedOperationException("Cannot divided by zero");
                }
                return a/b;
        }
        return 0;
    }

}
