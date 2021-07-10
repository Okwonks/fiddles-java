import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
	private String clockTime;

	public void run() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					clear();
					Calendar cal = Calendar.getInstance();

					int hours = cal.get(Calendar.HOUR_OF_DAY);
					int minutes = cal.get(Calendar.MINUTE);
					int seconds = cal.get(Calendar.SECOND);
					String ampm = hours >= 12 ? "PM" : "AM";

					log(formatTime(hours, minutes, seconds) + " " + ampm);
				}
		}, 1000L, 1000L);
	}

	public String formatTime(Integer hour, Integer minute, Integer seconds) {
		String pHour = prependZero(civilianHour(hour));
		String pMinute = prependZero(minute);
		String pSeconds = prependZero(seconds);
		String time = pHour + ":" + pMinute + ":" + pSeconds;
		return time;
	}

	public String prependZero(Integer num) {
		return num < 10 ? "0".concat(num.toString()) : num.toString();
	}

	public int civilianHour(Integer hours) {
		return hours > 12 ? hours - 12 : hours;
	}

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void setTimeout(Runnable runnable, int delay) {
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				runnable.run();
			} catch (Exception e) {
				System.err.println(e);
			}
		}).start();
	}
	
	static void log(String message) {
		System.out.println(message);
	}
}
