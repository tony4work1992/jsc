package smartosc.jsc.applications.etl.utils.seed_data;

import java.util.ArrayList;
import java.util.List;

public class SeedData {
    public static List<Person> generateSeedData() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Hong", 25, "Female"));
        people.add(new Person("Dat", 26, "Male"));
        people.add(new Person("Chi", 30, "Female"));
        people.add(new Person("Nam", 24, "Male"));

        return people;
    }

}
