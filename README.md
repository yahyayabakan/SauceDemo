SAUCEDEMO AUTOMATION TASK Codes and Tests Prepared by: Yahya AYABAKAN

Software Developer and QA Test Automation Engineer

ayabakanyahya@gmail.com

https://www.linkedin.com/in/yahya-ayabakan-0794a918b/

Build Tool: Maven

Test Framework: Cucumber BBD (with Junit 4 Assertions)

System Requirements: Java 8+ SDK Notes: To run all Scenarios, use Maven verify or CukesRunner

Way: Clone the projects Import maven dependencies from POM Go src -> test > java > com > liveLend > runners > CukesRunner and RUN To generate "HTML Maven Cucumber Report" ; Open Maven right side panel Double Click Project's Lifecycle Click "verify"
In CukesRunner class you can run the tests
Way: Run from command line invoke mvn clean verify

Test Reports Locations 1- Cucumber HTML Plugin Reports target -> cucumber-html-reports > overview-steps.html (Right Click and Open in any Browser )

It will be run by multithread parallelly in order to not to waste time

2022 Yahya AYABAKAN

End
