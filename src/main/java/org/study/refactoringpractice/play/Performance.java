package org.study.refactoringpractice.play;

import lombok.Getter;

@Getter
public class Performance {
    private String playID;
    private int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }
}
