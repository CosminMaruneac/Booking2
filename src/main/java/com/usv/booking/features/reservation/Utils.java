package com.usv.booking.features.reservation;

import com.usv.booking.features.user.Account;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

public class Utils {

  private Utils() {

  }

  static PropertyMap<Reservation, ReservationDto> reservationDtoPropertyMap = new PropertyMap<>() {
    @Override
    protected void configure() {

      map().setOwnerId(source.getOwner().getId());
      using(converter).map(source.getOwner(), destination.getOwnerName());
    }
  };

  static Converter<Account, String> converter = ctx -> generateFullName(ctx.getSource());

  public static String generateFullName(Account account) {
    return account.getFirstName() + " " + account.getLastName();
  }
}
