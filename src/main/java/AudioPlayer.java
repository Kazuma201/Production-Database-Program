

public class AudioPlayer extends Product implements MultimediaControl{
  //audio and playlist formats supported
  //
  private final String supportedAudioFormats;
  private final String supportedPlaylistFormats;


  //Parent constructor
  public AudioPlayer(String name, String manufacturer,
      String supportedAudioFormats, String supportedPlaylistFormats)
  {
    super(name,ItemType.AUDIO.code, manufacturer);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;

  }
  //Child constructor
  public AudioPlayer(int id, String name, String manufacturer,
      String supportedAudioFormats, String supportedPlaylistFormats)
  {
    super(id, name, ItemType.AUDIO.code, manufacturer);
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
    System.out.println("Start playing!");
  }

  @Override
  // stop method, print a statement
  public void stop() {
    System.out.println("Stop playing!");
  }

  @Override
  // previous method, print a statement
  public void previous() {
    System.out.println("Previous song!");
  }

  @Override
  // next method, print a statement
  public void next() {
      System.out.println("Next song!");
    }

  @Override
  public String toString(){
    return super.toString() + "Supported Audio Formats"
        + getSupportedAudioFormats() +"Supported Playlist Formats"
        + getSupportedPlaylistFormats();
  }
}
