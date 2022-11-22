cd ..
cd dependencies
call mvn clean deploy

cd ..
cd common
call mvn clean deploy

cd ..
cd common-domain
call mvn clean deploy

cd ..
cd common-service
call mvn clean deploy

cd ..
cd common-web
call mvn clean deploy