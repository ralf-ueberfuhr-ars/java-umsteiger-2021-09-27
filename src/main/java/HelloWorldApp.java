public class HelloWorldApp {

	public static void main(String[] args) {
		System.out.println("Hallo " + (args.length > 0 ? args[0] : "Welt") + "!");
	}

}