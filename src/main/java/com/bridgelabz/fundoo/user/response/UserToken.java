package com.bridgelabz.fundoo.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {

	private String userIdToken;
	private String userEmailToken;


}
