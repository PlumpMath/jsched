package jsched;

public interface Body<T> {
	Continuation exec(T arg);
}
