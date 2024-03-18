import enums.CollectibleTypes;
import enums.MonsterNames;

import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int turn = 1;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Digite o nome do herói: ");
        String heroName = scanner.nextLine();
        Hero hero = new Hero(heroName);

        System.out.println("Herói '" + hero.getName() + "' criado com sucesso!");

        while (hero.getLife() > 0) {
            System.out.println("Um novo monstro apareceu!");

            MonsterNames[] monsterNames = MonsterNames.values();
            MonsterNames monsterName = monsterNames[random.nextInt(monsterNames.length)];

            int monsterLevel = (random.nextInt(5) - 2) + hero.getLevel();

            int monsterDamageRandom = random.nextInt(51);
            int monsterDamage = (int) (monsterDamageRandom + ((monsterDamageRandom * 0.5) * (monsterLevel - 1)));

            Monster monster = new Monster(monsterName.toString(), monsterDamage, monsterLevel);

            printHeroInfo(hero);

            int collectibleStrengthRandom = random.nextInt(75);
            double collectibleStrength = (collectibleStrengthRandom + ((collectibleStrengthRandom * 0.5) * (hero.getLevel() - 1)));

            CollectibleTypes[] collectibleTypes = CollectibleTypes.values();
            CollectibleTypes collectibleType = collectibleTypes[random.nextInt(collectibleTypes.length)];

            printCollectibleInfo(collectibleType.toString(), collectibleStrength);

            System.out.println("Escolha o que fazer com o coletável:");
            System.out.println("1. Pegar novo coletável");
            System.out.println("2. Jogar fora coletável atual");
            System.out.println("3. Continuar");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    hero.item.take(collectibleType, collectibleStrength);
                    break;
                case 2:
                    hero.item.discard();
                    break;
                case 3:
                    break;
            }

            while (hero.getLife() > 0 && monster.getLife() > 0) {
                heroPlay(hero, monster);

                if (monster.getLife() <= 0) {
                    System.out.println("O monstro foi derrotado!");
                    hero.upLevel();
                    break;
                }

                monster.attack(hero);

                if (hero.getLife() <= 0) {
                    System.out.println("O herói foi derrotado!");
                    break;
                }
            }

            turn++;
        }
}

    public static void heroPlay(Hero hero, Monster monster) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Turno do Herói");
        printHeroInfo(hero);
        printMonsterInfo(monster);

        System.out.println("Seu coletável atual:");
        printCollectibleInfo(hero.item.getCollectibleType().toString(), hero.item.getStrength());

        // Dar opções ao jogador
        System.out.println("Escolha sua jogada:");
        System.out.println("1. Atacar");
        System.out.println("2. Usar coletável");
        System.out.print("Opção: ");
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                hero.attack(monster);
                break;
            case 2:
                if (hero.getItem() != null) {
                    hero.useItem(monster);
                } else {
                    System.out.println("Você não possui nenhum coletável para usar.");
                }
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
    public static void printHeroInfo(Hero hero) {
        try (Formatter formatter = new Formatter()) {
            String collectibleType = hero.item.getCollectibleType().toString();
            String collectibleStrength = hero.item.getStrength().toString();

            if (hero.item.getStrength() == 0.0) {
                collectibleStrength =  "Vazio";
            }
            if (hero.item.getCollectibleType() == CollectibleTypes.None){
                collectibleType = "Vazio";
            }

            formatter.format("+------------+--------------+----------------+------+--------+-------+%n");
            formatter.format("| %-10s | %-13s | %-14s | %-4s | %-6s | %-5s |%n", "Nome", "Tipo Coletável", "Força Coletável", "Dano", "Vida", "Nível");
            formatter.format("+------------+--------------+----------------+------+--------+-------+%n");
            formatter.format("| %-10s | %-13s | %-14s | %-4d | %-6.1f | %-5d |%n", hero.getName(), collectibleType, collectibleStrength, hero.getDamage(), hero.getLife(), hero.getLevel());
            formatter.format("+------------+--------------+----------------+------+--------+-------+%n");

            System.out.print(formatter);
        }
    }



    public static void printMonsterInfo(Monster monster) {
        System.out.println("+------------------------+");
        System.out.println("|      Informações do Monstro     |");
        System.out.println("+------------------------+");
        try (Formatter formatter = new Formatter()) {
            formatter.format("+------------+---------+--------+-------+%n");
            formatter.format("| %-10s | %-7s | %-6s | %-5s |%n", "Nome", "Dano", "Vida", "Nível");
            formatter.format("+------------+---------+--------+-------+%n");
            formatter.format("| %-10s | %-7d | %-6.1f | %-5d |%n", monster.getName(), monster.getDamage(), monster.getLife(), monster.getLevel());
            formatter.format("+------------+---------+--------+-------+%n");

            System.out.print(formatter);
        }
    }

    public static void printCollectibleInfo(String collectibleType, Double CollectibleStrength) {
            System.out.println("Informações do Coletável:");
            System.out.println("Tipo: " + collectibleType);
            System.out.println("Força: " + CollectibleStrength);
    }
}
