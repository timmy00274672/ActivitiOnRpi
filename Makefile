JAVA_HOME = /usr/lib/jvm/jdk-8-oracle-arm-vfp-hflt
#JAVA_HOME =  /usr/lib/jvm/java-8-oracle/
EMPTY :=
SPACE := $(EMPTY) $(EMPTY)

CLASSPATH += src/main/resource
CLASSPATH += src/test/resource
CLASSPATH += target/classes
CLASSPATH += target/test-classes
CLASSPATH += target/dependency/*

#JAVA_FLAG += -Djava.library.path=./target/jni

CLEANPATH += target/classes
CLEANPATH += target/test-classes
#CLEANPATH += target/jni/*

clean:
	rm -rf $(CLEANPATH)

# environment_RPi:
# 	mvn test-compile dependency:copy-dependencies
	#make -C src/main/jni libdevice.so

# TestOpenOnRpiLight: 
# 	java -cp $(subst $(SPACE),:,$(CLASSPATH)) \
# 		$(JAVA_FLAG) \
# 		com.diplab.activiti.test.process.TestOpenOnRpiLight

toggleSwitch:
	sudo mvn install exec:java -DskipTests \
		-Dexec.mainClass=com.diplab.activitionrpi.device.RpiTrunLightController

toggleSwitch_activiti:
	sudo mvn install exec:java -DskipTests -Dexec.classpathScope=test\
		-Dexec.mainClass=com.diplab.activitionrpi.test.process.TestOpenOnRpiLight