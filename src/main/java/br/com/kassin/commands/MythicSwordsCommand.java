package br.com.kassin.commands;

import br.com.kassin.item.MythicSword;
import br.com.kassin.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public final class MythicSwordsCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (args.length < 1) {
            incorrectCommand(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "worm_hammer":
                player.getInventory().addItem(MythicSword.WORM_HAMMER.getItem());
                Message.Chat.sendMessage(player, "&6 Você recebeu uma Worm Hammer!");
                break;

            case "peacekeeper_sword":
                player.getInventory().addItem(MythicSword.PEACEKEEPER_SWORD.getItem());
                Message.Chat.sendMessage(player, "&a Você recebeu uma PeaceKeeper Sword!");
                break;
        }
        return false;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("worm_hammer","peacekeeper_sword");
        }
        return new ArrayList<>();
    }

    private void incorrectCommand(final Player player) {
        Message.Chat.sendMessage(player, "&aCorrect use: /mythicItem <item>");
    }
}
