package org.study.refactoringpractice.play;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TheaterTest {

    private Theater theater;

    @BeforeEach
    void setUp() {
        theater = new Theater();
    }

    @DisplayName("원하는 문자열이 출력되는 자가진단 테스트")
    @Test
    void testOne() {
        // Given
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

        String customerName = "BigCo";

        Invoice invoice = new Invoice(customerName,
                List.of(hamletPerformance, asYouLikeItPerformance, othelloPerformance));

        // When
        String result = theater.statement(invoice, plays);
        String expected = "statement for (customer: BigCo)\n" +
                "Hamlet: $650.00 (55 seats)\n" +
                "As You Like It: $580.00 (35 seats)\n" +
                "Othello: $500.00 (40 seats)\n" +
                "Total amount: $1,730.00\n" +
                "You earned: 47 points\n";

        // Then
        assertThat(result).isEqualTo(expected);
    }


}