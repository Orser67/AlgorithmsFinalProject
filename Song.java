import java.util.Comparator;
import java.io.Serializable;

public class Song implements Comparable<Song>, Serializable
{
   //fields
   private String title;
   private String artist;
   private String album;
   private int length; //length in milliseconds
   private String path;
   
   //constructors
   public Song()
   {
      title = null;
      artist = null;
      album = null;
      length = 0;
      path = null;
   }
   
   public Song(String parTitle, String parArtist, String parAlbum, int parLength)
   {
      title = parTitle;
      artist = parArtist;
      album = parAlbum;
      length = parLength;
      path = "C:\\music\\" + parArtist + "\\" + parTitle + ".mp4";
   }
   
   //setters
   public void setTitle(String parTitle)
   {
      title = parTitle;
   }
   
   public void setArtist(String parArtist)
   {
      artist = parArtist;
   }
   
   public void setAlbum(String parAlbum)
   {
      album = parAlbum;
   }
   
   public void setLength(int parLength)
   {
      length = parLength;
   }
   
   public void setPath(String parPath)
   {
      path = parPath;
   }
   
   //getter methods
   public String getTitle()
   {
      return title;
   }
   
   public String getArtist()
   {
      return artist;
   }
   
   public String getAlbum()
   {
      return album;
   }
   
   public int getLength()
   {
      return length;
   }
   
   public String getPath()
   {
      return path;
   }
   
   public String toString()
   {
      String retString;
      retString = "\nTitle: " + title + ", Artist: " + artist
      + "\nAlbum: " + album + ", Length: " + length/1000 + " seconds"
      + "\nPath: " + path;
      return retString;
   }
   
   @Override 
   public boolean equals(Object obj)
   // Returns true if 'obj' is a Song with the same title, artist, and album
   // otherwise returns false. Note that it does not compare song length.
   {
      if (obj == this)
         return true;
      else 
      if (obj == null || obj.getClass() != this.getClass())
         return false;
      else
      {
         Song fp = (Song) obj; 
         return (this.title.equals(fp.title) && this.artist.equals(fp.artist) 
         && this.album.equals(fp.album) && this.length == fp.length); 
      }
   }
   
   public int compareTo (Song other)
   // Precondition: 'other' is not null
   // Compares this Song with 'other' for order. Returns a positive integer, zero, or a negative integer 
   // as this object is less than, equal to, or greater than 'other'.
   {
    
     if (this.equals(other) == true)
     {
       return 0;
     } else
     {
       if (!this.title.equals(other.title))
       {
          return this.title.compareTo(other.title);
       } else if (!this.artist.equals(other.artist))
       {
          return this.artist.compareTo(other.artist);
       } else 
       {
          return this.album.compareTo(other.album);
       }
     }
   }
   
   public static Comparator<Song> lengthAscComparator()
   {
     return new Comparator<Song>()
     {
        public int compare(Song element1, Song element2)
        {
          return (element1.length - element2.length);
        }
     };
   }
   
   public static Comparator<Song> lengthDescComparator()
   {
     return new Comparator<Song>()
     {
        public int compare(Song element1, Song element2)
        {
          return (element2.length - element1.length);
        }
     };
   }
   
   public static Comparator<Song> titleDescComparator()
   {
     return new Comparator<Song>()
     {
        public int compare(Song element1, Song element2)
        {
          return element2.title.compareTo(element1.title);
        }
     };
   }
   
   public static Comparator<Song> artistComparator()
   {
     return new Comparator<Song>()
     {
        public int compare(Song element1, Song element2)
        {
          return element2.artist.compareTo(element1.artist);
        }
     };
   }
   
   public static Comparator<Song> albumComparator()
   {
     return new Comparator<Song>()
     {
        public int compare(Song element1, Song element2)
        {
          return element2.album.compareTo(element1.album);
        }
     };
   }    
}