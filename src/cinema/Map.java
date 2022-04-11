package cinema;

import java.util.Arrays;

public class Map {
    private char layout[];

    private Map() {
        this.layout = new char[138];
        char value = '[]';
        Arrays.fill(this.layout, (char)'[]');
    }
}
