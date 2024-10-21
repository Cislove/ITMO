package org.example;
import java.util.Date;

public record Response(float x, float y, float r, boolean result, String requestTime, long processingTime) {
}