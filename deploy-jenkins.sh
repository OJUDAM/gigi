#!/bin/sh

if [ -z $1 ]
then
    echo -e ">> 첫번째 인자가 비었습니다.\n"
    echo ">> 첫번째 인자값은 profile명 입니다."
elif [ $1 != "dev-web" ] && [ $1 != "prod" ]; then
    echo ">> 첫번째 인자 값은 dev(개발) or prod(운영)로 입력해주세요."
else
    cd /var/lib/jenkins/workspace/gigi

    ./gradlew bootJar

    cd ./build/libs

    scp -i ../../key/gigi.pem book-app.jar ubuntu@158.180.68.72:/home/ubuntu/gigi/
    ssh -i ../../key/gigi.pem -t ubuntu@158.180.68.72 -T "cd /home/ubuntu/gigi ; bash" "./deploy.sh gigi.jar $1"

    cd ../..
fi
