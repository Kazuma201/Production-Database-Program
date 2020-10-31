/*
 * | Jose Alvarez    |
 * | SemesterProject |
 * | 10/21/2020      |
 * | MoviePlayer.java   |
 */
public class MoviePlayer extends Product implements MultimediaControl {
  // Data fields
  private Screen screen;
  private MonitorType monitorType;

  // Constructor MoviePlayer
  public MoviePlayer(String name, String manufacturer, Screen scr,
      MonitorType monType)
  {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = scr;
    this.monitorType = monType;

  }

  // Getters and Setters :p
  public Screen getScreen() {
    return screen;
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
  }

  public MonitorType getMonitorType() {
    return monitorType;
  }

  public void setMonitorType(MonitorType monitorType) {
    this.monitorType = monitorType;
  }

  @Override
  // play method,print a statement
  public void play(){
    System.out.println("Playing movie");
  }

  @Override
  // stop method, print a statement
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  // previous method, print a statement
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  // next method, print a statement
  public void next() {
    System.out.println("Next movie");
  }

  @Override
  public String toString() {
    return super.toString() + "Screen: " + getScreen() + "Monitor Type" +
        getMonitorType();
  }

}
