import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class BankResponse {

    public HashMap<String, BigDecimal> getCurrency(ChoiceBank choiceBank) throws IOException, InterruptedException {
        return switch (choiceBank) {
            case NBU -> new NBU().getRate();
            case PB -> new PrivatBank().getRate();
            case Monobank -> new Monobank().getRate();
        };
    }
}