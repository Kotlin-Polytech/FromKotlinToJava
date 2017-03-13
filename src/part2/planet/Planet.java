package part2.planet;

public enum Planet {
    MERCURY(3.302e+23, 2.439e+06),
    // ...
    EARTH(5.973e+24, 6.371e+06),
    // ...
    NEPTUNE(1.024e+26, 2.477e+07);
    private final double mass;
    private final double radius;
    private final double gravity;

    private static final double G = 6.67300e-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.gravity = G * mass / (radius * radius);
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getGravity() {
        return gravity;
    }

    public static void main(String[] args) {
        // Перебрать все планеты
        for (Planet p: Planet.values()) {
            // Numeric code of a planet
            System.out.println(p.ordinal());
        }
        // Planet by name
        Planet p = Planet.valueOf("EARTH");
        System.out.println(p.getMass());
        System.out.println(p.getRadius());
        System.out.println(p.getGravity());
    }
}
