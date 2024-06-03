package org.study.refactoringpractice.play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class Theater {

    public String statement(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("statement for (customer: %s)\n", invoice.getCustomerName());
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.getPerformances()) {
            Play play = playFor(plays, performance);
            int thisAmount = amountFor(performance, play);

            // 포인트를 적립한다.
            volumeCredits += Math.max(performance.getAudience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }

            // 청구 내역을 출력한다.
            ;
            result += String.format("%s: %s (%d seats)\n", play.getName(), currencyFormatter.format(thisAmount / 100), performance.getAudience());
            totalAmount += thisAmount;
        }
        result += String.format("Total amount: %s\n", currencyFormatter.format(totalAmount / 100));
        result += String.format("You earned: %s points\n", volumeCredits);
        return result;

    }

    private static Play playFor(Map<String, Play> plays, Performance performance) {
        return plays.get(performance.getPlayID());
    }

    private int amountFor(Performance aPerformance, Play play) {
        int result = 0;
        switch (play.getType()) {
            case "tragedy": // 비극
                result = 40_000;
                if (aPerformance.getAudience() > 30) {
                    result += 1000 * (aPerformance.getAudience() - 30);
                }
                break;
            case "comedy": // 희극
                result = 30_000;
                if (aPerformance.getAudience() > 20) {
                    result += 10_000 + 500 * (aPerformance.getAudience() - 20);
                }
                result += 300 * aPerformance.getAudience();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown genre: %s", play.getType()));
        }
        return result;
    }


}
