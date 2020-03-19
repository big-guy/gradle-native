package dev.nokee.docs.fixtures;

import org.apache.commons.lang3.SystemUtils;

import javax.annotation.Nullable;

public abstract class OnlyIfCondition {

	public abstract boolean canExecute();

	public static OnlyIfCondition of(@Nullable String condition) {
		if (condition == null) {
			return new NoCondition();
		} else if (condition == "macos") {
			return new OnlyIfOperatingSystemIsMacOSCondition();
		}
		throw new IllegalArgumentException("only-if keyword only supports 'macos'");
	}

	private static class NoCondition extends OnlyIfCondition {
		@Override
		public boolean canExecute() {
			return true;
		}
	}

	private static class OnlyIfOperatingSystemIsMacOSCondition extends OnlyIfCondition {
		@Override
		public boolean canExecute() {
			return SystemUtils.IS_OS_MAC;
		}
	}
}
