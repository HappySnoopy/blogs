package net.loyintean.blog.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PatternMatchingLcciTest {

    private PatternMatchingLcci.Solution solution = new PatternMatchingLcci().new Solution();

    @Test
    public void test() {
        assertTrue(solution.patternMatching("abba", "dogcatcatdog"));
        assertTrue(solution.patternMatching("", ""));
        assertTrue(solution.patternMatching("a", ""));
        assertTrue(solution.patternMatching("bbba", "xxxxxx"));
        assertFalse(solution.patternMatching("ab", ""));
        assertFalse(solution.patternMatching("", "x"));
        assertTrue(solution.patternMatching("bbb", "xxxxxx"));
        assertFalse(solution.patternMatching("bbbaa", "xxxxxxy"));
        assertFalse(
                solution.patternMatching("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", "p"));

    }
}