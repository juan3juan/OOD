class Singleton {
	private static Singleton single_instance = null;
	public String s;

	private Singleton() {
		s = "Hello";
	}

	public static Singleton getInstance() {
		if (single_instance == null)
			single_instance = new Singleton();
		return single_instance;
	}
}

public class TwoSum {
//	public static void main(String[] args) {
//		Singleton x = Singleton.getInstance();
//		Singleton y = Singleton.getInstance();
//		x.s = (x.s).toUpperCase();
//		System.out.println(x.s);
//		System.out.println(y.s);
//	}
}