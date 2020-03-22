package de.viadee.planningproblem;

import java.awt.geom.Point2D;

public class Bestellung {

    int requestId;

    int setId;

    double basketValue;

   private Point2D coordinate;


    // TODO: Testdaten erklären und entsprechend Klasse korrigieren
    public Bestellung(int requestId, int setId, double basketValue, Point2D coordinate) {
    this.requestId = requestId;
    this.setId = setId;
    this.basketValue = basketValue;
    this.coordinate = coordinate;
    }

}
