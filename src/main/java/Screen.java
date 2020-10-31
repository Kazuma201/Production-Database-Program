public class Screen implements ScreenSpec{

  String resolution;
  int refreshRate;
  int responseTime;

  public Screen(String res, int refRate, int resTime){
    this.resolution = res;
    this.refreshRate = refRate;
    this.responseTime = resTime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }
  @Override
  public int getRefreshRate() {
    return refreshRate;
  }
  @Override
  public int getResponseTime() {
    return responseTime;
  }
  @Override
  public String toString() {
    return "Screen:" + "\n" + "Resolution : " + resolution + "\n" + "Refresh rate : "
        + refreshRate + "\n" + "Response time : " + responseTime;
  }

}
