package com.example.viberide_taxibookingsystem.admin_management;

import java.time.LocalDate;

public class SupportAdmin extends Admin {

    private String ticketCategory;
    private int resolvedTickets;

    public SupportAdmin(String adminId, String name, String email, String password,
                        String department, LocalDate createdDate,
                        String ticketCategory, int resolvedTickets) {
        super(adminId, name, email, password, department, createdDate);
        this.ticketCategory = ticketCategory;
        this.resolvedTickets = resolvedTickets;
    }

    public String getTicketCategory() { return ticketCategory; }
    public void setTicketCategory(String ticketCategory) { this.ticketCategory = ticketCategory; }

    public int getResolvedTickets() { return resolvedTickets; }
    public void setResolvedTickets(int resolvedTickets) { this.resolvedTickets = resolvedTickets; }

    @Override
    public String getRole() { return "SUPPORT_ADMIN"; }

    @Override
    public String getPermissions() {
        return "Handle Support Tickets | Category: " + ticketCategory;
    }

    @Override
    public String getActivityLog() {
        return "SupportAdmin: " + getName() + " | Category: " + ticketCategory
               + " | Resolved: " + resolvedTickets + " tickets";
    }

    @Override
    public String toFileString() {
        return "SUPPORT," + getAdminId() + "," + getName() + "," + getEmail() + ","
               + getPassword() + "," + getDepartment() + "," + getCreatedDate() + ","
               + ticketCategory + "," + resolvedTickets;
    }
}
