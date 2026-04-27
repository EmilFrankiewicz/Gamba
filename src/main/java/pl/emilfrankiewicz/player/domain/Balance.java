package pl.emilfrankiewicz.player.domain;

public class Balance {

    private final int amount;

    public Balance(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be less than 0");
        }
        this.amount = amount;
    }

    public Balance increase(int amountToAdd) {
        if (amountToAdd < 0) {
            throw new IllegalArgumentException("Amount to add cannot be less than 0");
        }
        return new Balance(amount + amountToAdd);
    }

    public Balance decrease(int amountToSubtract) {
        if (amountToSubtract < 0) {
            throw new IllegalArgumentException("Amount to subtract cannot be less than 0");
        }
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
        if (this == obj) return true;
        if (!(obj instanceof Balance)) return false;
        Balance other = (Balance) obj;
        return this.amount == other.amount;
    }
}