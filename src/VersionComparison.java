public class VersionComparison {

    /**
     * NOT LEETCODE PROBLEM
     * Compare the version strings
     * https://www.baeldung.com/java-comparing-versions
     *
     * SOLUTION: Tokenize the version strings using a dot delimiter, and then compare integer conversion of every String token, beginning from the left.
     * If the token's integer value is the same, examine the next token
     * Continuing this step until we find a difference (or until we reach the last token in either string)
     *
     * Example 1:
     * Most likely "1.0" should be considered less than "1.0.1", but what about "1.0.0"
     */
    public static void compareVersions(String version1, String version2) {
        int comparisonResult = 0;

        String[] versionSplit1 = version1.split("\\.");
        String[] versionSplit2 = version2.split("\\.");
        int maxLengthOfVersionSplits = Math.max(versionSplit1.length, versionSplit2.length);

        for (int i = 0; i < maxLengthOfVersionSplits; i++){
            Integer version1Integer = i < versionSplit1.length ? Integer.parseInt(versionSplit1[i]) : 0;
            Integer version2Integer = i < versionSplit2.length ? Integer.parseInt(versionSplit2[i]) : 0;

            int compare = version1Integer.compareTo(version2Integer);

            if (compare != 0) {
                comparisonResult = compare;
                break;
            }
        }

        if (comparisonResult < 0) {
            System.out.println("Version1 < Version2");
        } else if (comparisonResult == 0){
            System.out.println("Version1 = Version2");
        } else {
            System.out.println("Version1 > Version2");
        }
    }

    public static void main(String[] args) {
        VersionComparison versionComparison = new VersionComparison();
        versionComparison.compareVersions("1.0", "1.0.0");
        versionComparison.compareVersions("2.0.1.1", "2.0.1");
        versionComparison.compareVersions("2.0.0.0.0", "2.0");
        versionComparison.compareVersions("5.17.20", "5.17.200");
    }
}
