
class CodCurrency {
    public int get(Currency currency) {
        return switch (currency) {
            case USD -> 840;
            case EUR -> 978;
        };
    }
}