import java.io.*;
import java.util.*;

public class FinalProject
{ 
   public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      System.out.println("Welcome to the playlist program. Please wait while the playlist is generated.");
       
      SortedABList<Song> songList = new SortedABList<Song>(); //main list will hold all songs
      SortedABList<Song> filteredList = new SortedABList<Song>(); //filtered list hold subset of songs as specified
      
      //read file
      final String fileName = "songlist.dat";
      FileInputStream inStream = new FileInputStream(fileName);
      ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
      ArrayList<Song> readArrayList = (ArrayList<Song>) objectInputFile.readObject();
      
      //reassign to sortedABList
      while (readArrayList.isEmpty() == false)
      {
         Song addSong = readArrayList.remove(0);
         songList.add(addSong);
      }
      
      //while loop for user selection
      boolean done = false;
      Scanner keyboard = new Scanner(System.in);
      int orderChoice = 1; //playlist order set to "song title-ascending" by default
      boolean filterExists = false;
      
      while (done == false)
      { 
         //get user input
         int userChoice = 0;
         System.out.println("What would you like to do?\nEnter 1 to create and edit a filtered playlist."
         + "\nEnter 2 to edit songs in the list.\nEnter 3 to delete songs from the list.\nEnter 4 to view or reorder the full list"
         + "\nEnter 5 exit.");
         while (!keyboard.hasNextInt()) //validate input
         {
            System.out.println("Please enter 1 to view or edit a filtered playlist, 2 to edit a song, or 3 to delete a song.");  
         }
         userChoice = keyboard.nextInt();
         keyboard.nextLine();
         if (userChoice < 2 && userChoice > 5)
         {
            userChoice = 1;
         }
         
         if (userChoice == 1) //view or edit filtered playlist
         {
           int viewChoice = 0;
           while (viewChoice < 4)
            {
               System.out.println("What would you like to do?\nEnter 1 to view filtered playlist."
               + "\nEnter 2 to filter playlist."
               + "\nEnter 3 to clear all filters\nEnter 4 to return to main menu");
               while (!keyboard.hasNextInt()) //check for integer
               {
                  System.out.println("What would you like to do?\nEnter 1 to view filtered playlist."
                  + "\nEnter 2 to filter playlist."
                  + "\nEnter 3 to clear all filters\nEnter 4 to return to main menu"); 
               }
               viewChoice = keyboard.nextInt();
               while (viewChoice < 1 && viewChoice > 4)
               {
                  System.out.println("What would you like to do?\nEnter 1 to view filtered playlist."
                  + "\nEnter 2 to create a filtered playlist."
                  + "\nEnter 3 to clear all filters\nEnter 4 to return to main menu");
                  viewChoice = keyboard.nextInt();
               }
               keyboard.nextLine();
            
               if (viewChoice == 1)//view playlist
               {  
                  if (filteredList == null)
                  {
                     System.out.println("Filtered list has not been created.");
                  }
                  else if (filteredList.isEmpty())
                  {
                     System.out.println("Filtered list has not been created.");
                  } else
                  {
                     filteredList.printString();
                     System.out.println("");
                  }
               } else if (viewChoice == 2) //filter playlist
               {
                  int filterChoice = 0;
                  System.out.println("Enter 1 to filter by artist, or 2 to filter by album.");
                  while (!keyboard.hasNextInt())
                  {
                     System.out.println("Enter 1 to filter by artist, 2 to filter by album, or 3 to remove all filters.");

                  }
                     filterChoice = keyboard.nextInt();
                     keyboard.nextLine();
                     if (filterChoice == 1) //filter by artist
                     {
                        String artistChoice = "";
                        System.out.println("Enter name of artist.");
                        artistChoice = keyboard.nextLine();
                        if (filterExists == false)
                        //create a new list using correct comparator, then filter
                        {
                           int numberSongs = songList.size() - 1;
                           SortedABList<Song> tempList = new SortedABList<Song>(Song.artistComparator());
                           while (numberSongs >= 0)
                           {
                              Song tempSong = songList.get(numberSongs);
                              tempList.add(tempSong);
                              numberSongs--;
                           }
                           boolean allRetrieved = false;
                           while (allRetrieved != true)
                           {
                              Song tempSong = new Song("", artistChoice, "", 0);
                              int songIndex = tempList.indexOf(tempSong);
                              if (songIndex != -1)
                              {
                                 Song tempSong2 = tempList.remove(songIndex);
                                 filteredList.add(tempSong2);
                              } else
                              {
                                 allRetrieved = true;
                              }
                           }
                           if (filteredList.isEmpty() == false)
                           {
                              filterExists = true;
                           } else
                           {
                              System.out.println("Artist not found.");
                           }
                        } else if (filterExists == true)
                        //create a new list using correct comparator, then swap to that new list, then filter
                        {
                           SortedABList<Song> tempList1 = new SortedABList<Song>(Song.artistComparator());
                           int numberSongs = filteredList.size() - 1;
                           while (numberSongs >= 0)
                           {
                              Song tempSong = filteredList.remove(numberSongs);
                              tempList1.add(tempSong);
                              numberSongs--;
                           }
                           boolean allRetrieved = false;
                           SortedABList<Song> tempList2 = new SortedABList<Song>(Song.artistComparator());
                           while (allRetrieved != true)
                           {
                              Song tempSong = new Song("", artistChoice, "", 0);
                              int songIndex = tempList1.indexOf(tempSong);
                              if (songIndex != -1)
                              {
                                 Song tempSong2 = tempList1.remove(songIndex);
                                 tempList2.add(tempSong2);
                              } else
                              {
                                 allRetrieved = true;
                              }
                              filteredList = tempList2;
                           }
                        }
                     } else if (filterChoice == 2) //filter by album
                     {
                        String albumChoice = "";
                        System.out.println("Enter name of album.");
                        albumChoice = keyboard.nextLine();
                        if (filterExists == false)
                        {
                           int numberSongs = songList.size() - 1;
                           SortedABList<Song> tempList = new SortedABList<Song>(Song.albumComparator());
                           while (numberSongs >= 0)
                           {
                              Song tempSong = songList.get(numberSongs);
                              tempList.add(tempSong);
                              numberSongs--;
                           }
                           boolean allRetrieved = false;
                           while (allRetrieved != true)
                           {
                              Song tempSong = new Song("", "", albumChoice, 0);
                              int songIndex = tempList.indexOf(tempSong);
                              if (songIndex != -1)
                              {
                                 Song tempSong2 = tempList.remove(songIndex);
                                 filteredList.add(tempSong2);
                              } else
                              {
                                 allRetrieved = true;
                              }
                           }
                           if (filteredList.isEmpty() != true)
                           {
                              filterExists = true;
                           } else
                           {
                              System.out.println("Album not found.");
                           }
                        } else if (filterExists == true)
                        {
                           SortedABList<Song> tempList1 = new SortedABList<Song>(Song.albumComparator());
                           int numberSongs = filteredList.size() - 1;
                           while (numberSongs >= 0)
                           {
                              Song tempSong = filteredList.remove(numberSongs);
                              tempList1.add(tempSong);
                              numberSongs--;
                           }
                           boolean allRetrieved = false;
                           SortedABList<Song> tempList2 = new SortedABList<Song>(Song.albumComparator());
                           while (allRetrieved != true)
                           {
                              Song tempSong = new Song("", "", albumChoice, 0);
                              int songIndex = tempList1.indexOf(tempSong);
                              if (songIndex != -1)
                              {
                                 Song tempSong2 = tempList1.remove(songIndex);
                                 tempList2.add(tempSong2);
                              } else
                              {
                                 allRetrieved = true;
                              }
                              filteredList = tempList2;
                           }
                        }
                     }
               } else if (viewChoice == 3)
               {
                  filteredList = new SortedABList<Song>();
                  filterExists = false;
                  System.out.println("Filter removed.");
               }
          }
         } else if (userChoice == 2) //edit song
         {
            System.out.println("Enter 1 to edit title,\nenter 2 to edit artist,\nenter 3 to edit album,"
            + "\nenter 4 to return to main menu.");
            int editChoice = 0;
            while (!keyboard.hasNextInt())
            {
               System.out.println("Enter 1 to edit title,\nenter 2 to edit artist,\nenter 3 to edit album,"
               + "\nenter 4 to return to main menu.");  
            }
            editChoice = keyboard.nextInt();
            keyboard.nextLine();
            if (editChoice != 2 && editChoice != 3 && editChoice != 1)
            {
               editChoice = 4;
            }  
            if (editChoice >= 1 && editChoice <= 3)
            //create new song based on user specifications
            {
               System.out.println("Please enter information about the song to be edited.");
               System.out.println("Enter the song title.");
               String songChoice = keyboard.nextLine();
               System.out.println("Enter the artist.");
               String artistChoice = keyboard.nextLine();
               System.out.println("Enter the album.");
               String albumChoice = keyboard.nextLine();
               Song addSong = new Song(songChoice, artistChoice, albumChoice, 0);
               //find the song
               int songNumber = songList.indexOf(addSong);
               if (songNumber == -1)
               {
                  System.out.println("Song not found. Returning to main menu.");
                  editChoice = 4;
               } else
               {
                  int songLength = songList.get(songNumber).getLength();
                  addSong.setLength(songLength);
               }
               if (editChoice == 1)
               {
                  System.out.println("Please enter the new title.");
                  String editTitle = keyboard.nextLine();
                  songList.remove(songNumber);
                  addSong.setTitle(editTitle);
                  songList.add(addSong);
                  System.out.println("Song edited. Returning to main menu.");
               } else if (editChoice == 2)
               {
                  System.out.println("Please enter the new artist.");
                  String editArtist = keyboard.nextLine();
                  songList.remove(addSong);
                  addSong.setTitle(editArtist);
                  songList.add(addSong);
                  System.out.println("Song edited. Returning to main menu.");
               } else if (editChoice == 3)
               {
                  System.out.println("Please enter the new album.");
                  String editAlbum = keyboard.nextLine();
                  songList.remove(addSong);
                  addSong.setTitle(editAlbum);
                  songList.add(addSong);
                  System.out.println("Song edited. Returning to main menu.");
               }
            }
         } else if (userChoice == 3) //delete song
         {
            System.out.println("Please enter information about the song to be deleted.");
            System.out.println("Enter the song title.");
            String songChoice = keyboard.nextLine();
            System.out.println("Enter the artist.");
            String artistChoice = keyboard.nextLine();
            System.out.println("Enter the album.");
            String albumChoice = keyboard.nextLine();
            Song removeSong = new Song(songChoice, artistChoice, albumChoice, 0);
            int songNumber = songList.indexOf(removeSong);
            if (songNumber == -1)
            {
               System.out.println("Song not found. Returning to main menu.");
            } else
            {
               System.out.println("Song deleted. Returning to main menu.");
               songList.remove(songNumber);
            }
         } else if (userChoice == 4) //view or reorder playlist 
         {
            int listView = 0;
            while (listView < 3)
               {
                  System.out.println("Enter 1 to print out the entire playlist,\nEnter 2 to reorder playlist, or"
                  + "\nEnter 3 to return to main menu");
                  while (!keyboard.hasNextInt())
                  {
                     System.out.println("Enter 1 to print out the entire playlist,\nEnter 2 to reorder playlist, or"
                  + "\nEnter 3 to return to main menu");  
                  }  
                  listView = keyboard.nextInt();
                  
                  if (listView < 1 && listView > 2)
                  {
                     listView = 3;
                  }
                  
                  if (listView == 1)
                  {
                     songList.printString();
                     System.out.println("");
                  } else if (listView == 2)
                  {
                     int reorderChoice = 0;
                     System.out.println("How would you like to order the playlist?\nEnter 1 to order by song title descending, \n2 to "
                     + "order by song title ascending,\n3 to order by song length descending,\nor 4 to order by song length ascending");
                     while (!keyboard.hasNextInt())
                     {
                        System.out.println("Enter 1 to order by song title descending,\n2 to "
                     + "order by song title ascending,\n3 to order by song length descending,\n or 4 to order by song length ascending");  
                     }
                        reorderChoice = keyboard.nextInt();
                        keyboard.nextLine();
                        if (reorderChoice != 2 && reorderChoice != 3 && reorderChoice != 4)
                     {
                        reorderChoice = 1;
                     }
                     
                     int numberSongs = songList.size() - 1;
                     if (reorderChoice == 1)
                     {
                        System.out.println("Reordering playlist by song title descending.");
                        orderChoice = 2;
                        SortedABList<Song> tempList = new SortedABList<Song>(Song.titleDescComparator());
                        while (numberSongs >= 0)
                        {
                           Song tempSong = songList.remove(numberSongs);
                           tempList.add(tempSong);
                           numberSongs--;
                        }
                        songList = tempList;
                     } else if (reorderChoice == 2)
                     {
                        System.out.println("Reordering playlist by song title ascending.");
                        orderChoice = 1;
                        SortedABList<Song> tempList = new SortedABList<Song>();
                        while (numberSongs >= 0)
                        {
                           Song tempSong = songList.remove(numberSongs);
                           tempList.add(tempSong);
                           numberSongs--;
                        }
                        songList = tempList;
                     } else if (reorderChoice == 3)
                     {
                         System.out.println("Reordering playlist by song length descending.");
                         orderChoice = 3;
                         SortedABList<Song> tempList = new SortedABList<Song>(Song.lengthDescComparator());
                         while (numberSongs >= 0)
                         { 
                           Song tempSong = songList.remove(numberSongs);
                           tempList.add(tempSong);
                           numberSongs--;
                         }
                        songList = tempList;
                     } else //reorderChoice4
                     {
                        System.out.println("Reordering playlist by song length ascending.");
                        orderChoice = 4;
                        SortedABList<Song> tempList = new SortedABList<Song>(Song.lengthAscComparator());
                        while (numberSongs >= 0)
                        {
                           Song tempSong = songList.remove(numberSongs);
                           tempList.add(tempSong);
                           numberSongs--;
                        }
                        songList = tempList;
                     }   
                }
            }
         } else
         {
            System.out.println("Saving updated playlist.");
            while (songList.isEmpty() == false)
            {
               Song newSong = songList.remove(0);
               readArrayList.add(newSong);
            }
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputFile.writeObject(readArrayList);
            System.out.println("Program exited. Happy Holidays.");
            done = true;
         }
      }
   }
}