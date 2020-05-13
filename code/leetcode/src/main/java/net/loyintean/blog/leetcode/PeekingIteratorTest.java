package net.loyintean.blog.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * https://leetcode-cn.com/problems/peeking-iterator/
 */
public class PeekingIteratorTest {


    @Test
    public void test() {
        PeekingIterator pi = new PeekingIterator(Arrays.asList(1, 2, 3).iterator());

        assertEquals(1, (int) pi.next());
        assertEquals(2, (int) pi.peek());
        assertEquals(2, (int) pi.next());
        assertEquals(3, (int) pi.next());
        assertNull(pi.next());
        assertFalse(pi.hasNext());
    }

    class PeekingIterator implements Iterator<Integer> {


        private Node<Integer> head = new Node<>();

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.

            for (Node<Integer> current = head; iterator.hasNext(); ) {
                Node<Integer> next = new Node<>();
                next.val = iterator.next();
                current.next = next;
                current = next;
            }

        }


        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return Optional.of(head).map(h -> h.next).map(n -> n.val).orElse(null);
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer result = peek();
            Optional.of(head).map(h -> h.next).ifPresent(n -> head.next = n.next);
            return result;
        }

        @Override
        public boolean hasNext() {
            return head.next != null;
        }

        private class Node<T> {
            private T val;

            private Node<T> next;

        }
    }
}