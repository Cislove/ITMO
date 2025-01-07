package org.ifmo.laba4.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="records")
@NoArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Coordinate coordinate;
    @Column(name="in_area", nullable = false)
    private boolean isHit;
    @Column(name="start_time", nullable = false)
    private LocalDateTime createDateTime;
    @Column(name="processed_time", nullable = false)
    private long processDateTime;

    public Point(Coordinate coordinate, boolean inArea, LocalDateTime startTime) {
        this.coordinate = coordinate;
        this.isHit = inArea;
        this.createDateTime = startTime;
        this.processDateTime = Duration.between(startTime, LocalDateTime.now()).toMillis();
    }
}
