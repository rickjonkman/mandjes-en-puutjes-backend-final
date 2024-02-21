package dev.rick.mandjesenpuutjesback20.models.shoppingList;

import dev.rick.mandjesenpuutjesback20.models.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "shoppingList")
    private List<Grocery> groceries = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "created")
    private LocalDate creationDate;

    @Column(name = "is_new")
    private boolean newList;

    public ShoppingList() {
    }

    public void addGroceryToList(Grocery grocery) {
        this.groceries.add(grocery);
    }

    public void removeGroceryFromList(Grocery grocery) {

        this.getGroceries().remove(grocery);
    }

    public long getId() {
        return this.id;
    }

    public List<Grocery> getGroceries() {
        return this.groceries;
    }

    public User getOwner() {
        return this.owner;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public boolean isNewList() {
        return this.newList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setNewList(boolean newList) {
        this.newList = newList;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ShoppingList)) return false;
        final ShoppingList other = (ShoppingList) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$groceries = this.getGroceries();
        final Object other$groceries = other.getGroceries();
        if (this$groceries == null ? other$groceries != null : !this$groceries.equals(other$groceries)) return false;
        final Object this$owner = this.getOwner();
        final Object other$owner = other.getOwner();
        if (this$owner == null ? other$owner != null : !this$owner.equals(other$owner)) return false;
        final Object this$creationDate = this.getCreationDate();
        final Object other$creationDate = other.getCreationDate();
        if (this$creationDate == null ? other$creationDate != null : !this$creationDate.equals(other$creationDate))
            return false;
        if (this.isNewList() != other.isNewList()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ShoppingList;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $groceries = this.getGroceries();
        result = result * PRIME + ($groceries == null ? 43 : $groceries.hashCode());
        final Object $owner = this.getOwner();
        result = result * PRIME + ($owner == null ? 43 : $owner.hashCode());
        final Object $creationDate = this.getCreationDate();
        result = result * PRIME + ($creationDate == null ? 43 : $creationDate.hashCode());
        result = result * PRIME + (this.isNewList() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "ShoppingList(id=" + this.getId() + ", groceries=" + this.getGroceries() + ", owner=" + this.getOwner() + ", creationDate=" + this.getCreationDate() + ", newList=" + this.isNewList() + ")";
    }
}
