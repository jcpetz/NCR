REM build demo app
cd ReverseGeocodingDemo
call mvn clean install
cd ..
copy ReverseGeocodingDemo\target\reversegeocodingdemo-0.0.1-SNAPSHOT.jar
