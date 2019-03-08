package p03_mass_effect_galaxy_map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int starClusters = Integer.parseInt(reader.readLine());
        int reportCount = Integer.parseInt(reader.readLine());
        int galaxySize = Integer.parseInt(reader.readLine());

        List<StarSystem> starSystems = new ArrayList<>();

        while (starClusters-- > 0) {
            String[] coordinates = reader.readLine().split("\\s+");
            String name = coordinates[0];
            int x = Integer.parseInt(coordinates[1]);
            int y = Integer.parseInt(coordinates[2]);

            starSystems.add(new StarSystem(name, x, y));
        }

        Collections.sort(starSystems);

        while (reportCount-- > 0) {
            String[] params = reader.readLine().split("\\s+");

            int x1 = Integer.parseInt(params[1]);
            int y1 = Integer.parseInt(params[2]);
            int x2 = x1 + Integer.parseInt(params[3]);
            int y2 = y1 + Integer.parseInt(params[4]);

            int galaxiesInRange = printGalaxiesInRange(x1, y1, x2, y2, starSystems);
            System.out.println(galaxiesInRange);
        }
    }

    private static int printGalaxiesInRange(int x1, int y1, int x2, int y2, List<StarSystem> starSystems) {
        int galaxiesInRange = 0;

        for (StarSystem starSystem : starSystems) {
            if (x1 > starSystem.getX() || y1 > starSystem.getY() || y2 < starSystem.getY()) {
                continue;
            }

            if (x2 < starSystem.getX()) {
                break;
            }

            if (starSystem.isInside(x1, y1, x2, y2)) {
                galaxiesInRange++;
            }
        }

        return galaxiesInRange;
    }
}
