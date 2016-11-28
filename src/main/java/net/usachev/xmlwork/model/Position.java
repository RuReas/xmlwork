package net.usachev.xmlwork.model;

public class Position extends BaseEntity {

    private String DepCode;

    private String DepJob;

    private String Description;

    public Position(Integer id, String depCode, String depJob, String description) {
        super(id);
        DepCode = depCode;
        DepJob = depJob;
        Description = description;
    }

    public Position(String depCode, String depJob, String description) {
        DepCode = depCode;
        DepJob = depJob;
        Description = description;
    }

    public String getDepCode() {
        return DepCode;
    }

    public void setDepCode(String depCode) {
        DepCode = depCode;
    }

    public String getDepJob() {
        return DepJob;
    }

    public void setDepJob(String depJob) {
        DepJob = depJob;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
