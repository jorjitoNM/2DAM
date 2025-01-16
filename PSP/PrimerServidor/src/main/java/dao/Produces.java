package dao;

import net.datafaker.Faker;

public class Produces {
    @jakarta.enterprise.inject.Produces
    public Faker getFaker () {
        return new Faker();
    }
}
