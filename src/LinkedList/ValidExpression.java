package LinkedList;
import java.io.*;
import java.util.*;


/*
Input Format: The first line of input contains string expression.
If expression = “(1+2)*3” then input should be:
(1+2)*3
Output Format:
Output in a single line an integer value which is either 1 or 0 if the result is true and false respectively.
For input expression = “(1+2)*3”
1


 */
public class ValidExpression {
    private static boolean is_valid(String expression) {
        // Write your code here
        Stack<Character> expressionStack = new Stack();
        Stack<Integer> numberStack = new Stack();
        boolean match = false;
        for(char c: expression.toCharArray()){
            if(c >= '0' && c <= '9' ){
                numberStack.push(c - '0');
                match = true;
            }else if( isOpenBracket(c) || isOperator(c)){
                expressionStack.push(c);
                match = false;
            }else if(c == ')' || c == '}' || c == ']'){
                match =  validateExpr(c, expressionStack, numberStack);
            }
        }
        while(!expressionStack.isEmpty()){
            char poppedChar = expressionStack.pop();
            if(isOpenBracket(poppedChar)){
                match = false;
            }else{
                match = popIntegers(numberStack, poppedChar);
            }
        }
        if(numberStack.size() >=2){
            match = false;
        }
        return match;
    }
    public static boolean isOpenBracket(char c){
        return c == '(' || c == '{' || c == '[';
    }
    public static boolean isOperator(char c){
        return c == '+' || c == '*' || c == '-';
    }
    public static boolean popIntegers(Stack<Integer> numberStack, char c){
        if(numberStack.size() >=2){
            int operand1 = numberStack.pop();
            int operand2 = numberStack.pop();
            if(c == '+'){
                numberStack.push(operand1 + operand2);
            }
            if(c == '*'){
                numberStack.push(operand1 * operand2);
            }
            if(c == '-'){
                numberStack.push(operand1 - operand2);
            }
            return true;
        }else{
            return false;
        }
    }

    public static boolean validateExpr(char c, Stack<Character> expressionStack, Stack<Integer> numberStack){
        boolean isValid = false;
        while(!expressionStack.isEmpty()){
            char poppedChar = expressionStack.pop();
            if(isOperator(poppedChar)){
                isValid = popIntegers(numberStack, poppedChar);
                if(isValid)
                    continue;
                else
                    break;
            }else{
                if( (c==')' && poppedChar == '(') || (c=='}' && poppedChar == '{') || (c==']' && poppedChar == '[')){
                    isValid = true;break;
                }else{
                    isValid = false;break;
                }
            }
        }
        return isValid;

    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String expression = bufferedReader.readLine();

        boolean result = is_valid(expression);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedWriter.close();

        bufferedReader.close();
    }


}
