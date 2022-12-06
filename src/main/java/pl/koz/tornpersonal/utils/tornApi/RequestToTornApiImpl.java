package pl.koz.tornpersonal.utils.tornApi;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RequestToTornApiImpl implements RequestToTornApi {

    private final ReadJson readJson;
    private final BuildApiUrl buildApiUrl;

    public RequestToTornApiImpl(ReadJson readJson, BuildApiUrl buildApiUrl) {
        this.readJson = readJson;
        this.buildApiUrl = buildApiUrl;
    }

    @Override
    public JSONObject makeRequestToApi(String category, String apiSelection) {
        try {
            String requestUrl = this.buildApiUrl.buildUrl(category, apiSelection);
            return readJson.readJsonFromUrl(requestUrl);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
