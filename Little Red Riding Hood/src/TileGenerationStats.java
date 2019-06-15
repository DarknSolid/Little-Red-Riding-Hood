public class TileGenerationStats {
    private int spawnChance,
            surviveConditionAmount, spawnConditionAmount, iterations;

    public TileGenerationStats(int spawnChance, int surviveConditionAmount, int spawnConditionAmount, int iterations) {
        this.spawnChance = spawnChance;
        this.surviveConditionAmount = surviveConditionAmount;
        this.spawnConditionAmount = spawnConditionAmount;
        this.iterations = iterations;
    }

    public int getSpawnChance() {
        return spawnChance;
    }

    public int getSurviveConditionAmount() {
        return surviveConditionAmount;
    }

    public int getSpawnConditionAmount() {
        return spawnConditionAmount;
    }

    public int getIterations() {
        return iterations;
    }
}
