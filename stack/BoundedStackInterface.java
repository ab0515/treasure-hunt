package stack;

public interface BoundedStackInterface<T> extends StackInterface<T>{
	public void pop();
	
	public boolean isEmpty();
	
	public T top();
	

}
