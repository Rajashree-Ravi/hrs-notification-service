package com.hrs.notificationservice.services;

import com.hrs.notificationservice.models.CustomerDto;

public interface NotificationService {

	void sendWelcomeEmail(CustomerDto customer);

}
