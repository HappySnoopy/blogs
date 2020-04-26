package net.loyintean.blog.standard;

import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 处理代码中的行尾注释
 *
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2019 -11-20
 */
public class Comment {
    /** 单行注释的标注 */
    private static final String SINGLE_COMMENT_PREFIX = "//";

    /**
     * 入口
     * <p>
     * 为了避免在ci时被单测执行到，这里定义为main方法
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        comment();
    }

    /**
     * 处理入口。指定一个文件或路径，对这个文件/路径递归地处理其中的行尾注释
     */
    private static void comment() {
        File file = new File("filepath");
        loopFile(file);
    }

    /**
     * 递归处理
     * <p>
     * 如果是一个文件，那么去处理这个文件。
     * <p>
     * 如果是一个文件夹，那么递归地调用自己，处理这个文件夹下的所有文件/文件夹
     *
     * @param file the file
     */
    private static void loopFile(File file) {
        System.out.println(file.getAbsolutePath());
        if (file.isFile()) {
            try {
                comment(file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println(file.getAbsolutePath() + "  发生异常 " + e.getMessage());
            }
        } else if (file.isDirectory()) {
            Optional.of(file).map(File::listFiles).map(Stream::of).ifPresent(s -> s.forEach(Comment::loopFile));
        }
    }

    /**
     * 针对指定文件做处理
     * <p>
     * 读取文件其中所有代码/注释，处理一遍之后，把文件写回去（覆盖原有文件）。读写编码格式都是utf-8
     * <p>
     * 可能会导致：虽然编译完全没问题，但是IDEA不识别部分类。
     * <p>
     * 解决方案：删掉本地分支或者本地代码（注意先push已有修改），然后重新checkout/clone
     *
     * @param filePath 文件路径。这里必须是一个指定文件，不能是文件夹。
     * @throws IOException 读/写文件时如果出错会抛出此异常
     */
    private static void comment(String filePath) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(filePath), Charsets.UTF_8);

        List<String> result = new LinkedList<>();
        for (String line : lines) {
            commentLine(line, result);
        }

        Files.write(Paths.get(filePath), result, Charsets.UTF_8);
    }

    /**
     * 处理代码行
     * <p>
     * 如果有行末注释（代码行中有"//"，且//不在行首），则按行末注释来处理
     * <p>
     * 如果没有行末注释，则尝试处理上一行的注释
     *
     * @param line   the line
     * @param result the result
     */
    private static void commentLine(String line, List<String> result) {
        if (line.trim().indexOf(SINGLE_COMMENT_PREFIX) > 0) {
            // 有行末注释
            inComment(line, result);
        } else {
            // 如果没有行末注释，按没有行末注释来处理
            fieldWithSingleLineComment(line, result);
        }
    }

    /**
     * 当前注释被嵌套在了其它的注释里
     */
    private static void inComment(String line, List<String> result) {
        String code = line.trim();
        if (code.trim().indexOf(SINGLE_COMMENT_PREFIX) > 0 && (code.trim().startsWith("/*") || code.trim()
                .startsWith(SINGLE_COMMENT_PREFIX) || code.trim().startsWith("*"))) {
            // 有行末注释，但是行末注释被放在了其它注释行里
            // 直接删掉一个 //
            result.add(line.replace(SINGLE_COMMENT_PREFIX, StringUtils.EMPTY));
        } else {
            // 否则，看看是否字段注释
            fieldLine(line, result);
        }

    }


    /**
     * 提取出左侧空格
     */
    private static String leftBlank(String line) {
        char[] chars = line.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c == ' ') {
                stringBuilder.append(c);
            } else if (c == '\t') {
                stringBuilder.append("    ");
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 尝试按字段来解析，把字段后的行尾注释转换为javadoc注释。
     * <br>
     * 转换前：
     * <pre>
     * private String str; // 这是一个字符串
     * </pre>
     * <br>
     * 转换后：
     * <pre>
     * /** 这是一个字符串 *\/
     * private String str;
     * </pre>
     */
    private static void fieldLine(String line, List<String> result) {
        // 尝试拆成两部分
        String[] lines = line.split(SINGLE_COMMENT_PREFIX);

        // 如果第一部分是public/private/protected开头、是;结尾，那么认为这是一个字段
        String code = lines[0].trim();

        boolean isFiled = isFiled(code);

        if (isFiled) {
            result.add(leftBlank(line) + "/** " + (lines.length > 1 ? lines[1] : code.split(" ")[2]) + " */");
            result.add(lines[0]);
        } else {
            // 否则，不能当做字段来解析 ，开始下一步解析
            bracketLine(line, result);
        }
    }

    /**
     * 判断给定的代码是否是字段 目前处理不了default字段
     */
    private static boolean isFiled(String line) {
        String code = line.trim();
        return code.indexOf(";") == code.length() - 1 && (code.startsWith("public") || code
                .startsWith("protected") || code.startsWith("private"));
    }

    /**
     * 尝试按照括号行来解析
     * <p>
     * 行末注释跟在花括号（"{}"）后的，放到括号下一行去
     */
    private static void bracketLine(String line, List<String> result) {
        // 尝试拆成两部分
        String[] lines = line.split(SINGLE_COMMENT_PREFIX);
        String code = lines[0].trim();

        if (code.endsWith("{")) {
            result.add(lines[0]);
            result.add(leftBlank(line) + "    // " + (lines.length > 1 ? lines[1] : ""));
        } else if (code.endsWith("}")) {
            result.add(lines[0]);
            result.add(leftBlank(line) + "// " + (lines.length > 1 ? lines[1] : ""));
        } else {
            // 否则，尝试按下一种方式来解析
            codeComment(line, result);
        }
    }

    /**
     * 把普通代码行中的行尾注释提到上一行去
     * <p>
     * 如：
     * <pre>
     * String str= "a"; // 这是注释
     * </pre>
     * 转换为
     * <pre>
     * // 这是注释
     * String str = "a";
     * </pre>
     */
    private static void codeComment(String line, List<String> result) {
        // 尝试拆成两部分
        String[] lines = line.split(SINGLE_COMMENT_PREFIX);
        String code = lines[0].trim();
        if (!isFiled(code) && !code.endsWith("{") && !code.endsWith("}")) {
            // 非独立字段、非括号结尾的，才放到这里处理
            // 从result中反向找出当前代码行所属的代码片段
            List<String> codeFrag = new ArrayList<>(5);
            codeFrag.add(lines[0]);
            // 从后往前找
            for (int i = result.size() - 1; i > -1; i--) {
                String frag = result.get(i).trim();
                if (frag.endsWith(";") || frag.endsWith("{") || frag.endsWith("}") || frag.endsWith("*/") || frag
                        .startsWith(SINGLE_COMMENT_PREFIX)) {
                    // 如果是;、{、}结束，说明这一行是代码；如果是*/结尾或者是//开头，说明这一行是注释
                    // 此时，当前代码片段就完结了
                    break;
                } else {
                    // 如果不是以上情况，说明上一行还属于当前的代码片段
                    // 注意要插入到当前代码之前
                    codeFrag.add(0, result.get(i));
                    // 并且把result中的老代码删掉，以免重复
                    result.remove(i);
                }
            }
            // 把注释行放到完整代码片段之前
            codeFrag.add(0, leftBlank(codeFrag.get(0)) + "// " + lines[1].trim());
            // 然后把完整代码片段加入到整个文件中
            result.addAll(codeFrag);
        } else {
            // 否则，直接把这一行加进来
            result.add(line);
        }
    }

    /**
     * 如果字段上的注释是单行注释，转为javadoc注释
     * <p>
     * 如<br>{@code // 这是一个字符串 private String str; }
     * <p>
     * 转为：<br> {@code /** 这是一个字符串 *\/ private String str; }
     */
    private static void fieldWithSingleLineComment(String line, List<String> result) {
        if (isFiled(line)) {
            // 如果当前行是字段，那么检查上一行是否是单行注释
            String lastLine = result.get(result.size() - 1);
            if (isSingleLienComment(lastLine)) {
                // 如果字段的上一行是单行注释
                // 先把上一行去掉
                result.remove(result.size() - 1);
                // 再把上一行转成javadoc注释
                StringBuilder commentLint = new StringBuilder(lastLine.length() + 5).append(lastLine).append(" */");
                commentLint
                        .replace(lastLine.indexOf(SINGLE_COMMENT_PREFIX), lastLine.indexOf(SINGLE_COMMENT_PREFIX) + 2,
                                "/** ");
                // 把新的注释行，以及当前代码行，写回文件中
                result.add(commentLint.toString());
            }
        }
        result.add(line);
    }

    /** 判断是否是单行注释 */
    private static boolean isSingleLienComment(String line) {
        return line.trim().startsWith(SINGLE_COMMENT_PREFIX);
    }
}
