import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

interface CurrencyService {
    HashMap<String, BigDecimal> getRate() throws IOException, InterruptedException;
}