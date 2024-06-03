package org.study.refactoringpractice.play;

import lombok.Getter;

import java.util.List;
@Getter
public class Invoice {
    private String customerName;
    private List<Performance> performances;

    public Invoice(String customerName, List<Performance> performances) {
        this.customerName = customerName;
        this.performances = performances;
    }
}
