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
            Play play = plays.get(performance.getPlayID());
            int thisAmount = 0;

            switch (play.getType()) {
                case "tragedy": // 비극
                    thisAmount = 40_000;
                    if (performance.getAudience() > 30) {
                        thisAmount += 1000 * (performance.getAudience() - 30);
                    }
                    break;
                case "comedy": // 희극
                    thisAmount = 30_000;
                    if (performance.getAudience() > 20) {
                        thisAmount += 10_000 + 500 * (performance.getAudience() - 20);
                    }
                    thisAmount += 300 * performance.getAudience();
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unknown genre: %s", play.getType()));
            }
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
}
