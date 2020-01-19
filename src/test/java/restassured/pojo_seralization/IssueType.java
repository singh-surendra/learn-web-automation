package restassured.pojo_seralization;

public class IssueType {
    // example : https://developer.atlassian.com/server/jira/platform/jira-rest-api-examples/

    /*

    {
    "fields": {
       "project":
       {
          "key": "TEST"
       },
       "summary": "REST ye merry gentlemen.",
       "description": "Creating of an issue using project keys and issue type names using the REST API",
       "issuetype": {
          "name": "Bug"
       }
   }
}
     */

    /*

    Steps :
    1. Create fields
    2. Craete getters and setters
    3. Create Constructers
    4.

     */

    String name;

    public IssueType(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
