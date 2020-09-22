package hw2;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class MyStringTest {
	private static final char[][] data = { "book".toCharArray(), "bookkeeper".toCharArray(),
			"cover".toCharArray(), "orange".toCharArray(), "steal".toCharArray(), "steel".toCharArray(),
			"tan".toCharArray(), "tangerine".toCharArray(), "tangle".toCharArray(), "vector".toCharArray()};
	private static final char[] empty = {};
	private static MyString[] s1;
	private static MyString[] s2;
	private static MyString empty1 = new MyString(empty);
	private static MyString empty2 = new MyString(empty);

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@BeforeClass
	public static void initAll() {
		s1 = new MyString[data.length];
		s2 = new MyString[data.length];
		for (int i = 0; i < data.length; i++) {
			s1[i] = new MyString(data[i]);
			s2[i] = new MyString(data[i]);
		}
	}
	
	// 5 points
	@Test
	public void test05EqualsEmpty() {
		assertTrue(empty1.equals(empty2));
		assertTrue(empty2.equals(empty1));
		MyString hello = new MyString("hello".toCharArray());
		assertFalse(empty1.equals(hello));
		assertFalse(hello.equals(empty1));
	}
	
	// 5 points
	@Test
	public void test05EqualsSameObject() {
		for(int i = 0; i < s1.length; i++) {
			for(int j = 0; j < s1.length; j++) {
				if (i == j) {
					assertTrue(s1[i].equals(s1[j]));
				} else {
					assertFalse(s1[i].equals(s1[j]));
				}
			}
		}
	}
	
	// 10 points
	@Test
	public void test10Equals() {
		for(int i = 0; i < s1.length; i++) {
			for(int j = 0; j < s2.length; j++) {
				if (i == j) {
					assertTrue(s1[i].equals(s2[j]));
					assertTrue(s2[j].equals(s2[i]));
				} else {
					assertFalse(s1[i].equals(s2[j]));
					assertFalse(s2[j].equals(s2[i]));
				}
			}
		}
	}
	
	// 5 points
	@Test
	public void test05CompareToEmpty() {
		assertTrue(empty1.compareTo(empty2) == 0);
		assertTrue(empty2.compareTo(empty1) == 0);
		MyString hello = new MyString("hello".toCharArray());
		assertTrue(empty1.compareTo(hello) < 0);
		assertTrue(hello.compareTo(empty1) > 0);
	}
	
	// 5 points
	@Test
	public void test05CompareSingleLetters() {
		char[] aArray = {'a'};
		char[] bArray = {'b'};
		char[] yArray = {'y'};
		MyString a = new MyString(aArray);
		MyString b = new MyString(bArray);
		MyString y = new MyString(yArray);
		
		assertTrue(a.compareTo(b) < 0);
		assertTrue(a.compareTo(y) < 0);
		assertTrue(y.compareTo(a) > 0);
		assertTrue(y.compareTo(b) > 0);
		assertTrue(b.compareTo(a) > 0);
		assertTrue(b.compareTo(y) < 0);
	}
	
	// 10 points
	@Test
	public void test10CompareTo() {
		for(int i = 0; i < s1.length; i++) {
			for(int j = 0; j < s2.length; j++) {
				if (i < j) {
					assertTrue(s1[i].compareTo(s2[j]) < 0);
					assertTrue(s2[j].compareTo(s2[i]) > 0);
				} else if (i > j) {
					assertTrue(s1[i].compareTo(s2[j]) > 0);
					assertTrue(s2[j].compareTo(s2[i]) < 0);
				} else {
					assertTrue(s1[i].compareTo(s2[j]) == 0);
					assertTrue(s2[j].compareTo(s2[i]) == 0);
				}
			}
		}
	}
	
	
	//5 points
	@Test
	public void test05IndexOfEmpty() {
		for(char c = 'a'; c <= 'z'; c++) {
			assertEquals(-1, empty1.indexOf(c));
		}
	}
	
	// 5 points
	@Test
	public void test05IndexOfSingleLetter() {
		char[] aArray = {'a'};
		char[] bArray = {'b'};
		char[] yArray = {'y'};
		MyString a = new MyString(aArray);
		MyString b = new MyString(bArray);
		MyString y = new MyString(yArray);
		assertEquals(0, a.indexOf('a'));
		assertEquals(0, b.indexOf('b'));
		assertEquals(0, y.indexOf('y'));
	}
	
	// 10 points
	@Test
	public void test10IndexOf() {
		for(char c = 'a'; c <= 'z'; c++) {
			for(int i = 0; i < data.length; i++) {
				assertEquals(String.copyValueOf(data[i]).indexOf(c), s1[i].indexOf(c));
			}
		}
	}
	
	//5 points
	@Test
	public void test05ReplaceEmpty() {
		char[] empty = {};
		MyString e = new MyString(empty);
		MyString e2 = e.replace('a', 'b');
		assertTrue(e == e2);
		e.replace('x', 'X');
		assertTrue(e == e2);
		e.replace('a', 'a');
		assertTrue(e == e2);
	}
	
	//5 points
	@Test
	public void test05ReplaceOldNotPresent() {
		char[] appleArray = "apple".toCharArray();
		char[] bananaArray = "banana".toCharArray();
		char[] carArray = "car".toCharArray();
		MyString apple = new MyString(appleArray);
		MyString banana = new MyString(bananaArray);
		MyString car = new MyString(carArray);
		MyString result = apple.replace('b', 'a');
		assertTrue(result == apple);
		result = banana.replace('c',  'b');
		assertTrue(result == banana);
		result = car.replace('b', 'c');
		assertTrue(result == car);
		
		result = apple.replace('c', 'x');
		assertTrue(result == apple);
		result = banana.replace('z',  'r');
		assertTrue(result == banana);
		result = car.replace('t', 'u');
		assertTrue(result == car);
	}
	
	//5 points
	@Test
	public void test05ReplaceSingle() {
		char[] aArray = {'a'};
		char[] bArray = {'b'};
		char[] yArray = {'y'};
		MyString a = new MyString(aArray);
		MyString b = new MyString(bArray);
		MyString y = new MyString(yArray);
		MyString result = a.replace('a', 'y');
		assertEquals('y', result.charAt(0));
		assertEquals('a', a.charAt(0));
		result = b.replace('b', 'a');
		assertEquals('a', result.charAt(0));
		assertEquals('b', b.charAt(0));
		result = y.replace('y', 'b');
		assertEquals('b', result.charAt(0));
		assertEquals('y', y.charAt(0));
	}
	
	//5 points
	@Test
	public void test05ReplaceInWord() {
		char[] appleArray = "apple".toCharArray();
		char[] bananaArray = "banana".toCharArray();
		MyString apple = new MyString(appleArray);
		MyString banana = new MyString(bananaArray);
		MyString result = apple.replace('p', 'x');
		assertEquals('a', result.charAt(0));
		assertEquals('x', result.charAt(1));
		assertEquals('x', result.charAt(2));
		assertEquals('l', result.charAt(3));
		assertEquals('e', result.charAt(4));
		assertEquals('a', apple.charAt(0));
		assertEquals('p', apple.charAt(1));
		assertEquals('p', apple.charAt(2));
		assertEquals('l', apple.charAt(3));
		assertEquals('e', apple.charAt(4));
		
		result = banana.replace('n', 't');
		assertEquals('b', result.charAt(0));
		assertEquals('a', result.charAt(1));
		assertEquals('t', result.charAt(2));
		assertEquals('a', result.charAt(3));
		assertEquals('t', result.charAt(4));
		assertEquals('a', result.charAt(5));
		assertEquals('b', banana.charAt(0));
		assertEquals('a', banana.charAt(1));
		assertEquals('n', banana.charAt(2));
		assertEquals('a', banana.charAt(3));
		assertEquals('n', banana.charAt(4));
		assertEquals('a', banana.charAt(5));
	}
}
