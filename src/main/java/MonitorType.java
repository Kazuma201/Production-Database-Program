/*
 * | Jose Alvarez      |
 * | SemesterProject   |
 * | 10/01/2020        |
 * | MonitorType.java  |
 */
//Simple enum storing values
public enum MonitorType {
  LCD("LCD"),
  LED("LED");

  public String tvScr;

  MonitorType(String tv)
  {
    tvScr = tv;
  }
  public String getTvScr(){
    return tvScr;
  }
}
