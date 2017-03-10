package stack;

public class ArrayStack<T> implements UnboundedStackInterface<T> {
	protected int top;
	protected T[] container;
	protected int capacity;
	
	public ArrayStack(int capacity) {
		this.capacity = capacity;
		container = (T[]) new Object[this.capacity];
		top = -1;
	}
	
	public void push(T element) throws IndexOutOfBoundsException {
		if (top < capacity - 1) {
			top ++;
			container[top] = element;
		}
		else {
			throw new IndexOutOfBoundsException("Stack is full!");
		}
	}
	
	public void pop() {
		if (top < 0) {
			throw new StackUnderflowException("Stack is empty");
		}
		else {
			top--;
		}
	}
	
	public T top() {
		if (isEmpty()) {
			throw new StackUnderflowException("Stack is empty");
		}
		else {
			return container[top];
		}
	}
	
	public boolean isEmpty() {
		if (top < 0) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i <= top; i++) {
			result += container[i];
		}
		return result;
	}
	

}
