package pl.koz.tornpersonal.utils.tornApi;

import org.json.JSONObject;

public interface RequestToTornApi {

    JSONObject makeRequestToApi(String category, String apiSelection);

}
