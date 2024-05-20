package TheCoinMachine.domain;

public class Result {
    private int rightPerson;
    private int leftPerson;

    public Result(int rightPerson, int leftPerson) {
        this.rightPerson = rightPerson;
        this.leftPerson = leftPerson;
    }

    public int getRightPerson() {
        return rightPerson;
    }

    public void setRightPerson(int rightPerson) {
        this.rightPerson = rightPerson;
    }

    public int getLeftPerson() {
        return leftPerson;
    }

    public void setLeftPerson(int leftPerson) {
        this.leftPerson = leftPerson;
    }
}
