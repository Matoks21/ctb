import lombok.Data;

@Data
class JsonPB {
    private Currency ccy;
    private Currency base_ccy;
    private float buy;
    private float sale;
}