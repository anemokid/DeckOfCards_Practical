package com.example.niemawidaha.practical_deckofcards;

import android.content.Context;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niemawidaha.practical_deckofcards.model.ShuffledDeckJsonResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // Private members:
    private final static String LOG_TAG = "LOG_TAG: Main Activity";
    private TextView m_tv_NumberOfCardsRemaining;
    private Button m_btn_ShuffleNewDeck;
    private EditText m_et_SpecifiedCards;
    private Button m_btn_DrawCards;
    private int fullDeck = 52;


    // card deck values:
    // private List<CardModel> cardDeck;     // LIST OF CARD DECK
    private static String deck_id; // DECK ID
    private int currentCount = 52; // current count OF CARDS:
    private int numOfUsersValue; // USER SELECTED VALUE

    // Cards Remaining Formatting:
    private String displayCount = String.format("Cards Remaining: %s", currentCount); // String placeholder that updates the Cards Remaining text
    private String displayUserValueValidationError = String.format("There are only %s, cards remaining", currentCount); // String placeholder that updates the Cards Remaining text


    // CARD JSON RESPONSE MODELS:
    private ShuffledDeckJsonResponse shuffledDeck;

    // Recyler View Imports:

    // Networking Imports:
    public static final String SHUFFLE_CARD_DECK_BASE_URL = "https://deckofcardsapi.com/api/deck/new/shuffle/";
    public static final String deckOfCards_BASE_URL = "https://deckofcardsapi.com/api/deck/" + deck_id + "/draw/?";
    public String QUERY_PARAM = "count"; // Parameter for the search string




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup RV:

        // initViews:()
        initViews();

        // allows the use of building a URI:
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    TextView testJSON;

    /**
     * initViews()
     * this method will find all the views needed fro the main activity
     */
    public void initViews() {

        m_tv_NumberOfCardsRemaining = findViewById(R.id.tv_cards_remaining);
        m_btn_ShuffleNewDeck = findViewById(R.id.btn_shuffle_new_deck);
        m_et_SpecifiedCards = findViewById(R.id.et_draw_cards);
        m_btn_DrawCards = findViewById(R.id.btn_display_drawn_cards);

       testJSON = findViewById(R.id.test_json);

        setUpRV();
    } // ends initViews()

    /**
     * setUpRv()
     * this method will set the RV views and automatically adapt image views into the adapter
     */
    public void setUpRV() {
    } // ends setUpRv()

    public void shuffleNewDeck(View view) {

        // build query URI for ShuffleNewDeckJsonResponse::
        Uri builtURI = Uri.parse(SHUFFLE_CARD_DECK_BASE_URL)
                .buildUpon()
                .build();

        // convert the Card Deck URI to a string:
        String shuffledCardDeckURL = builtURI.toString();

        // CONNECT AND DOWNLOAD DATA:
        // pass this string into the downloadURL()
        String cardDeckResponse = null;

        // DOWNLOAD URL:
        try {

            cardDeckResponse = downloadUrl(shuffledCardDeckURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the JSON OBJECT: deck_id
        try {
            JSONObject fullData = new JSONObject(cardDeckResponse);


            // grab all the values from the JSON:
            deck_id = fullData.getString("deck_id");
            boolean success = fullData.getBoolean("success");
            int remaining = fullData.getInt("remaining");
            boolean shuffled = fullData.getBoolean("shuffled");

            // initialize your shuffledCardDeckJsonResponse model:
            shuffledDeck = new ShuffledDeckJsonResponse(success,deck_id, shuffled, remaining);

            testJSON.setText(deck_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    } // ends shuffleNewDeck()



    public void drawDeck(View view) {

        // If user passes Validation:
        // the value needed to draw is the numOfUserSelectedValue from the et_draw_cards:
        // grab the usr selected value for the deck of cards endpoint:

        boolean isUserValueValid;

        int numOfUserSelection = Integer.valueOf(m_et_SpecifiedCards.getText().toString());

        // if blank:
        // set an error: you must draw at least one card:
        if( m_et_SpecifiedCards.getText().toString() == "" ) {

            isUserValueValid = false; // values null and not true
            Toast.makeText(MainActivity.this, R.string.txt_error_user_value, Toast.LENGTH_LONG).show();
        }else if ( numOfUserSelection == 0) {

            // if 0:
            // set an error: You must draw atleast one card
            isUserValueValid = false; // values null and not true
            Toast.makeText(MainActivity.this, R.string.txt_error_user_value, Toast.LENGTH_LONG).show();

        } else if (numOfUserSelection> currentCount){

            // if the user input is greater than the number of cards remaining in the current deck:
            // set an error: There are only X, cards remaining
            // use currentCount to display:
            // String display User Validation error: use the users count:

            isUserValueValid = false;
            Toast.makeText(MainActivity.this, displayUserValueValidationError, Toast.LENGTH_LONG).show();

        } else {

            // if the value is valid then store the value selected by user in a global var:
            numOfUsersValue = numOfUserSelection;

            Toast.makeText(MainActivity.this, R.string.txt_valid_user_value, Toast.LENGTH_LONG).show();

            isUserValueValid = true;

        }

        // if the value if valid then
        // start a request:
        if ( isUserValueValid ){
            // do something:

            // Make an HTTP GET request: to num_cards:
            // grab the value of the deck_id for the endpoint:
            // make an HTTP GET request ot the "draw cards" API endpoint
            // https://deckofcardsapi.com/api/deck/{deck_id}/draw/?count={num_cards}
            // take the value and make an HTTP get request: query parameter count:
            // endpoint: https://deckofcardsapi.com/api/deck/{deck_id}/draw/?count={num_of_cards}

            Log.d(LOG_TAG, "drawDeck called! valid user input");
        }

        // delete the user input from the Edit text, clear input so hint is visibl
        m_et_SpecifiedCards.getText().clear();

        // close/ hide the keyboard:
        hideKeyboard();

    } // ends drawDeck()

    /**
     * hideKeyboard():
     */
    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * downloadURL():
     */
    public String downloadUrl(String cardDeckUrl) throws IOException {

        InputStream inputStream = null;

        // Only dislay the first 100 characters of the
        // web page content:
        int len = 100;


        HttpURLConnection connection = null;
        try {

            URL url = new URL(cardDeckUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);

            // Start the Query:
            connection.connect();

            int response = connection.getResponseCode();

            Log.d(LOG_TAG, "The response is" + response);

            inputStream = connection.getInputStream();

            // conver the input stream into a string
            String contentAsString = convertInputToString(inputStream, len);
            return contentAsString;

            // Close the InputStream and connectoin
        } finally {

            connection.disconnect();

            if (inputStream != null) {
                inputStream.close();
            }
        } // ends try-finally for url connection
    } // ends downloadUrl()

    /**
     * convertInputToString(): converts the InputStream to a string so tht
     * the activity can display it, the method uses an InputStreamReader instance
     * to read bytes and decode them into characters.
     */
    public String convertInputToString(InputStream stream, int length) throws IOException {

        Reader reader = null;

        reader = new InputStreamReader(stream, "UTF-8");

        char[] buffer = new char[length];

        reader.read(buffer);

        return new String(buffer);
    } // ends convertInputToString()
} // ends Main Activity
