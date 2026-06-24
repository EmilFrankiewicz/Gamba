package pl.emilfrankiewicz.games.roulette.domain;

public final class Amount {

    private final int amount;

    public Amount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be less than or equal to 0");
        }

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Amount amount1 = (Amount) o;
        return amount == amount1.amount;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}