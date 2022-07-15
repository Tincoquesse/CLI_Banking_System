
class Person {
    String name;
    int age;
}

class MakingChanges {
    public static void changeIdentities(Person p1, Person p2) {
        int p1TempAge = p1.age;
        String p1TempName = p1.name;
        p1.age = p2.age;
        p1.name = p2.name;
        p2.age = p1TempAge;
        p2.name = p1TempName;
    }
}
