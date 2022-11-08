package com.strider.posterr.application.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserFeedFilter {

    private UUID userId;

}
