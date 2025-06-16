package dsw.concessionaria.enums;

public enum UserRole {
    ADMIN,
    CLIENT,
    STORE;

    public static UserRole fromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + role);
    }
    public String toString() {
        return name();
    }
}
