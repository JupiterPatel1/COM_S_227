package hw2;

public class ClockRadioTest {

	public static void main(String[] args) {
		
		ClockRadio c = new ClockRadio();
		System.out.println(c.getClockTimeRaw()); // expected 0
		c.setTime(60);
		System.out.println(c.getClockTimeRaw()); // expected 60
		c.advanceTime(15);
		System.out.println(c.getClockTimeRaw()); // expected 75
		c.advanceTime(4000);
		System.out.println(c.getClockTimeRaw()); // expected 1195

	}

}
