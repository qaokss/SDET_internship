package api.tests.helpers;

import api.tests.model.Fact;

import java.util.List;

import static io.restassured.RestAssured.get;

public class Routing {

    public static final String HTTPS_CAT_FACT = "https://cat-fact.herokuapp.com/facts";


    public static List<Fact> getAllFacts() {
        return get(HTTPS_CAT_FACT).jsonPath().getList("all", Fact.class);
    }
}
