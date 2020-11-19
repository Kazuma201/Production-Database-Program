/*
 * | Jose Alvarez    |
 * | SemesterProject |
 * | 10/01/2020      |
 * | ItemType.java   |
 */

//enumerated name
public enum ItemType {
  //variables (CAPs are common practice)
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  public String code;

  ItemType(String code) {
    this.code = code;
  }
  public String getCode()
  {
    return code;
  }

}
