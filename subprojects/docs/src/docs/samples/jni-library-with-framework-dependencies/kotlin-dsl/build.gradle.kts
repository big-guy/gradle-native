plugins {
	id("java")
	id("dev.nokee.jni-library")
	id("dev.nokee.objective-c-language")
}

library {
	dependencies {
		nativeImplementation("dev.nokee.framework:Cocoa:10.15")        // <1>

		nativeImplementation("dev.nokee.framework:JavaVM:10.15") {
			capabilities {
				requireCapability("JavaVM:JavaNativeFoundation:10.15") // <2>
			}
		}
	}
}

library.variants.configureEach {
	sharedLibrary {
		linkTask.configure {
			linkerArgs.add("-nostdinc")
			linkerArgs.add("-lobjc")
		}
		compileTasks.configureEach {
			compilerArgs.add("-nostdinc")
		}
	}
}
