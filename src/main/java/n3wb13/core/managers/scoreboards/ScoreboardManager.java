package n3wb13.core.managers.scoreboards;

import n3wb13.core.managers.Manager;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager extends Manager {

    private Scoreboard myBoard;

    public ScoreboardManager(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        myBoard = plugin.getServer().getScoreboardManager().getNewScoreboard();
    }

    public Scoreboard getScoreBoard() {
        return myBoard;
    }

    public void createTeam(String name) {
        if (getTeam(name) == null)
            myBoard.registerNewTeam(name);
    }

    public Team getTeam(String name) {
        return myBoard.getTeam(name);
    }
}
