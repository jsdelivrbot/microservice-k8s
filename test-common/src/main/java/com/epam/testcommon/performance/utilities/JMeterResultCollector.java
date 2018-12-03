package com.epam.testcommon.performance.utilities;

import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.util.Calculator;

public class JMeterResultCollector extends org.apache.jmeter.reporters.ResultCollector {

    private Calculator calculator;
    private long startTime;
    private long endTime;

    public JMeterResultCollector(Summariser summer) {
        super(summer);
        this.calculator = new Calculator();
    }

    @Override
    public void sampleOccurred(SampleEvent e) {
        super.sampleOccurred(e);
        SampleResult r = e.getResult();
        calculator.addSample(r);
    }

    @Override
    public void testStarted(String host) {
        super.testStarted(host);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void testEnded(String host) {
        super.testEnded(host);
        endTime = System.currentTimeMillis();
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}