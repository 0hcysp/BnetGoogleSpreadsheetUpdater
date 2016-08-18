import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.pojo.POJO.Armory;
        
        
public class SheetsQuickstart {
    
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Google Sheets API";
    public static final String SPREADSHEETS = "https://www.googleapis.com/auth/spreadsheets";
    /** Directory to store user credentials */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/sheets.googleapis.auth/spreadsheets");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes for the Sheets API
    */
    private static final List<String> SCOPES =
        Arrays.asList(SheetsScopes.SPREADSHEETS);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            SheetsQuickstart.class.getResourceAsStream("client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
        Sheets service = getSheetsService();

        // Prints spreadsheet data:
        String spreadsheetId = "15PrNFdM8rqrTHX_J07xycsxq3ApngXUPmLeRW88ZvJY";
        String range = "ItemLevel!A3";
        ValueRange response = service.spreadsheets().values()
            .get(spreadsheetId, range)
            .execute();
        
        List<List<Object>> values = response.getValues();
       /**
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } else {
          System.out.println("ItemLvl");
          for (List row : values) {     
             
        // READ // Print columns A and C, which correspond to indices 0 and 2.
        try {   
        System.out.printf("%s, %s\n", row.get(0), row.get(2));
        
          } catch (IndexOutOfBoundsException op)  {
              continue;
          }
        }
        }
        */
//Get info from bnet API 

    APIConnection APIConnection = new APIConnection();
     
      
    String json = APIConnection.getHTML("https://eu.api.battle.net/wow/character/Gordunni/Вмх?fields=items&locale=ru_RU&apikey=dutdqt7stzxq7uua5ryeqqsp9y3dbxxt"); //"{\"lastModified\":1471263936000,\"name\":\"Эммастоунх\",\"realm\":\"Гордунни\",\"battlegroup\":\"Vindication\",\"class\":6,\"race\":1,\"gender\":1,\"level\":100,\"achievementPoints\":10485,\"thumbnail\":\"gordunni/156/136154780-avatar.jpg\",\"calcClass\":\"d\",\"faction\":0,\"items\":{\"averageItemLevel\":748,\"averageItemLevelEquipped\":743,\"head\":{\"id\":124331,\"name\":\"Каска гнусного сосредоточения\",\"icon\":\"inv_raidpaladinmythic_p_01helm\",\"quality\":4,\"itemLevel\":735,\"tooltipParams\":{\"gem0\":127763,\"transmogItem\":118940,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":74,\"amount\":444},{\"stat\":36,\"amount\":410},{\"stat\":49,\"amount\":182},{\"stat\":7,\"amount\":667}],\"armor\":349,\"context\":\"raid-mythic\",\"bonusLists\":[565,567],\"artifactId\":0,\"displayInfoId\":144408,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":118940,\"itemAppearanceModId\":0}},\"neck\":{\"id\":124219,\"name\":\"Ожерелье презрительного превосходства\",\"icon\":\"inv_6_2raid_necklace_2c\",\"quality\":4,\"itemLevel\":740,\"tooltipParams\":{\"enchant\":5319,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":262},{\"stat\":32,\"amount\":204},{\"stat\":49,\"amount\":144},{\"stat\":7,\"amount\":393}],\"armor\":0,\"context\":\"raid-mythic\",\"bonusLists\":[567],\"artifactId\":0,\"displayInfoId\":0,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"enchantDisplayInfoId\":5319}},\"shoulder\":{\"id\":124344,\"name\":\"Наплечье демонического взора\",\"icon\":\"inv_shoulder_plate_raiddeathknightmythic_p_01\",\"quality\":4,\"itemLevel\":735,\"tooltipParams\":{\"gem0\":127763,\"set\":[124344,124317,124338,124327],\"transmogItem\":38663,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":333},{\"stat\":49,\"amount\":184},{\"stat\":36,\"amount\":260},{\"stat\":7,\"amount\":500}],\"armor\":322,\"context\":\"raid-mythic\",\"bonusLists\":[565,567],\"artifactId\":0,\"displayInfoId\":142382,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":38663,\"itemAppearanceModId\":0}},\"back\":{\"id\":124144,\"name\":\"Плащ обжигающей ярости\",\"icon\":\"inv_cape_felfireraid_d_03\",\"quality\":4,\"itemLevel\":751,\"tooltipParams\":{\"enchant\":5312,\"transmogItem\":134111,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":49,\"amount\":135},{\"stat\":32,\"amount\":251},{\"stat\":63,\"amount\":166},{\"stat\":4,\"amount\":290},{\"stat\":7,\"amount\":435}],\"armor\":100,\"context\":\"raid-mythic\",\"bonusLists\":[562,40,567],\"artifactId\":0,\"displayInfoId\":144319,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":134111,\"enchantDisplayInfoId\":5312,\"itemAppearanceModId\":0}},\"chest\":{\"id\":124317,\"name\":\"Бригантина демонического взора\",\"icon\":\"inv_chest_plate_raiddeathknightmythic_p_01\",\"quality\":4,\"itemLevel\":735,\"tooltipParams\":{\"set\":[124344,124317,124338,124327],\"transmogItem\":119470,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":444},{\"stat\":49,\"amount\":346},{\"stat\":32,\"amount\":245},{\"stat\":7,\"amount\":667}],\"armor\":429,\"context\":\"raid-mythic\",\"bonusLists\":[567],\"artifactId\":0,\"displayInfoId\":142371,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":119470,\"itemAppearanceModId\":0}},\"shirt\":{\"id\":98086,\"name\":\"Рубашка-полуфрак\",\"icon\":\"inv_shirt_black_01\",\"quality\":3,\"itemLevel\":1,\"tooltipParams\":{\"timewalkerLevel\":100},\"stats\":[],\"armor\":0,\"context\":\"quest-reward\",\"bonusLists\":[],\"artifactId\":0,\"displayInfoId\":11646,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{}},\"tabard\":{\"id\":52252,\"name\":\"Гербовая накидка Светоносного\",\"icon\":\"inv_shirt_guildtabard_01\",\"quality\":4,\"itemLevel\":80,\"tooltipParams\":{\"timewalkerLevel\":100},\"stats\":[],\"armor\":0,\"context\":\"quest-reward\",\"bonusLists\":[],\"artifactId\":0,\"displayInfoId\":65733,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{}},\"wrist\":{\"id\":124353,\"name\":\"Поцарапанные наручи Пролома\",\"icon\":\"inv_raidpaladinmythic_p_01bracer\",\"quality\":4,\"itemLevel\":740,\"tooltipParams\":{\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":74,\"amount\":262},{\"stat\":36,\"amount\":242},{\"stat\":49,\"amount\":107},{\"stat\":7,\"amount\":393}],\"armor\":191,\"context\":\"raid-mythic\",\"bonusLists\":[567],\"artifactId\":0,\"displayInfoId\":141850,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{}},\"hands\":{\"id\":124327,\"name\":\"Рукавицы демонического взора\",\"icon\":\"inv_glove_plate_raiddeathknightmythic_p_01\",\"quality\":4,\"itemLevel\":741,\"tooltipParams\":{\"set\":[124344,124317,124338,124327],\"transmogItem\":38667,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":352},{\"stat\":32,\"amount\":295},{\"stat\":36,\"amount\":174},{\"stat\":7,\"amount\":529}],\"armor\":273,\"context\":\"raid-mythic\",\"bonusLists\":[562,567],\"artifactId\":0,\"displayInfoId\":142386,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":38667,\"itemAppearanceModId\":0}},\"waist\":{\"id\":124349,\"name\":\"Латный пояс искоренителя\",\"icon\":\"inv_plate_raidwarriormythic_p_01belt\",\"quality\":4,\"itemLevel\":740,\"tooltipParams\":{\"gem0\":127761,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":64,\"amount\":199},{\"stat\":32,\"amount\":282},{\"stat\":74,\"amount\":349},{\"stat\":49,\"amount\":183},{\"stat\":7,\"amount\":524}],\"armor\":245,\"context\":\"raid-mythic\",\"bonusLists\":[565,43,567],\"artifactId\":0,\"displayInfoId\":141507,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{}},\"legs\":{\"id\":124338,\"name\":\"Ножные латы демонического взора\",\"icon\":\"inv_pant_plate_raiddeathknightmythic_p_01\",\"quality\":4,\"itemLevel\":735,\"tooltipParams\":{\"gem0\":127763,\"set\":[124344,124317,124338,124327],\"transmogItem\":38669,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":444},{\"stat\":49,\"amount\":220},{\"stat\":36,\"amount\":372},{\"stat\":7,\"amount\":667}],\"armor\":376,\"context\":\"raid-mythic\",\"bonusLists\":[565,567],\"artifactId\":0,\"displayInfoId\":142383,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":38669,\"itemAppearanceModId\":0}},\"feet\":{\"id\":124322,\"name\":\"Ботфорты осквернителя\",\"icon\":\"inv_raidpaladinmythic_p_01boot\",\"quality\":4,\"itemLevel\":745,\"tooltipParams\":{\"transmogItem\":38670,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":74,\"amount\":366},{\"stat\":36,\"amount\":348},{\"stat\":49,\"amount\":139},{\"stat\":7,\"amount\":548}],\"armor\":308,\"context\":\"raid-mythic\",\"bonusLists\":[567],\"artifactId\":0,\"displayInfoId\":141844,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":38670,\"itemAppearanceModId\":0}},\"finger1\":{\"id\":124199,\"name\":\"Петля призванных теней\",\"icon\":\"inv_6_2raid_ring_3a\",\"quality\":4,\"itemLevel\":740,\"tooltipParams\":{\"enchant\":5326,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":262},{\"stat\":32,\"amount\":249},{\"stat\":49,\"amount\":100},{\"stat\":7,\"amount\":393}],\"armor\":0,\"context\":\"raid-mythic\",\"bonusLists\":[567],\"artifactId\":0,\"displayInfoId\":0,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"enchantDisplayInfoId\":5326}},\"finger2\":{\"id\":124634,\"name\":\"Торасий, Каменное Сердце Дренора\",\"icon\":\"inv_60legendary_ring1c\",\"quality\":5,\"itemLevel\":795,\"tooltipParams\":{\"enchant\":5326,\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":437},{\"stat\":32,\"amount\":308},{\"stat\":49,\"amount\":263},{\"stat\":7,\"amount\":656}],\"armor\":0,\"context\":\"quest-reward\",\"bonusLists\":[649,641],\"artifactId\":0,\"displayInfoId\":0,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"enchantDisplayInfoId\":5326}},\"trinket1\":{\"id\":124241,\"name\":\"Проклятое перо Анзу\",\"icon\":\"ability_mount_pandarenkitemount_blue\",\"quality\":4,\"itemLevel\":730,\"tooltipParams\":{\"upgrade\":{\"current\":1,\"total\":2,\"itemLevelIncrement\":5},\"timewalkerLevel\":100},\"stats\":[{\"stat\":40,\"amount\":375}],\"armor\":0,\"context\":\"raid-mythic\",\"bonusLists\":[567],\"artifactId\":0,\"displayInfoId\":0,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{}},\"trinket2\":{\"id\":124236,\"name\":\"Неутолимый голод\",\"icon\":\"spell_deathknight_gnaw_ghoul\",\"quality\":4,\"itemLevel\":735,\"tooltipParams\":{\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":32,\"amount\":786}],\"armor\":0,\"context\":\"raid-mythic\",\"bonusLists\":[567],\"artifactId\":0,\"displayInfoId\":0,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{}},\"mainHand\":{\"id\":124360,\"name\":\"Рассекатель преисподней\",\"icon\":\"inv_axe_2h_felfireraid_d_01\",\"quality\":4,\"itemLevel\":746,\"tooltipParams\":{\"enchant\":3368,\"transmogItem\":118412,\"upgrade\":{\"current\":2,\"total\":2,\"itemLevelIncrement\":10},\"timewalkerLevel\":100},\"stats\":[{\"stat\":4,\"amount\":492},{\"stat\":32,\"amount\":412},{\"stat\":49,\"amount\":243},{\"stat\":7,\"amount\":738}],\"armor\":0,\"weaponInfo\":{\"damage\":{\"min\":2546,\"max\":3821,\"exactMin\":2546.0,\"exactMax\":3820.0},\"weaponSpeed\":3.6,\"dps\":884.1667},\"context\":\"raid-mythic\",\"bonusLists\":[562,567],\"artifactId\":0,\"displayInfoId\":143129,\"artifactAppearanceId\":0,\"artifactTraits\":[],\"relics\":[],\"appearance\":{\"itemId\":118412,\"enchantDisplayInfoId\":3368,\"itemAppearanceModId\":0}}},\"totalHonorableKills\":10446}"; 
    Gson gson = new GsonBuilder().create();
    Armory armory = gson.fromJson(json, Armory.class);
    System.out.println(armory.toString());
    String target = armory.toString();
   
          








// Update spreadsheet
	
        values.get(0).set(0, target);
        BatchUpdateValuesRequest buvr = new BatchUpdateValuesRequest();
        
        
        ValueRange item = new ValueRange();
        item.setRange("ItemLevel!C3:E");
        item.setValues(values);
        List<ValueRange> Sheet = new ArrayList<ValueRange>();
        Sheet.add(item);
        buvr.setData(Sheet);
        buvr.setValueInputOption("RAW");
        BatchUpdateValuesResponse Set = service.spreadsheets().values().batchUpdate(spreadsheetId, buvr).execute();
    }
}

