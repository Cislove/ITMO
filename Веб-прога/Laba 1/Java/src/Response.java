import java.util.Date;

public record Response(float x, float y, float r, boolean result, Date requestTime, long processingTime) {
}
