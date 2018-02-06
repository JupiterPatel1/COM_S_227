package hw2;

import java.lang.String;

/**
 * 
 * @author Richard Smith
 *
 */
public class ClockRadio {

  /**
  * number of minutes in a 24-hour day.
  */
  public static final int MINUTES_PER_DAY = 1440;

  /**
  * number of minutes alarm snoozes.
  */
  public static final int SNOOZE_MINUTES = 9;

  /**
  * clock time measured in minutes.
  */
  private int clockTime;

  /**
  * alarm time measured in minutes.
  */
  private int alarmTime;

  /**
  * The clock time expressed in the form of a string.
  * Format: "HH:MM", "HH:MM AM", or "HH:MM PM".
  */
  private String clockTimeStr;

  /**
  * The alarm time expressed in the form of a string.
  * "HH:MM", "HH:MM AM", or "HH:MM PM".
  */
  private String alarmTimeStr;

  /**
  * The effective alarm time measured in minutes.
  */
  private int effectiveAlarmT;

  /**
  * The effective alarm time expressed in the form of a string.
  * "HH:MM", "HH:MM AM", or "HH:MM PM".
  */
  private String effectiveAlarmTimeStr;

  /**
  * Returns true when the alarm is buzzing, and false when it is
  * either not sounding or playing the radio.
  */
  private boolean buzz;
  
  /**
   * Returns true when the radio is playing, and false when it is
   * either not sounding or buzzing
   */
  private boolean radio;

  /**
  * Returns true when the alarm is either buzzing or playing
  * the radio, and false when it is not sounding.
  */
  private boolean sound;

  /**
  * If the display is true, the clock is set in 24-hour mode.
  * If the display is false, the clock is set in 12-hour mode.
  */
  private boolean display;

  /**
  * Returns true when the alarm is on, and false when
  * the alarm is off.
  */
  private boolean alarmOn;

  /**
  * Returns true when snooze is on,
  * and false when it is off.
  */
  private boolean snooze;

  /**
  * Constructs a clock radio with initial clock
  * time at 00:00 and alarm time at 01:00
  */
  public ClockRadio () {
	buzz = false;
	radio = false;
	sound = false;
	display = true;
	alarmOn = false;
	snooze = false;
    clockTime = 0;
    alarmTime = 60;
    clockTimeStr = "00:00";
    alarmTimeStr = "01:00";
    effectiveAlarmT = alarmTime;
    effectiveAlarmTimeStr = alarmTimeStr;
  }

  /**
  * @param givenMinutesPastMidnight
  * Constructs a clock radio with the given initial 
  * clock time and with the alarm time at 01:00
  */
  public ClockRadio (int givenMinutesPastMidnight) 
  {
	clockTime = givenMinutesPastMidnight % 1440;
    clockTimeStr = "00:" + clockTime;
    alarmTime = 60;
    alarmTimeStr = "01:00";
    effectiveAlarmT = alarmTime;
    effectiveAlarmTimeStr = alarmTimeStr;
    buzz = false;
    radio = false;
    sound = false;
    display = true;
    alarmOn = false;
    snooze = false;
  }

  /**
  * @param givenMinutes
  * Advances the clock time by the specified number of minutes.
  * If snooze is not in effect, "effective alarm T" is the 
  * alarm time as currently set. 
  * If snooze is in effect, the "effective alarm time" is 9 minutes after 
  * the clock time at which the snooze() method was last called. 
  * If the clock goes into sounding mode as a result of advancing the time, 
  * snooze is canceled. 
  * If the updated clock time passes or equals the effective alarm time, 
  * and the alarm is on, then the clock should go into sounding mode. 
  */
  public void advanceTime (int givenMinutes) 
  {

    clockTime = clockTime + givenMinutes;
    
    if (clockTime >= effectiveAlarmT && alarmOn == true) 
    {
      sound = true;
    }
    if (snooze == false) 
    {
      effectiveAlarmT = alarmTime;
    }
    else if (snooze == true) 
    {
      effectiveAlarmT = SNOOZE_MINUTES;
    }
    if (sound == true && clockTime >= alarmTime) 
    {
      snooze = false;
    }
  }

  /**
  * @param givenHours
  * @param givenMinutes
  * Advances the clock time by the given hours and minutes. 
  * If the updated clock time passes or equals the effective alarm time, 
  * and the alarm is set, then the clock should go into ringing mode.
  * If the clock goes into ringing mode as a result of advancing the time, 
  * snooze is canceled.  
  * If snooze is not in effect, "effective alarm time" is just the alarm time as currently set. 
  * If snooze is in effect, the "effective alarm time" is 9 minutes after the clock time at which 
  * the snooze() method was called. 
  * The arguments have to be non-negative numbers
  */
  public void advanceTime (int givenHours, int givenMinutes) 
  {
	  givenMinutes = givenMinutes + (givenHours * 60);
	  clockTime = clockTime + givenMinutes;
	  
	if (clockTime >= effectiveAlarmT && alarmOn == true) 
	{
		sound = true;
	}
	if (snooze == false) 
	{
		effectiveAlarmT = alarmTime;
	}
	if (snooze == true) 
	{
		effectiveAlarmT = SNOOZE_MINUTES + alarmTime;
	}
	if (sound == true && clockTime >= alarmTime) 
	{
		snooze = false;
	}
  }

 
  /**
  * Turns off alarm and stops it from sounding 
  * and cancels snooze if it is in effect.
  */
  public void alarmDisabled () 
  {
    alarmOn = false;
    sound = false;
    snooze = false;
  }

  /**
  * Turns alarm on, but not sound.
  */
  public void alarmEnabled () 
  {
    alarmOn = true;
  }

  /**
  * Returns current alarm time as a string in form
  * depending on whether the clock is currently in 24-hour mode:
  * "hh:mm", "hh:mm AM", or "hh:mm PM"
  * @return alarmTimeString
  */
  public java.lang.String getAlarmTimeAsString () 
  {

    if (display == true) 
    {
      alarmTimeStr = String.format("%02d:%02d", (alarmTime / 60), (alarmTime % 60));
    }
    else if (display == false && alarmTime < 720) 
    {
      alarmTimeStr = String.format("%02d:%02d AM", (alarmTime / 60), (alarmTime % 60));
    }
    else if (display == false && alarmTime >= 720) 
    {
      alarmTimeStr = String.format("%02d:%02d PM", (alarmTime / 60), (alarmTime % 60));
    }

    return alarmTimeStr;
  }

  /**
  * Returns current alarm time as number of minutes past midnight.
  * @return alarmTime
  */
  public int getAlarmTimeRaw () 
  {
    return alarmTime;
  }

  /**
  * Returns current clock time as a string in one of the following forms,
  * depending on whether the clock is currently in 24-hour mode: "hh:mm",
  * "hh:mm AM", or "hh:mm PM"
  * @return clockTimeString
  */
  public java.lang.String getClockTimeAsString () 
  {
    if (display == true) 
    {
      clockTimeStr = String.format("%02d:%02d", (clockTime / 60), (clockTime % 60));
    }
    else if (display == false && clockTime < 720) 
    {
      clockTimeStr = String.format("%02d:%02d AM", (clockTime / 60), (clockTime % 60));
    }
    else if (display == false && clockTime >= 720) 
    {
      clockTimeStr = String.format("%02d:%02d PM", ((clockTime / 60)-12), (clockTime % 60));
    }
    return clockTimeStr;
  }

  /**
  * Returns current clock time as number of minutes past midnight.
  * @return clockTime
  */
  public int getClockTimeRaw () 
  {
	 if (clockTime >= 1440) 
	 {
	    clockTime = clockTime % 60;
	 }
	 return clockTime;
  }

  /**
  * Returns current effective alarm time as a string in one of the
  * following forms, depending on whether the clock is currently in 24-hour
  * mode: "hh:mm", "hh:mm AM", or "hh:mm PM".
  * @return effectiveAlarmTimeString
  */
  public java.lang.String getEffectiveAlarmTimeAsString () 
  {

    if (display == true) 
    {
      effectiveAlarmTimeStr = String.format("%02d:%02d", (effectiveAlarmT / 60), (effectiveAlarmT % 60));
    }
    else if (display == false && clockTime < 720) 
    {
      effectiveAlarmTimeStr = String.format("%02d:%02d AM", (effectiveAlarmT / 60), (effectiveAlarmT % 60));
    }
    else if (display == false && clockTime >= 720) 
    {
      effectiveAlarmTimeStr = String.format("%02d:%02d PM", (effectiveAlarmT / 60), (effectiveAlarmT % 60));
    }

    return effectiveAlarmTimeStr;
  }

  /**
  * Returns effective alarm time as number of minutes past midnight. 
  * If snooze is not in effect, this value is the same as the current alarm time. 
  * This value is always between 0 and 1439, inclusive.
  * @return effectiveAlarmTime
  */
  public int getEffectiveAlarmTimeRaw () 
  {
	  if (effectiveAlarmT >= 1440) 
	  {
	  effectiveAlarmT = effectiveAlarmT % 1440;
	  } 
	  if (snooze == false) 
	  {
	  effectiveAlarmT = alarmTime;
	  }
	  return effectiveAlarmT;
  }

  /**
  * Determines whether alarm is currently buzzing. Returns true when
  * the alarm is buzzing and false when it is not.
  * @return true or false
  */
  public boolean isBuzzing () 
  {
    if (radio == false && sound == true) 
    {
      radio = false;
      return true;
    }
    else 
    {
      return false;
    }
  }

  /**
  * Determines whether alarm is currently playing radio. Returns true
  * the alarm is playing the radio and false when it is not.
  * @return true or false
  */
  public boolean isPlayingRadio () 
  {
    if (buzz == false && sound == true) 
    {
      buzz = false;
      return true;
    }
    else 
    {
      return false;
    }
  }

  /**
  * Determines whether alarm is currently sounding (buzzer or radio).
  * Returns true when it is playing a sound and false if not.
  * @return sound
  */
  public boolean isSounding () 
  {
    if (alarmOn == true && effectiveAlarmT == alarmTime) 
    {
      sound = true;
    }
    else 
    {
      sound = false;
    }
    return sound;
  }

  /**
  * @param use24HourDisplay
  * Sets whether clock should display time using 24-hour mode 
  * (00:00 to 23:59) 
  * or AM/PM (12:00 AM to 11:59 PM).
  */
  public void set24HourDisplay (boolean use24HourDisplay) 
  { 
	  display = use24HourDisplay;
  }

  /**
  * @param givenMinutesPastMidnight
  * Sets alarm time to given number of minutes past midnight. 
  * This method won't cause the clock to start sounding, 
  * and will not cause it to stop sounding if it is already in the sounding state. 
  * If snooze is in effect it will be canceled by this method.
  * hours and minutes are to be non-negative but may be arbitrarily large. 
  */
  public void setAlarmTime (int givenMinutesPastMidnight) 
  {
    alarmTime = givenMinutesPastMidnight % 1440;
    snooze = false;
    sound = false;
  }

  /**
  * @param givenHours
  * @param givenMinutes
  * @param isPM
  * Sets alarm time to given hours and minutes. 
  * hours are assumed to be in the range [1, 12] 
  * minutes are assumed to be in the range [0, 59]. 
  * This method will not cause the clock to start sounding, 
  * and will not cause it to stop sounding if it is already in the sounding state. 
  * If snooze is in effect, it is canceled by this method.
  * The given time is interpreted as AM or PM based on the third argument, 
  * regardless of whether the clock is currently in 24-hour mode. 
  */
  public void setAlarmTime (int givenHours, int givenMinutes, boolean isPM) 
  {

    if (isPM == true) 
    {
      alarmTimeStr = givenHours + ":" + givenMinutes + " PM";
    }
    if (isPM == false) 
    {
      alarmTimeStr = givenHours + ":" + givenMinutes + " AM";
    }
    snooze = false;
    sound = false;
  }

  /**
  * @param useRadio
  * Sets whether clock should play radio or buzzer when sounding
  * alarm.
  */
  public void setRadioMode (boolean useRadio) 
  {
 
	  if (useRadio == true) 
	  {
      buzz = false;
      radio = true;
	  }
	  if (useRadio == false) 
	  {
      buzz = true;
      radio = false;
	  }
  }

  /**
  * @param givenMinutesPastMidnight
  * is currently set, and will not cause it to stop sounding if it is already 
  * in the sounding state. 
  * If snooze is in effect, it is canceled by this method.
  * Sets the current clock time to the given number of minutes past midnight. 
  * The argument is assumed to be non-negative but may be arbitrarily large. 
  * This method will not cause the clock to start sounding, even if the alarm 
  */
  public void setTime (int givenMinutesPastMidnight) 
  {
	  clockTime = givenMinutesPastMidnight % 1440;
	  snooze = false;
	  sound = false;
  }

  /**
  * @param givenHours
  * @param givenMinutes
  * @param isPM
  * hours are assumed to be in range [1, 12] 
  * and minutes are assumed to be in range [0, 59]. 
  * given time is interpreted as AM or PM based on third argument, 
  * regardless of whether clock is currently in 24-hour mode. 
  * method will not cause clock to start sounding, 
  * even if alarm is currently set, and will not cause it 
  * to stop ringing if it is already in ringing state. 
  * If snooze is in effect, it is canceled by this method.
  * Sets the current clock time to given hours and minutes. 
  */
  public void setTime (int givenHours, int givenMinutes, boolean isPM) 
  {
    if (isPM == true) 
    {
      clockTimeStr = givenHours + ":" + givenMinutes + " PM";
    }
    if (isPM == false) 
    {
      clockTimeStr = givenHours + ":" + givenMinutes + " AM";
    }
    snooze = false;
    sound = false;
  }

  /**
  * Stops clock from sounding and sets effective alarm time 
  * for SNOOZE_MINUTES minutes after current clock time. 
  * Does not disable alarm or change alarm time. 
  * Does nothing if the alarm is not currently sounding.
  */
  public void snooze () 
  {
	  if (sound == true) {
		  sound = false;
		  snooze = true;
		  effectiveAlarmT += clockTime + SNOOZE_MINUTES;
	  }

	  
  }
}
