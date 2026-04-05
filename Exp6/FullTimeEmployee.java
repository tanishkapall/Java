class FullTimeEmployee extends Employee {
    protected double baseSalary;
    protected double perfBonus;
    protected String role;

    public FullTimeEmployee(String name, String panNo, String joiningDate,
                            String designation, int empId,
                            double baseSalary, double perfBonus, String role) {
        super(name, panNo, joiningDate, designation, empId);
        this.baseSalary = baseSalary;
        this.perfBonus = perfBonus;
        this.role = role;
    }

    @Override
    double calcCTC() {
        double extra = 0;

        if (role.equalsIgnoreCase("SWE")) {
            extra = baseSalary * 0.2; // tech allowance
        } else if (role.equalsIgnoreCase("HR")) {
            extra = baseSalary * 0.15; // hiring commission
        }

        return baseSalary + perfBonus + extra;
    }
}