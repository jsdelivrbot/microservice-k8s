package com.epam.event.it;

public class TestObject2 {

    private final String field1;
    private final String field2;

    private TestObject2() {
        field1 = null;
        field2 = null;
    }

    public TestObject2(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestObject2 that = (TestObject2) o;

        if (field1 != null ? !field1.equals(that.field1) : that.field1 != null) return false;
        return field2 != null ? field2.equals(that.field2) : that.field2 == null;
    }

    @Override
    public int hashCode() {
        int result = field1 != null ? field1.hashCode() : 0;
        result = 31 * result + (field2 != null ? field2.hashCode() : 0);
        return result;
    }
}
