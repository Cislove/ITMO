package org.ifmo.laba3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Named("tableRow")
@Table(name="records")
@SessionScoped
public class TableRow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Point point;
    @Column(name="inArea", nullable = false)
    private boolean inArea;
    @Column(name="startTime", nullable = false)
    private LocalDateTime startTime;
    @Column(name="processedTime", nullable = false)
    private long processedTime;

    public TableRow(Point point, boolean inArea, LocalDateTime startTime) {
        this.point = point;
        this.inArea = inArea;
        this.startTime = startTime;
        this.processedTime = Duration.between(startTime, LocalDateTime.now()).toMillis();
    }
}
