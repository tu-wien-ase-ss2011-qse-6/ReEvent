package reevent.domain;

public enum Rating {
    NEGATIVE(-1.0),
    NEUTRAL(0.0),
    POSITIVE(1.0);

    double value;

    Rating(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
