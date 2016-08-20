
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
import com.pojo.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;


public class SheetsQuickstart {

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME
            = "Google Sheets API";
    public static final String SPREADSHEETS = "https://www.googleapis.com/auth/spreadsheets";
    /**
     * Directory to store user credentials
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/sheets.googleapis.auth/spreadsheets");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY
            = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes for the Sheets API
     */
    private static final List<String> SCOPES
            = Arrays.asList(SheetsScopes.SPREADSHEETS);

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
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in
                = SheetsQuickstart.class.getResourceAsStream("client_secret.json");
        GoogleClientSecrets clientSecrets
                = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow
                = new GoogleAuthorizationCodeFlow.Builder(
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
     *
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    public static int number = 3;
    
    public static void saveCharacterItemLevel(String character_name, String html_name) throws IOException 
{
	String html_request = "https://eu.api.battle.net/wow/character/Gordunni/"
		+ character_name + "?fields=items&locale=ru_RU&apikey=dutdqt7stzxq7uua5ryeqqsp9y3dbxxt";
	// Build a new authorized API client service.
	Sheets service = getSheetsService();

	// Prints spreadsheet data:
	String spreadsheetId = "1qUVcXXFcp9Zi6qr1Zj5HGbR-4u6MJGMZyXkie58Qehw";
	String range = "ItemLevel!A3";
	ValueRange response = null;
        try {
            response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
        } catch (IOException ex) {
            Logger.getLogger(SheetsQuickstart.class.getName()).log(Level.SEVERE, null, ex);
        }

	List<List<Object>> values = response.getValues();

	if (values == null || values.size() == 0) {
		System.out.println("No data found.");
	}
	else {
		//   System.out.println("ItemLvl");
		for (List row : values) {

			// READ // Print columns A and C, which correspond to indices 0 and 2.
			try {
				System.out.printf("%s, %s\n", row.get(0), row.get(2));

			}
			catch (IndexOutOfBoundsException op) {
				continue;
			}
		}
	}

	//Get info from bnet API
	APIConnection APIConnection = new APIConnection();

	String json = APIConnection.getHTML(html_request);
	Gson gson = new GsonBuilder().create();
	Armory armory = gson.fromJson(json, Armory.class);
	System.out.println(armory.toString());
	String target = armory.toString();

	// Update spreadsheet
	values.get(0).set(0, target);
	BatchUpdateValuesRequest buvr = new BatchUpdateValuesRequest();

	ValueRange item = new ValueRange();
	item.setRange(html_name);
	item.setValues(values);
	List<ValueRange> Sheet = new ArrayList<ValueRange>();
	Sheet.add(item);
	buvr.setData(Sheet);
	buvr.setValueInputOption("RAW");
        try {
            BatchUpdateValuesResponse Set = service.spreadsheets().values().batchUpdate(spreadsheetId, buvr).execute();
        } catch (IOException ex) {
            Logger.getLogger(SheetsQuickstart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
}
    public static void main(String[] args) throws IOException {
        //Creating array of users
        ArrayList<String> raiders = new ArrayList<String>();
        raiders.add("Даэска");
        raiders.add("Фордрейн");
        raiders.add("Арнбьорн");
        raiders.add("Квистя");
        raiders.add("Круська");
        raiders.add("Хорошаяидея");
        raiders.add("Рончи");
        raiders.add("Якоо");
        raiders.add("Даннериэль");
        raiders.add("Сэбастьян");
        raiders.add("Чудновскии");
        raiders.add("Страшилочка");
        raiders.add("Килила");
        raiders.add("Опохмелинка");
        raiders.add("Лайлет");
        raiders.add("Своии");
        raiders.add("Кериджейн");
        raiders.add("Хамочках");
        raiders.add("Хетэн");
        raiders.add("Лавстайл");
        raiders.add("Селинн");
        raiders.add("Тадароки");
        raiders.add("Морринт");
        raiders.add("Месьенитро");
        raiders.add("Меллания");
        raiders.add("Кальдорай");
        raiders.add("Нэрилим");
        raiders.add("Омикронс");
        raiders.add("Еракси");
        raiders.add("Найсилол");
        raiders.add("Мэнно");
        raiders.add("Стрим");
        raiders.add("Вмх");
        
        
        
              
        for (String raider:raiders) {
            String htmlName = "ItemLevel!C"+number+":E";
            saveCharacterItemLevel(raider, htmlName );
            number++;
       }
    }
}
