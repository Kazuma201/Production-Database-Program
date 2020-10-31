/*
 * | Jose Alvarez     |
 * | SemesterProject  |
 * | 10/01/2020       |
 * | AudioPlayer.java |
 */

public class AudioPlayer extends Product implements MultimediaControl{
  //audio and playlist formats supported
  //
  private final String supportedAudioFormats;
  private final String supportedPlaylistFormats;


  //Parent constructor
  public AudioPlayer(String name, String manufacturer,
      String supportedAudioFormats, String supportedPlaylistFormats)
  {
    super(name,manufacturer,ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  // gets the supported audio formats
  public String getSupportedAudioFormats(){
    return supportedAudioFormats;
}
  // gets the supported playlists formats
  public String getSupportedPlaylistFormats(){
    return supportedPlaylistFormats;
}

  public int getId(int id) {
    return id;
  }

  @Override
  // play method,print a statement
  public void play(){
    System.out.println("Playing");
  }

  @Override
  // stop method, print a statement
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  // previous method, print a statement
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  // next method, print a statement
  public void next() {
      System.out.println("Next");
    }

  @Override
  public String toString(){
    return super.toString() + "\nSupported Audio Formats: "
        + getSupportedAudioFormats() +"\nSupported Playlist Formats: "
        + getSupportedPlaylistFormats();
  }
}
