package org.jio.fyoga.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyTotal {
    private Integer month;
    private double totalAmount;

    public MonthlyTotal(Integer month, double totalAmount) {
        this.month = month;
        this.totalAmount = totalAmount;
    }
}
