package chooseadventure.data.model.session;

import chooseadventure.data.entity.Item;
import chooseadventure.data.model.command.CardinalDirection;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    public static String QUEEN_NAME = "QUEEN OF ICO";
    public static String JESTER_NAME = "TIN JESTR";

    private String name;

    private CardinalDirection directionFacing;

    private Set<Item> inventory = new HashSet<>();

    private Set<Item> discoveredItems = new HashSet<>();

    public Player() {
        directionFacing = CardinalDirection.EAST;
        int i = new Random().nextInt()%2;
        name = i == 1 ? QUEEN_NAME, JESTER_NAME;
    }

    public Player(String name) { this.name = name; }

    public String getName(){ return name;}

    public Set<Item> getInventory() {
        return inventory;
    }

    public boolean hasItem(Item item) {
        return inventory.stream().anyMatch(i->i.getName().equalsIgnoreCase(item.getName()));
    }

    public Optional<Item> findItem(String item) {
        return inventory
                .stream()
                .filter(i -> i.getName().equalsIgnoreCase(item))
                .findFirst();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public Set<Item> getDiscoveredItems() {
        return discoveredItems;
    }

    public boolean discoveredItem(Item item) {
        return discoveredItems.contains(item);
    }

    public void discoveryItem(Item item) {
        discoveredItems.add(item);
    }

    public void clearDiscoveredItems() {
        discoveredItems.clear();
    }

    public CardinalDirection getDirectionFacing() { return directionFacing; }

    public void setDirectionFacing(CardinalDirection directionFacing) {
        this.directionFacing = directionFacing;
    }

    public Player toResource() {
        return new Player(name);
    }
}
