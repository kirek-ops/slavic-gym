package MMM.demo.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LocationFetcher {

    private static final String API_KEY = "AIzaSyCcGid1vTF4zEMmDMWgS5sX3fOxrAtGhDs";
    private static final String GEOCODING_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";

    public static Double[] getLatLng(String city, String street) throws Exception {
        // System.out.println("START GEO OBTAINING");

        OkHttpClient client = new OkHttpClient();

        String address = String.format("%s,%s", street, city);

        // System.out.println(address);
        // System.out.println(address.replace(" ", "+"));

        String url = String.format("%s?address=%s&key=%s", GEOCODING_API_URL, address.replace(" ", "+"), API_KEY);

        Request request = new Request.Builder()
            .url(url)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new Exception("Unexpected code " + response);

            String responseData = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();

            if (!jsonObject.get("status").getAsString().equals("OK")) {
                throw new Exception("Error from the API: " + jsonObject.get("status").getAsString());
            }

            JsonObject location = jsonObject.getAsJsonArray("results")
                .get(0).getAsJsonObject()
                .getAsJsonObject("geometry")
                .getAsJsonObject("location");

            Double latitude = location.get("lat").getAsDouble();
            Double longitude = location.get("lng").getAsDouble();

            return new Double[]{latitude, longitude};
        }
    }
}