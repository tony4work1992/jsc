package smartosc.jsc.applications.etl.utils.seed_data;

public class Person {
    public String name;
    public int age;
    public String gender;

    // Constructor
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
//    // Getter và Setter
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(int age) {
//        this.gender = gender;
//    }
    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\", \"age\":\"" + age + "\", \"gender\":\"" + gender + "\"}";
    }


}
