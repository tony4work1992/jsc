package smartosc.jsc.applications.jec.user_mgt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import smartosc.jsc.applications.jec.file_handling.services.FileProcessingService;
import smartosc.jsc.applications.jec.user_mgt.models.User;

public class UserService {
    public static final String SOURCE = "app/src/main/java/smartosc/jsc/applications/jec/resources/users.csv";

    public List<User> getUserList() {
        // Simulate fetching user list

        FileProcessingService fileService = new FileProcessingService();

        List<Map<String, String>> userList = fileService.readCsvWithHeaders(SOURCE);
        return userList.stream()
                .map(item -> {
                    return this.convert(item);
                })
                .collect(Collectors.toList());

        // Example: Fetch from database or any data source
    }

    public User convert(Map<String, String> data) {
        User user = new User();
        user.setUserId(Integer.parseInt(data.get("userId")));
        user.setFullname(data.get("fullname"));
        user.setEmail(data.get("email"));

        return user;
    }

    public Integer chooseUser(Scanner scanner) {
        List<User> userList = getUserList();
        List<Integer> userIds = new ArrayList<>();
        String input;
        Integer userId;
        System.out.println("Danh sach user:");
        System.out.println("userId, fullname, email");
        for (User user : userList) {
            userIds.add(user.getUserId());
            System.out.println(user.getUserId() + "," + user.getFullname() + "," + user.getEmail());
        }

        while (true) {
            System.out.println("Chon 1 user hop le:");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                userId = Integer.parseInt(input);
                if (userIds.contains(userId)) {
                    break;
                }
            }
        }

        return userId;
    }

    public User getUserDetail(Integer userId) {
        // Simulate fetching user list

        FileProcessingService fileService = new FileProcessingService();

        List<Map<String, String>> userList = fileService.readCsvWithHeaders(SOURCE);
        List<User> users = userList.stream()
                .map(item -> {
                    return this.convert(item);
                })
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            return null;
        }

        return users.getFirst();

    }
}
