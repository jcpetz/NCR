REM build demo app

cd ReverseGeocodingDemo

mvn install

cd ..
cp -f ReverseGeocodingDemo/target/reversegeocodingdemo-0.0.1-SNAPSHOT.jar .
