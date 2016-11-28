package net.usachev.xmlwork.model;

public class KeyPosition {

    private String depCode;

    private String depJob;

    public KeyPosition(String depCode, String depJob) {
        this.depCode = depCode;
        this.depJob = depJob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyPosition that = (KeyPosition) o;

        if (!depCode.equals(that.depCode)) return false;
        return depJob.equals(that.depJob);
    }

    @Override
    public int hashCode() {
        int result = depCode.hashCode();
        result = 31 * result + depJob.hashCode();
        return result;
    }
}
