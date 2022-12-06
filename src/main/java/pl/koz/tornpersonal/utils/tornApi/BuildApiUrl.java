package pl.koz.tornpersonal.utils.tornApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BuildApiUrl {

    private final String apiDomain;
    private final String apiKey;

    public BuildApiUrl(@Value("${torn.api.url}") String apiDomain, @Value("${torn.api.koziolkuj}") String apiKey) {
        this.apiDomain = apiDomain;
        this.apiKey = apiKey;
    }

    public String buildUrl(String apiCategory, String apiSelection) {
        return apiDomain + apiCategory + "/?selections=" + apiSelection + "&key=" + apiKey;
    }

}
