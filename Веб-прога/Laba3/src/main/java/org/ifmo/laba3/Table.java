package org.ifmo.laba3;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Named("table")
@SessionScoped
public class Table implements Serializable {
    private ConnectionDB connection;
    private static final Logger logger = Logger.getLogger(Table.class.getName());
    private List<TableRow> points = new ArrayList<>();

    public List<TableRow> getPoints(){
        logger.info("Returning points for rendering: " + points.size());
        return this.connection.getAllRecords();
    }

    @PostConstruct
    public void init(){
        logger.info("PointList init");
    }

    public void addRow(TableRow row){
        this.connection.saveTableRow(row);
        logger.info("PointList addPoint: " + row.toString() + ", Total points: " + points.size());
    }
}
