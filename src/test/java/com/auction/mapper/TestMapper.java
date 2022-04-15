package com.auction.mapper;

import com.auction.datetime.DateTimeDto;
import com.auction.dto.lot.LotDto;
import com.auction.dto.user.UserDto;
import com.auction.entity.lot.Lot;
import com.auction.entity.user.User;

public class TestMapper {
    private TestMapper() {
        throw new UnsupportedOperationException();
    }

    public static Lot lotDtoToLot(LotDto lotDto) {
        if (lotDto == null) {
            return null;
        }

        return Lot.builder()
                  .name(lotDto.getName())
                  .description(lotDto.getDescription())
                  .startBid(lotDto.getStartBid())
                  .maxBidRateStep(lotDto.getMaxBidRateStep())
                  .startDateTime(lotDto.getLotDateTime().getStartDateTime())
                  .endDateTime(lotDto.getLotDateTime().getEndDateTime())
                  .build();
    }

    public static LotDto lotToDto(Lot lot) {
        if (lot == null) {
            return null;
        }

        return LotDto.builder()
                     .id(lot.getId())
                     .name(lot.getName())
                     .description(lot.getDescription())
                     .startBid(lot.getStartBid())
                     .maxBidRateStep(lot.getMaxBidRateStep())
                     .lotDateTime(new DateTimeDto(lot.getStartDateTime(),
                                                  lot.getEndDateTime()))
                     .build();
    }

    public static User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                   .firstName(userDto.getFirstName())
                   .lastName(userDto.getLastName())
                   .email(userDto.getEmail())
                   .build();
    }

    public static UserDto userToDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                      .id(user.getId())
                      .firstName(user.getFirstName())
                      .lastName(user.getLastName())
                      .email(user.getEmail())
                      .build();
    }
}
