package com.epam.event.it;

public class AnotherTestObject {
    private final String anotherField1;
    private final String anotherField2;

    private AnotherTestObject() {
        anotherField1 = null;
        anotherField2 = null;
    }

    public AnotherTestObject(String anotherField1, String anotherField2) {
        this.anotherField1 = anotherField1;
        this.anotherField2 = anotherField2;
    }

    public String getAnotherField1() {
        return anotherField1;
    }

    public String getAnotherField2() {
        return anotherField2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnotherTestObject that = (AnotherTestObject) o;

        if (anotherField1 != null ? !anotherField1.equals(that.anotherField1) : that.anotherField1 != null)
            return false;
        return anotherField2 != null ? anotherField2.equals(that.anotherField2) : that.anotherField2 == null;
    }

    @Override
    public int hashCode() {
        int result = anotherField1 != null ? anotherField1.hashCode() : 0;
        result = 31 * result + (anotherField2 != null ? anotherField2.hashCode() : 0);
        return result;
    }
}
