package org.ifmo.laba3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Point implements Serializable {
    @Column(name="x", nullable = false)
    private double x;
    @Column(name="y", nullable = false)
    private double y;
    @Column(name="r", nullable = false)
    private double r;
}
