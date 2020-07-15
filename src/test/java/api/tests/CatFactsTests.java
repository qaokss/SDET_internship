package api.tests;

import api.tests.helpers.TestInfo;
import api.tests.model.Fact;
import api.tests.model.Name;
import io.restassured.common.mapper.TypeRef;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CatFactsTests {


    @Ignore
    @Test
    @DisplayName("Получение всех фактов и проверка, что больше всего фактов написал Kasimir Schulz")
    @TestInfo(preconditions = "API: https://cat-fact.herokuapp.com/facts возвращает список фактов о животных \n",
            summary = "получаем все факты о животных \n" +
                    "ожидаем, что больше всего фактов написал Kasimir Schulz")

    public void animalFactsTest() {

        // получаем ответ и мапим его на класс Fact
        Map<String, List<Fact>> response = get("https://cat-fact.herokuapp.com/facts").as(new TypeRef<Map<String, List<Fact>>>() {
        });
        List<Fact> allFacts = response.get("all");

        // мапим юзер id с их именами
        Map<String, Name> userIdWithName = new HashMap<>();

        // перебираем значения в цикле, считаем кол-во fact для каждого userIdWithName
        Map<String, Integer> factsCounter = new HashMap<>();
        for (Fact fact : allFacts) {
            if (fact.getUser() != null) {
                userIdWithName.put(fact.getUser().getId(), fact.getUser().getName());
                String userId = fact.getUser().getId();
                factsCounter.merge(userId, 1, (a, b) -> a + b);
            }
        }
        // находим  id с максимальным кол-вом facts
        String idUserWithMaxFacts = factsCounter.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
        // для красоты записываем имя юзера в переменную
        String userWithMaxFacts = userIdWithName.get(idUserWithMaxFacts).toString();

        assertEquals("Kasimir Schulz", userWithMaxFacts);
    }

    @Test
    @DisplayName("Попытки упростить тест animalFactsTest используя stream()")
    public void attemptToSimplifyAnimalFactsTest() {


        Map<String, List<Fact>> response = get("https://cat-fact.herokuapp.com/facts").as(new TypeRef<Map<String, List<Fact>>>() {
        });
        List<Fact> allFacts = response.get("all");

        // для каждого имени считаем кол-во вхождений
        Map<Name, Long> collect = allFacts.stream().filter(fact -> fact.getUser() != null)
                .collect(
                        HashMap::new,
                        (hashMap, fact) -> hashMap.put(
                                fact.getUser().getName(),
                                allFacts
                                        .stream()
                                        .filter(f -> f.getUser() != null && f.getUser().getName().equals(fact.getUser().getName()))
                                        .count()
                        ),
                        HashMap::putAll
                );

        // выбираем юзера с максимальным значением value
        Name userWithMaxFacts = collect.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).get().getKey();

        Name kazimirName = new Name()
                .setFirstName("Kasimir")
                .setLastName("Schulz");

        assertEquals(kazimirName, userWithMaxFacts);

    }
}
