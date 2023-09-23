public class Account {
    private double balance;
    private Transaction[] balanceFlu = new Transaction[100];
    public int cnt = 0;
    private void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("So tien ban nap vao khong hop le!");
        }
    }
    private void withdraw(double amount) {
        if (amount > 0) {
            if (amount > balance) {
                System.out.println("So tien ban rut vuot qua so du!");
            } else {
                this.balance -= amount;
            }
        } else {
            System.out.println("So tien ban rut ra khong hop le");
        }
    }

    public void addTransaction(double amount, String operation) {
        if (operation != "DEPOSIT" || operation != "WITHDRAW") {
            System.out.println("Yeu cau khong hop le!");
        } else {
            if(operation == "DEPOSIT") {
                this.balance += amount;
            } else {
                this.balance -= amount;
            }
            double tran
            Transaction newTran = new Transaction(operation, amount, this.balance);
            balanceFlu[cnt] = newTran;
            cnt++;
        }
    }

    public void printTransaction() {
        for (int i = 0; i < cnt; i++) {
            if (balanceFlu[i].getOperation() == "DEPOSIT") {
                this.balance += balanceFlu[i].getAmount()
            }
            double tran = Math.round(balanceFlu[i].getAmount() * 100.0) / 100.0;
            double sodu = Math.round(this.balance * 100.0) / 100.0;
            System.out.println("Giao dich " + (i + 1) + " :");
        }
    }

}
