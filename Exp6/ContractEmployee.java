class ContractEmployee extends Employee {
    private int noOfHours;
    private double hourlyRate;

    public ContractEmployee(String name, String panNo, String joiningDate,
                            String designation, int empId,
                            int noOfHours, double hourlyRate) {
        super(name, panNo, joiningDate, designation, empId);
        this.noOfHours = noOfHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calcCTC() {
        return noOfHours * hourlyRate;
    }
}