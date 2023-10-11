import java.io.*;
import java.util.*;

public class Solution {
    public static int precedence(char ch){
        if(ch == '+') return 1;
        else if(ch == '-') return 1;
        else if(ch == '*') return 2;
        else if(ch == '/') return 2;
        else return 0;
    }
    
    public static int operation(int a, int b, char ch){
        if(ch == '+') return a+b;
        else if(ch == '-') return a-b;
        else if (ch == '*') return a*b;
        else return a/b;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc =new Scanner(System.in);
        String exp = sc.nextLine();
        
        Stack<Character> st1 = new Stack<>(); // operators and opening bracket
        Stack<String> st2 = new Stack<>(); // postfix exp
        Stack<String> st3 = new Stack<>(); // prefix exp
        
        for(int i=0; i<exp.length(); i++){
            char ch = exp.charAt(i);
            
            if (ch == '('){
                st1.push(ch);
            }
            else if (ch>='a' && ch<='z'){
                st2.push(ch + "");
                st3.push(ch + "");
            }
            else if (ch == ')'){
                while(st1.peek() != '('){
                    char op = st1.pop();
                    
                    String postv2 = st2.pop();
                    String postv1 = st2.pop();                   
                    String postv = postv1 + postv2 + op;
                    st2.push(postv);
                    
                    String prev2 = st3.pop();
                    String prev1 = st3.pop();                   
                    String prev = op + prev1 + prev2;
                    st3.push(prev);
                }
                st1.pop(); // poping opening bracket
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                while(st1.size() > 0 && precedence(ch) <= precedence(st1.peek())){
                    char op = st1.pop();
                    
                    String postv2 = st2.pop();
                    String postv1 = st2.pop();                   
                    String postv = postv1 + postv2 + op;
                    st2.push(postv);
                    
                    String prev2 = st3.pop();
                    String prev1 = st3.pop();                   
                    String prev = op + prev1 + prev2;
                    st3.push(prev);
                }
                      st1.push(ch);
            }
        }
        while(st1.size() > 0){
            char op = st1.pop();
            
            String postv2 = st2.pop();
            String postv1 = st2.pop();                   
            String postv = postv1 + postv2 + op;
            st2.push(postv);
                    
            String prev2 = st3.pop();
            String prev1 = st3.pop();                   
            String prev = op + prev1 + prev2;
            st3.push(prev);
        }
        System.out.println(st2.pop());
        System.out.println(st3.pop());
        
    }
}
