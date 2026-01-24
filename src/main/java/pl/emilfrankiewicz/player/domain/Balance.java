package pl.emilfrankiewicz.player.domain;

public class Balance {

    private final int amount;

    public Balance(int amount) {
        this.amount = amount;
    }

    public Balance increase(int amountToAdd) {
        return new Balance(amount + amountToAdd);
    }

    public Balance decrease(int amountToSubtract) {
        if (amount - amountToSubtract < 0) {
            throw new IllegalArgumentException("Amount cannot be less than 0");
        }
        return new Balance(amount - amountToSubtract);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Balance)) {
            return false;
        }
        Balance other = (Balance) obj;
        if (this.amount == other.amount) {
            return true;
        }
        return false;
    }
}

