package jmx;

public class Power implements PowerMBean{

    double exponential;

    double base;

    double result;

    public Power(double base) {
        this.base = base;
    }

    public Power() {
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setBase(double base) {
        this.base = base;
    }

    @Override
    public double getExponential() {
        return exponential;
    }

    @Override
    public void setExponential(double exponential) {
        this.exponential = exponential;
    }

    @Override
    public double power() {
        double result = Math.pow(base, exponential);
        setResult(result);
        return result;
    }
}
