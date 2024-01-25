import java.awt.Image;

public class Captcha {
    private Image challenge;
    private String solution;

    public boolean isSolutionCorrect(String solution) {
        return solution.equals(this.solution);
    }

    public Image getChallenge() {
        return challenge;
    }
}
