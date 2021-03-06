package dev.nokee.platform.jni

import dev.nokee.platform.jni.fixtures.JavaJniCppGreeterLib
import dev.nokee.platform.jni.fixtures.elements.JniLibraryElement

class JavaCppJniLibraryWithJUnitTestingFunctionalTest extends AbstractJavaJniLibraryWithJUnitTestingFunctionalTest {
	protected void makeSingleProject() {
		settingsFile << "rootProject.name = 'jni-greeter'"
		buildFile << '''
			plugins {
				id 'java'
				id 'dev.nokee.jni-library'
				id 'dev.nokee.cpp-language'
			}
		'''
	}

	protected JniLibraryElement getComponentUnderTest() {
		return new JavaJniCppGreeterLib('jni-greeter')
	}
}
