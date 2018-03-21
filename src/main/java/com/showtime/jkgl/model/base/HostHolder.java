package com.showtime.jkgl.model.base;


import com.showtime.jkgl.model.entity.Admin;
import com.showtime.jkgl.model.entity.Adviser;
import com.showtime.jkgl.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<Admin> admins = new ThreadLocal<>();

    private static ThreadLocal<User> users = new ThreadLocal<>();

    private static ThreadLocal<Adviser> advisers = new ThreadLocal<>();

    private static ThreadLocal<String> tickets = new ThreadLocal<>();

    public Admin getAdmin() {
        return admins.get();
    }

    public void setAdmin(Admin admin) {
        admins.set(admin);
    }

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public Adviser getAdviser() {
        return advisers.get();
    }

    public void setAdviser(Adviser adviser) {
        advisers.set(adviser);
    }

    public String getTicket() {
        return tickets.get();
    }

    public void setTicket(String ticket) {
        tickets.set(ticket);
    }

    public void clear() {
        admins.remove();
        users.remove();
        advisers.remove();
        tickets.remove();
    }
}
