package com.epam.event.consumer;

public class TestClass {

    private String fieldA;
    private String fieldB;

    public TestClass() {
    }

    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }

    public String getFieldB() {
        return fieldB;
    }

    public void setFieldB(String fieldB) {
        this.fieldB = fieldB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestClass testClass = (TestClass) o;

        if (fieldA != null ? !fieldA.equals(testClass.fieldA) : testClass.fieldA != null) return false;
        return fieldB != null ? fieldB.equals(testClass.fieldB) : testClass.fieldB == null;
    }

    @Override
    public int hashCode() {
        int result = fieldA != null ? fieldA.hashCode() : 0;
        result = 31 * result + (fieldB != null ? fieldB.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "fieldA='" + fieldA + '\'' +
                ", fieldB='" + fieldB + '\'' +
                '}';
    }
}
