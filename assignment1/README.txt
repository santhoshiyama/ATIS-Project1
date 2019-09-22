Steps to run project:

(Assumption: Running via IDE)

1. Change the filepath on line 38 of LoadAndQueryDBLPHdt.java to your local instance of dblp-20170124.hdt
2. Run main method in class Assisgnemnt1Application. This will start a server on localhost:8080 (Note: the first time running this, an index.hdt file will be created. This process will take a while. Go grab a coffee and relax for a bit)
3. Manually open index.html in a web browser (it's not hosted on the web server itself)
4. From that web page, enter two author's names (Note: They need to be exact matches of author's labels in DBLP)
5. Results will be populated on the screen showing number of publications the two authors have done together and a COI value
6. Search more authors and have fun :)

----------------------------------------------------------------------------

Architecture:

Assignemnt1Application.java: main method used to start web service. Spring Boot Application
LoadAndQueryDBLPHdt.java: Java class where our HTTP request handlers exist. Two main endpoints:
	- /authors/get: Return all authors within DBLP (Will take a substantial amount of time)
	- /load (paramaters: fauth = first author, sauth = second author): Gets a count of publications between given authors
index.html: Web interface
jquery.min.js: JQuery Javascript
main.js: Javascript created to call /load endpoint and update web page

---------------------------------------------------------------------------- 

COI Scale:

0-5 Papers: Low
6-15 Papers: Medium
>15: High

----------------------------------------------------------------------------

Examples:

C. J. Date & E. F. Codd: 	3 Papers, Low COI
Nirmalya Roy & Nilavra Pathak: 	4 Papers, Low COI
Anis Charfi & Mira Mezini:	27 Papers, High COI
Clement T. Yu & Hai He		16 Papers, High COI
Weiyi Meng & Yiyao Lu		8 Paper, Medium COI
