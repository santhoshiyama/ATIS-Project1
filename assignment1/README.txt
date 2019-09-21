Steps to execute the project:

1. Clone/Download the project from github
2. Make sure you have dblp dataset files(includes original hdt file and index file) downloaded from http://www.rdfhdt.org/datasets/
NOTE: If index file is not downloaded from above link, then it takes lot of time to start the server on your local machine.
3. Import the downloaded project as a Maven Project on any IDE. 
4. In "LoadAndQueryDBLPHdt.java" file, give the path of dataset where you have downloaded the hdt file.
5. Open "Assignment1Application.java" file and right-click, select 'Run As--->Java Application'.
6. Try to access the endpoint using  Example : "http://localhost:8080/load?fauth=C. J. Date&sauth=E. F. Codd"
NOTE: a)fauth and sauth should be the values given from Frontend page.


__________________________________________________________________________________________________

Steps to show the results on front end:
1. Clone/Download the project from github
2. Make sure that your browser does not have the Access-Control-Allow-Origin issue. If it happens, please download the addon and install on your browser. Search the addon name which called Allow CORS: Access-Control-Allow-Origin, author: Muyor.
3. Make sure that you import the jquery framework.
4. When you access the endpoint, open the demo html which is index.html and start to discover.


Issues:
1. Access-Control-Allow-Origin issue.
  Solution: Added "@CrossOrigin" annotation and respective package in "LoadAndQueryDBLPHdt.java" to resolve this issue.
  Reference: https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
2. Result COI value is not being displayed in frontend. Tried and unable to fix it. Help reuired.
