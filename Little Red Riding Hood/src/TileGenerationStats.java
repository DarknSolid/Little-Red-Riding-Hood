public class TileGenerationStats {
    private int spawnChance,
            surviveConditionAmount, spawnConditionAmount;

    public TileGenerationStats(int spawnChance, int surviveConditionAmount, int spawnConditionAmount) {
        this.spawnChance = spawnChance;
        this.surviveConditionAmount = surviveConditionAmount;
        this.spawnConditionAmount = spawnConditionAmount;
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
}
