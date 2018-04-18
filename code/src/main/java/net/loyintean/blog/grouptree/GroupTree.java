/**
 * All Rights Reserved
 */
package net.loyintean.blog.grouptree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.loyintean.blog.fileimport.FileImportService;
import net.loyintean.blog.fileimport.FileImportServiceAsExcel;

/**
 * @author linjun
 * @since 2017年10月23日
 */
public class GroupTree {

    private FileImportService<Group> groupImporter;

    public GroupTree() {
        FileImportServiceAsExcel<Group> excelgroupImporter = new FileImportServiceAsExcel<>();

        excelgroupImporter.setHeaderList(
            Arrays.asList(Arrays.asList("id", "name", "parentGroup_id")));

        excelgroupImporter.setClaz(Group.class);
        excelgroupImporter.setCellList(Arrays.asList("id", "name", "parentId"));

        this.groupImporter = excelgroupImporter;
    }

    public void parseToTree(String fileName) throws IOException {

        byte[] excelFile = Files.readAllBytes(Paths.get(fileName));

        List<Group> groups = this.groupImporter.importFile(excelFile);

        Group root = GroupTree.convertToTree(groups);
        groups = null;

        GroupTree.toFile(root);
    }

    /**
     * @author linjun
     * @since 2017年10月23日
     * @param root
     * @throws IOException
     */
    private static void toFile(Group root) throws IOException {

        //        Path filePath = Paths.get("F:\\work\\平台组\\统一认证\\机构树.txt");
        Path filePath = Paths.get("F:\\work\\平台组\\统一认证\\HR机构树.txt");
        GroupTree.toFile(root, 0, filePath);
    }

    private static void toFile(Group root, int level, Path filePath)
            throws IOException {
        StringBuilder line = new StringBuilder(50);

        line.append('|').append("    ");

        for (int i = 0; i < level; i++) {
            line.append('-').append("    ");
        }

        line.append(root.getId().toString()).append("    ")
            .append(root.getName()).append("    ")
            .append(root.getParentId().toString())
            .append(System.lineSeparator());

        Files.write(filePath, line.toString().getBytes(),
            StandardOpenOption.APPEND);

        root.getChildrenList().forEach(child -> {
            try {
                GroupTree.toFile(child, level + 1, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private static Group convertToTree(List<Group> groups) {
        Group root = new Group();
        root.setName("根节点");

        Map<Integer, Group> groupMap = groups.stream()
            .collect(Collectors.toMap(group -> group.getId(), group -> group));
        groupMap.entrySet().forEach(entry -> {

            Group g = entry.getValue();

            if (g.getParentId() > 0) {
                // 从map中找到父节点，把它放到父节点下
                Group parent = groupMap.get(g.getParentId());
                parent.addChild(g);

            } else {
                // 父节点为null时，它必须放到根节点下
                root.addChild(g);
            }

        });
        return root;
    }

    /**
     * @author linjun
     * @since 2017年10月23日
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        GroupTree groupTree = new GroupTree();
        groupTree.parseToTree("F:\\work\\平台组\\统一认证\\HRORG.xlsx");

    }
}
