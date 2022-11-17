package com.example.trips.model;

public class Expense {
    private String id, type, amount, timeExpense;

    public Expense() {
    }

    public Expense(String type, String amount, String timeExpense) {
        this.type = type;
        this.amount = amount;
        this.timeExpense = timeExpense;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimeExpense() {
        return timeExpense;
    }

    public void setTimeExpense(String timeExpense) {
        this.timeExpense = timeExpense;
    }
}
