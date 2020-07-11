package api.tests;

import api.tests.helpers.ApiTestAnnotations.TestInfo;
import api.tests.model.Fact;
import api.tests.model.Name;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CatFactsTests {

    @Test
    @DisplayName("Получение всех фактов и проверка, что больше всего фактов написал Kasimir Schulz")
    @TestInfo(preconditions = "API: https://cat-fact.herokuapp.com/facts возвращает список фактов о животных \n",
            summary = "получаем все факты о животных \n" +
                    "ожидаем, что больше всего фактов написал Kasimir Schulz")
    public void animalFactsTest() {

        String response = get("https://cat-fact.herokuapp.com/facts").asString();

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        Map<String, List<Fact>> all = gson.fromJson(response, new TypeToken<Map<String, List<Fact>>>() {
        }.getType());

        Map<String, Name> user = new HashMap<>();
        Map<String, Integer> counter = new HashMap<>();

        List<Fact> allFacts = all.get("all");

        for (Fact fact : allFacts) {
            if (fact.getUser() != null) {

                user.put(fact.getUser().getId(), fact.getUser().getName());

                String userId = fact.getUser().getId();
                if (counter.get(userId) != null) {
                    counter.put(userId, counter.get(userId) + 1);

                } else counter.put(userId, 1);
            }

        }

        String idUserWithMaxFacts = counter.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
        String userWithMaxFacts = user.get(idUserWithMaxFacts).toString();

        assertEquals("Kasimir Schulz", userWithMaxFacts);

    }

}
