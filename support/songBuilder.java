package support;
import java.io.*;
import java.util.*;
import ../SortedABList;

public class songBuilder
{
   public enum Planet {
      Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto, Ceres, Haumea, Makemake, Eris
   }
   
   public enum Animal {
      Cat, Dog, Bird, Mouse, Bear, Wolf, Lion, Tiger, Hyena, Dolphin, Whale, Worm, Ant, Bee, Wasp, Eagle, Falcon,   
      Pig, Donkey, Goat, Lamb, Sheep, Rabbit, Horse, Pony, Buffalo, Duck, Goose, Pigeon, Aardvark, Alpaca
   }
   
   public enum Title {
      Emperor, King, Duke, Count, Baron, Tsar, Kaiser, Basileus, Shahanshah, Pharaoh, Raja, Khan, Sultan, Prince,
      Queen, Empress, Duchess, Emir, Bey, Viscount, Esquire
   }
   
   public enum Adjective {
      Adamant, Adroit, Antic, Arcadian, Baleful, Bellicose, Caustic, Dilatory, Efficacious, Equananimous,
      Fulsome, Hubristic, Incendiary, Loquacious, Luminous, Mendacious, Pernicious, Petulant, Querulous, Zealous
   }

   public enum Verb {
      Sparkling, Thrilling, Exploding, Bursting, Cracking, Surging, Flooding, Snowballing, Skyrocketing,
      Rolling, Soaring, Catapulting, Shining, Scouring, Snapping, Guzzling, Stumbling, Wobbling, Swinging, 
      Lurching, Breezing, Gliding, Zipping, Sailing, Crashing, Sputtering, Spitting, Sprouting, Flourishing,
      Tackling, Grabbing, Grasping, Grappling, Poking, Stirring, Prodding, Knocking, Smashing, Plunging, Diving,
      Ducking, Lingering, Dropping, Trickling, Splashing, Seeping, Dumping, Draining, Squeezing, Dazzling, Sliding,
      Slumping, Tumbling, Toppling, Flying, Fixing, Blocking, Clogging, Muzzling, Stalling, Electrifying, Galvanizing
   }   
   
   public enum Adverb {
      Abnormally, Absentmindedly, Accidentally, Acidly, Adventurously, Angrily, Anxiously, 
      Arrogantly, Awkwardly, Badly, Bashfully, Beautifully, Bitterly, Bleakly, Blindly, Blissfully, Boastfully,
      Boldly, Bravely, Briefly, Brightly, Briskly, Broadly, Busily, Calmly, Carefully, Carelessly, Cautiously,
      Certainly, Cheerfully, Clearly, Cleverly, Closely, Colorfully, Commonly, Continually, Coolly, Correctly,
      Courageously, Crossly, Cruelly, Curiously, Daintily, Dearly, Deceivingly, Delightfully, Deeply, Defiantly,
      Deliberately, Diligently, Dimly, Doubtfully, Dreamily, Elegantly, Energetically, Enormously   
   } 
   
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      String filename;
      System.out.println("Enter the file name.");
      filename = keyboard.nextLine();
      
   
      System.out.println("Welcome to the playlist program. Please wait while the playlist is generated.");
      Random rand = new Random();
      SortedABList<Song> songList = new SortedABList<Song>(); //main list will hold all songs
      
      //100 artists, 10 albums, 11 songs per album
      for (int i = 0; i < 2; i++) //100 artists
      {
         //create artist by using random titles and adjectives
         Title[] titles = Title.values();
         int tempNumber = rand.nextInt(20);
         String tempTitle = titles[tempNumber].toString();
         Adjective[] adjectives = Adjective.values();
         tempNumber = rand.nextInt(19);
         String tempAdjective = adjectives[tempNumber].toString();
         String parArtist = tempTitle + " " + tempAdjective;
         
         for (int j = 0; j < 2; j++) //10 albums
         {
            //create album by using random values from enumerated planets and animals
            Planet[] planets = Planet.values();
            tempNumber = rand.nextInt(12);
            String tempPlanet = planets[tempNumber].toString();
            Animal[] animals = Animal.values();
            tempNumber = rand.nextInt(30);
            String tempAnimal = animals[tempNumber].toString();
            String parAlbum = tempPlanet + " " + tempAnimal;
            
            for (int z = 0; z < 2; z++) //11 songs per album
            {
               //create title by using random verbs and adverbs
               Verb[] verbs = Verb.values();
               tempNumber = rand.nextInt(74);
               String tempVerb = verbs[tempNumber].toString();
               Adverb[] adverbs = Adverb.values();
               tempNumber = rand.nextInt(55);
               String tempAdverb = adverbs[tempNumber].toString();
               String parTitle = tempVerb + " " + tempAdverb;
               
               //create song
               int parLength = 120000 + rand.nextInt(240000);
               Song newSong = new Song(parTitle, parArtist, parAlbum, parLength);
               
               //assign to queue
               songList.add(newSong);
            }
         }
      }
   }
}