package com.j256.ormlite.logger;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.j256.ormlite.logger.Log.Level;

public class LoggerTest {

	private Logger logger;
	private Log mockLog;
	private Throwable throwable = new Throwable();

	@Before
	public void before() {
		mockLog = createMock(Log.class);
		logger = new Logger(mockLog);
	}

	@Test
	public void testArgAtStart() {
		String arg = "x";
		String end = " yyy";
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, arg + end);
		replay(mockLog);
		logger.trace("{}" + end, arg);
		verify(mockLog);
	}

	@Test
	public void testArgAtEnd() {
		String start = "yyy ";
		String arg = "x";
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, start + arg);
		replay(mockLog);
		logger.trace(start + "{}", arg);
		verify(mockLog);
	}

	@Test
	public void testArgsNextToEachOther() {
		String arg1 = "x";
		String arg2 = "y";
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, arg1 + arg2);
		replay(mockLog);
		logger.trace("{}{}", arg1, arg2);
		verify(mockLog);
	}

	@Test
	public void testArgsApart() {
		String arg1 = "x";
		String middle = " middle ";
		String arg2 = "y";
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, arg1 + middle + arg2);
		replay(mockLog);
		logger.trace("{}" + middle + "{}", arg1, arg2);
		verify(mockLog);
	}

	@Test
	public void testToManyArgs() {
		String start = "yyy ";
		String arg = "x";
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, start + arg);
		replay(mockLog);
		logger.trace(start + "{}", arg);
		verify(mockLog);
	}

	@Test
	public void testNotEnoughArgs() {
		String start = "yyy ";
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, start);
		replay(mockLog);
		logger.trace(start + "{}");
		verify(mockLog);
	}

	@Test
	public void testObjectToString() {
		Foo arg = new Foo();
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, Foo.TO_STRING);
		replay(mockLog);
		logger.trace("{}", arg);
		verify(mockLog);
	}

	@Test
	public void testTraceThrowable() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		mockLog.log(Level.TRACE, msg, throwable);
		replay(mockLog);
		logger.trace(throwable, msg);
		verify(mockLog);
	}

	@Test
	public void testDebug() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.DEBUG)).andReturn(true);
		mockLog.log(Level.DEBUG, msg);
		replay(mockLog);
		logger.debug(msg);
		verify(mockLog);
	}

	@Test
	public void testDebugThrowable() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.DEBUG)).andReturn(true);
		mockLog.log(Level.DEBUG, msg, throwable);
		replay(mockLog);
		logger.debug(throwable, msg);
		verify(mockLog);
	}

	@Test
	public void testInfo() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.INFO)).andReturn(true);
		mockLog.log(Level.INFO, msg);
		replay(mockLog);
		logger.info(msg);
		verify(mockLog);
	}

	@Test
	public void testInfoThrowable() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.INFO)).andReturn(true);
		mockLog.log(Level.INFO, msg, throwable);
		replay(mockLog);
		logger.info(throwable, msg);
		verify(mockLog);
	}

	@Test
	public void testWarn() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.WARNING)).andReturn(true);
		mockLog.log(Level.WARNING, msg);
		replay(mockLog);
		logger.warn(msg);
		verify(mockLog);
	}

	@Test
	public void testWarnThrowable() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.WARNING)).andReturn(true);
		mockLog.log(Level.WARNING, msg, throwable);
		replay(mockLog);
		logger.warn(throwable, msg);
		verify(mockLog);
	}

	@Test
	public void testError() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.ERROR)).andReturn(true);
		mockLog.log(Level.ERROR, msg);
		replay(mockLog);
		logger.error(msg);
		verify(mockLog);
	}

	@Test
	public void testErrorThrowable() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.ERROR)).andReturn(true);
		mockLog.log(Level.ERROR, msg, throwable);
		replay(mockLog);
		logger.error(throwable, msg);
		verify(mockLog);
	}

	@Test
	public void testFatal() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.FATAL)).andReturn(true);
		mockLog.log(Level.FATAL, msg);
		replay(mockLog);
		logger.fatal(msg);
		verify(mockLog);
	}

	@Test
	public void testFatalThrowable() {
		String msg = "ooooooh";
		expect(mockLog.isLevelEnabled(Level.FATAL)).andReturn(true);
		mockLog.log(Level.FATAL, msg, throwable);
		replay(mockLog);
		logger.fatal(throwable, msg);
		verify(mockLog);
	}

	@Test
	public void testIsTraceEnabled() {
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(true);
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(false);
		replay(mockLog);
		assertTrue(logger.isLevelEnabled(Level.TRACE));
		assertFalse(logger.isLevelEnabled(Level.TRACE));
		verify(mockLog);
	}

	@Test
	public void testIsDebugEnabled() {
		expect(mockLog.isLevelEnabled(Level.DEBUG)).andReturn(true);
		expect(mockLog.isLevelEnabled(Level.DEBUG)).andReturn(false);
		replay(mockLog);
		assertTrue(logger.isLevelEnabled(Level.DEBUG));
		assertFalse(logger.isLevelEnabled(Level.DEBUG));
		verify(mockLog);
	}

	@Test
	public void testIsInfoEnabled() {
		expect(mockLog.isLevelEnabled(Level.INFO)).andReturn(true);
		expect(mockLog.isLevelEnabled(Level.INFO)).andReturn(false);
		replay(mockLog);
		assertTrue(logger.isLevelEnabled(Level.INFO));
		assertFalse(logger.isLevelEnabled(Level.INFO));
		verify(mockLog);
	}

	@Test
	public void testIsWarnEnabled() {
		expect(mockLog.isLevelEnabled(Level.WARNING)).andReturn(true);
		expect(mockLog.isLevelEnabled(Level.WARNING)).andReturn(false);
		replay(mockLog);
		assertTrue(logger.isLevelEnabled(Level.WARNING));
		assertFalse(logger.isLevelEnabled(Level.WARNING));
		verify(mockLog);
	}

	@Test
	public void testIsErrorEnabled() {
		expect(mockLog.isLevelEnabled(Level.ERROR)).andReturn(true);
		expect(mockLog.isLevelEnabled(Level.ERROR)).andReturn(false);
		replay(mockLog);
		assertTrue(logger.isLevelEnabled(Level.ERROR));
		assertFalse(logger.isLevelEnabled(Level.ERROR));
		verify(mockLog);
	}

	@Test
	public void testIsFatalEnabled() {
		expect(mockLog.isLevelEnabled(Level.FATAL)).andReturn(true);
		expect(mockLog.isLevelEnabled(Level.FATAL)).andReturn(false);
		replay(mockLog);
		assertTrue(logger.isLevelEnabled(Level.FATAL));
		assertFalse(logger.isLevelEnabled(Level.FATAL));
		verify(mockLog);
	}

	@Test
	public void testTraceShouldNotCallToString() {
		expect(mockLog.isLevelEnabled(Level.TRACE)).andReturn(false);
		replay(mockLog);
		logger.trace("msg {}", new ToStringThrow());
		verify(mockLog);
	}

	@Test
	public void testDebugShouldNotCallToString() {
		expect(mockLog.isLevelEnabled(Level.DEBUG)).andReturn(false);
		replay(mockLog);
		logger.debug("msg {}", new ToStringThrow());
		verify(mockLog);
	}

	@Test
	public void testInfoShouldNotCallToString() {
		expect(mockLog.isLevelEnabled(Level.INFO)).andReturn(false);
		replay(mockLog);
		logger.info("msg {}", new ToStringThrow());
		verify(mockLog);
	}

	@Test
	public void testWarnShouldNotCallToString() {
		expect(mockLog.isLevelEnabled(Level.WARNING)).andReturn(false);
		replay(mockLog);
		logger.warn("msg {}", new ToStringThrow());
		verify(mockLog);
	}

	@Test
	public void testErrorShouldNotCallToString() {
		expect(mockLog.isLevelEnabled(Level.ERROR)).andReturn(false);
		replay(mockLog);
		logger.error("msg {}", new ToStringThrow());
		verify(mockLog);
	}

	@Test
	public void testFatalShouldNotCallToString() {
		expect(mockLog.isLevelEnabled(Level.FATAL)).andReturn(false);
		replay(mockLog);
		logger.fatal("msg {}", new ToStringThrow());
		verify(mockLog);
	}

	@Test(expected = IllegalStateException.class)
	public void testFatalToStringThrow() {
		expect(mockLog.isLevelEnabled(Level.FATAL)).andReturn(true);
		replay(mockLog);
		logger.fatal("msg {}", new ToStringThrow());
	}

	private static class Foo {
		final static String TO_STRING = "foo to string";
		@Override
		public String toString() {
			return TO_STRING;
		}
	}

	private static class ToStringThrow {
		@Override
		public String toString() {
			throw new IllegalStateException("To string should not have been called");
		}
	}
}
