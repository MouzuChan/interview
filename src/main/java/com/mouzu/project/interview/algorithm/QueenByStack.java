package com.mouzu.project.interview.algorithm;

import java.util.Stack;

/**
 * 用栈实现一个队列,保证先进先出.
 * 思路：
 *  创建两个栈,执行队列的push操作时,向其中一个栈(PushStack)执行Push操作,执行poll操作时,先将数据弹出进另一个栈(PopStack),然后再执行pop操作.
 *
 */
public class QueenByStack {
    /*用栈实现一个队列*/
    public static class DoubleStackQueen{
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;
        public DoubleStackQueen() {
            this.stackPush = new Stack<Integer>();
            this.stackPop =  new Stack<Integer>();
        }
        public void pushToPop(){
            if(this.stackPush.isEmpty()){
                throw new RuntimeException("Your Queen is empty !");
            }
            while(!this.stackPush.isEmpty()){
                this.stackPop.push(this.stackPush.pop());
            }
        }
        public void popToPush(){
            if(this.stackPop.isEmpty()){
                return;
            }
            while(!this.stackPop.isEmpty()){
                this.stackPush.push(this.stackPop.pop());
            }
        }
        public void add(int pushInt){
            this.stackPush.push(pushInt);
        }
        public  int poll(){
            pushToPop();
            int value =  this.stackPop.pop();
            popToPush();
            return value;
        }
        public int peek(){
            pushToPop();
            int value =  this.stackPop.peek();
            popToPush();
            return value;
        }
    }
    public static void main(String[] args) {
        DoubleStackQueen queen = new DoubleStackQueen();
        queen.add(1);
        queen.add(2);
        queen.add(3);
        queen.add(4);
        queen.add(5);
        queen.add(6);
        queen.add(7);
        System.out.println(queen.poll());
        queen.add(8);
        System.out.println(queen.poll());
        System.out.println(queen.poll());
        System.out.println(queen.poll());
        System.out.println(queen.poll());
        System.out.println(queen.poll());
        System.out.println(queen.poll());
        System.out.println(queen.poll());
    }
}
