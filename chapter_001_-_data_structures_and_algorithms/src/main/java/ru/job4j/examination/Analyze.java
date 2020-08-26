package ru.job4j.examination;

import java.util.*;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> map = new HashMap<>();
        for (User previousUser : previous) {
            map.put(previousUser.id, previousUser);
        }
        Info resultInfo = new Info();
        int unmodifiedUsers = 0;
        for (User currentUser : current) {
            boolean currentUserIsContainedInMap = map.containsKey(currentUser.id);
            if (!currentUserIsContainedInMap) {
                resultInfo.added++;
            }
            if (currentUserIsContainedInMap
                    && !map.get(currentUser.id).name.equals(currentUser.name)) {
                resultInfo.changed++;
            }
            if (currentUserIsContainedInMap) {
                unmodifiedUsers++;
            }
        }
        if (unmodifiedUsers < map.keySet().size()) {
            resultInfo.deleted = map.keySet().size() - unmodifiedUsers;
        }
        return resultInfo;
    }

    public static class User {
        int id;
        String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "User{" + "id=" + id
                    + ", name='" + name
                    + '}';
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added && changed == info.changed && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return "Info{" + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }
}