Steps to execute the project:

1. Clone/Download the project from github
2. Make sure you have dblp dataset files(includes original hdt file and index file) downloaded from http://www.rdfhdt.org/datasets/
NOTE: If index file is not downloaded from above link, then it takes lot of time to start the server on your local machine.
3. Import the downloaded project as a Maven Project on any IDE. 
4. In "LoadAndQueryDBLPHdt.java" file, give the path of dataset where you have downloaded the hdt file.
5. Open "Assignment1Application.java" file and right-click, select 'Run As--->Java Application'.
6. Try to access the endpoint using "http://localhost:8080/load?fauth=santhoshi%20gurram&sauth=def"
NOTE: a)fauth and sauth should be the values given from Frontend page.
b) SPARQL query is not yet implemented correctly. For now, I have included a generic query. 
Need help on this part-------constructing a SPARQL query.
