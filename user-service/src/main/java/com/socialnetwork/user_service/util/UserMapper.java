package com.socialnetwork.user_service.util;

import com.socialnetwork.user_service.model.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toUserDTO(UserRepresentation userRepresentation) {
        if (userRepresentation == null) {
            return null;
        }

        String phone = null;
        LocalDate dateOfBirth = null;
        Map<String, List<String>> attributes = userRepresentation.getAttributes();

        if (attributes != null) {
            List<String> phoneList = attributes.get("phone");
            if (phoneList != null && !phoneList.isEmpty()) {
                phone = phoneList.get(0);
            }

            List<String> dobList = attributes.get("dateOfBirth");
            if (dobList != null && !dobList.isEmpty()) {
                dateOfBirth = parseDateOfBirth(dobList.get(0));
            }
        }

        return UserDTO.builder()
                .id(userRepresentation.getId())
                .email(userRepresentation.getEmail())
                .password(userRepresentation.getFederationLink())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .fullName(userRepresentation.getLastName() + " " +userRepresentation.getFirstName())
                .phone(phone)
                .dateOfBirth(dateOfBirth)
                .build();
    }

    private static LocalDate parseDateOfBirth(String date) {
        if (date != null && !date.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + date);
                return null;
            }
        }
        return null;
    }

    public static List<UserDTO> toUserDTOList(List<UserRepresentation> users) {
        if (users == null || users.isEmpty()) {
            return new ArrayList<>();
        }
        return users.stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }
}
