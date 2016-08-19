all:
	./gradlew desktop:dist
	cp desktop/build/libs/desktop-1.0.jar release/Bob_the_simplebot.jar
	launch4jc.exe ./launch4jconfig.xml
	git add release/Bob_the_simplebot.jar
	git add release/Bob_the_simplebot.exe
	git commit -m "Release last version"
	git push
