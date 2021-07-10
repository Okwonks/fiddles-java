public class Main {
	public static void main(String[] args) {
		log("Clock started");
		Clock clock = new Clock();
		clock.run();
	}

	public static void log(String message) {
		System.out.println(message);
	}
}
