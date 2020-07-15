package api.tests;

import api.tests.helpers.TestInfo;
import api.tests.model.Fact;
import api.tests.model.Name;
import api.tests.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static api.tests.helpers.Routing.getAllFacts;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CatFactsTests {


    @Test
    @DisplayName("Получение всех фактов и проверка, что больше всего фактов написал Kasimir Schulz")
    @TestInfo(preconditions = "API: https://cat-fact.herokuapp.com/facts возвращает список фактов о животных \n",
            summary = "получаем все факты о животных \n" +
                    "ожидаем, что больше всего фактов написал Kasimir Schulz")
    public void simplifyAnimalFactsTest() {

        List<Fact> allFacts = getAllFacts();

        // для каждого имени считаем кол-во вхождений
        Map<User, Long> groupingFacts = allFacts.stream().filter(f -> f.getUser() != null).collect(Collectors.groupingBy(Fact::getUser, Collectors.counting()));

        // выбираем юзера с максимальным значением value
        Name userWithMaxFacts = groupingFacts.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).get().getKey().getName();

        Name kazimirName = new Name("Kasimir", "Schulz");

        assertEquals(kazimirName, userWithMaxFacts);

    }


}
