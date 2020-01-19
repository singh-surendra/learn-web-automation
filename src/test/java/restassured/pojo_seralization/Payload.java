package restassured.pojo_seralization;

public class Payload {


    String summary;
    String description;

    IssueType issuetype;
    Projects poject;

    public Payload(String summary, String description, IssueType issue, Projects project){

        this.summary = summary;
        this.description = description;
        this.issuetype = issue;
        this.poject = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Projects getPoject() {
        return poject;
    }

    public void setPoject(Projects poject) {
        this.poject = poject;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public IssueType getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }


}
