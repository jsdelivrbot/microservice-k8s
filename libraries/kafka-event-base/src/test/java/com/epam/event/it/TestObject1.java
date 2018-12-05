package com.epam.event.it;

public class TestObject1 {
    private final String field1;
    private final int field2;

    private TestObject1() {
        field1 = null;
        field2 = 0;
    }

    public TestObject1(String field1, int field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public String getField1() {
        return field1;
    }

    public int getField2() {
        return field2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestObject1 that = (TestObject1) o;

        if (field2 != that.field2) return false;
        return field1 != null ? field1.equals(that.field1) : that.field1 == null;
    }

    @Override
    public int hashCode() {
        int result = field1 != null ? field1.hashCode() : 0;
        result = 31 * result + field2;
        return result;
    }
}
