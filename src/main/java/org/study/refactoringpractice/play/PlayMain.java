package org.study.refactoringpractice.play;

import java.util.List;
import java.util.Map;

public class PlayMain {

    public static void main(String[] args) {

        Play hamlet = new Play("hamlet", "Hamlet", "tragedy");
        Play asYouLikeIt = new Play("as-like", "As You Like It", "comedy");
        Play othello = new Play("othello", "Othello", "tragedy");

        Performance hamletPerformance = new Performance("hamlet", 55);
        Performance asYouLikeItPerformance = new Performance("as-like", 35);
        Performance othelloPerformance = new Performance("othello", 40);

        Map<String, Play> plays = Map.of(
                "hamlet", hamlet,
                "as-like", asYouLikeIt,
                "othello", othello
        );

        Theater theater = new Theater(plays);

        Invoice invoice = new Invoice("BigCo",
                List.of(hamletPerformance, asYouLikeItPerformance, othelloPerformance));

        String result = theater.statement(invoice);
        System.out.println(result);
    }
}
