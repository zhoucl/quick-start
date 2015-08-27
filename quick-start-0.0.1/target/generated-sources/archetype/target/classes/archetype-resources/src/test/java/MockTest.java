#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.junit.Test;

import junit.framework.TestCase;

public class MockTest extends TestCase {
	@Test
	public void test() {
		assertEquals(true, true);
	}
}
