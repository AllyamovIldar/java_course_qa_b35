# java_course_qa_b35
Репозиторий для хранения информации по прохождению [курса программирования по Java для тестировщиков](https://software-testing.ru/edu/3-online/1-java-for-testers).
## Вводная информация
* Написано на Java 8
* Запуск Jenkins (ver. 1.651.1):
java -Dhudson.model.DownloadService.noSignatureCheck=true -jar jenkins.war
* Для установки плагинов:
java -jar jenkins-cli.jar -s http://localhost:8080/ install-plugin https://updates.jenkins.io/download/plugins/git-client/1.19.6/git-client.hpi
* Запуск Selenium Standalone (с возможностью работы Firefox; Selenium 4.7.2):
java -Dwebdriver.firefox.bin="C:\Program Files\Mozilla Firefox\firefox.exe" -jar selenium-server-4.7.2.jar standalone
