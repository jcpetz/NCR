# NCR - coding example for job application
Instructions for running ReverseGeoDecoder demo application
Submitted by NCR candidate John Petz – 03/13/2017
1.	Pre-Reqs
a.	  Deployment platform must support JRE 1.8 or higher
2.	Build app <requires Maven util, Java JDK 1.8 or higher>
a.	  Deploy project to desired parent folder
b.	  Windows
i.	      build.cmd
c.	  Linux kernel
i.	    chmod 777 build.sh
ii.	    ./build.sh
3.	Configure run-time <optional>
a.	  Edit run command file
i.	    Ensure JAVA_HOME is defined for platform
b.	    If desired change Spring Boot (Tomcat) listening port – currently set to 8080
4.	Run demo app
a.	  Windows
i.	    run.cmd
b.	  Linux kernel
i.	    ./run.sh
c.	  Observe Spring Boot startup in terminal session
5.	Test demo using a browser or REST tool 
a.	  Get address for NCR info given lat/lon
i.	    http://localhost:8080/reversegeocoder/getAddress/lat/33.969601/lon/-84.100033/
ii. 	  Repeat the previous step for any number of lat/lon values
b.	  Get list of most recent lat/lon address queries [starting with oldest]
i.	    http://localhost:8080/reversegeocoder/getLatestQueries
c.	  To change Reverse Geocoder providers
i.	    Edit application jar file using winzip, 7zip, or similar archive tool
ii.	    Navigate to folder BOOT-INF\classes\
iii.	  Edit file application.properties
1.	    3 providers are available [MapQuest, Google, TexasA&M]
2.	    Uncomment the desired provider and its app key
3.	    Save file and update archive
iv.	    Re-run app
