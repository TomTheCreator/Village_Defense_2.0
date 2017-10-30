package me.TomTheDeveloper.Stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class MySQLDatabase {

    public MySQLConnectionManager manager;
    JavaPlugin plugin;

    public MySQLDatabase(JavaPlugin javaPlugin) {
        this.plugin = javaPlugin;
        this.manager = new MySQLConnectionManager(plugin);
        plugin.getLogger().info("Configuring connection pool...");
        manager.configureConnPool();


        try {
            Connection connection = manager.getConnection();
            if (connection == null) {
                System.out.print("CONNECTION TO DATABASE FAILED!");
                return;
            }

            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `playerstats` (\n" +
                    "  `UUID` text NOT NULL,\n" +
                    "  `kills` int(11) NOT NULL DEFAULT '0',\n" +
                    "  `deaths` int(11) NOT NULL DEFAULT '0',\n" +
                    "  `highestwave` int(11) NOT NULL DEFAULT '0',\n" +
                    "  `gamesplayed` int(11) NOT NULL DEFAULT '0',\n" +
                    "  `level` int(11) NOT NULL DEFAULT '0',\n" +
                    "  `xp` int(11) NOT NULL DEFAULT '0',\n" +
                    "  `orbs` int(11) NOT NULL DEFAULT '0'\n" +
                    ");");
            manager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // Table exists
    }


    public void executeUpdate(String query) {
        try {
            Connection connection = manager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            manager.closeConnection(connection);
        } catch (SQLException e) {
            plugin.getLogger().warning("Failed to execute update: " + query);
        }

    }

    public ResultSet executeQuery(String query) {
        try {
            Connection connection = manager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            manager.closeConnection(connection);
            return rs;
        } catch (SQLException exception) {
            exception.printStackTrace();
            plugin.getLogger().warning("Failed to execute request: " + query);
            return null;
        }
    }

    public void insertPlayer(String UUID) {
        executeUpdate("INSERT INTO playerstats (UUID,xp) VALUES ('" + UUID + "',0)");
    }

    public void closeDatabase() {
        manager.shutdownConnPool();
    }


    public void addStat(String UUID, String stat) {
        addStat(UUID, stat, 1);
    }

    public void addStat(String UUID, String stat, int amount) {
        executeUpdate("UPDATE playerstats SET " + stat + "=" + stat + "+" + amount + " WHERE UUID='" + UUID + "'");
    }

    public void setStat(String UUID, String stat, int number) {
        executeUpdate("UPDATE playerstats SET " + stat + "=" + number + " WHERE UUID='" + UUID + "';");
    }

    public int getStat(String UUID, String stat) {
        ResultSet set = executeQuery("SELECT " + stat + " FROM playerstats WHERE UUID='" + UUID + "'");
        try {
            if (!set.next())
                return 0;
            return (set.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return 0;
        }

    }

    public Map<UUID, Integer> getColumn(String stat) {
        ResultSet set = executeQuery("SELECT UUID, " + stat + " FROM playerstats ORDER BY " + stat + " DESC;");
        Map<java.util.UUID, java.lang.Integer> column = new LinkedHashMap<UUID, Integer>();
        try {
            while (set.next()) {
                column.put(java.util.UUID.fromString(set.getString("UUID")), set.getInt(stat));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return column;
    }


}