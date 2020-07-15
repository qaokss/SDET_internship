package api.tests.helpers;

import api.tests.model.Fact;
import io.restassured.common.mapper.TypeRef;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class Routing {

    public static final String HTTPS_CAT_FACT = "https://cat-fact.herokuapp.com/facts";


    public static List<Fact> getAllFacts() {
        Map<String, List<Fact>> response = get(HTTPS_CAT_FACT).as(new TypeRef<Map<String, List<Fact>>>() {
        });
        return response.get("all");
    }
}
