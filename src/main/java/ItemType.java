//enumerated name
public enum ItemType {
//variables (CAPs are common practice)
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");
//String value in item type
  String code;
//
  ItemType(String c) {
    code = c;
    this.code = c;
  }

  public String getCode()
  {
    return this.code;
  }

  public static void main(String[] args) {
    for (ItemType it : ItemType.values()) {
      System.out.println(it + " " + it.code);
    }
  }
}