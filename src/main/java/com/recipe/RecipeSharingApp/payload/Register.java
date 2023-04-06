package com.recipe.RecipeSharingApp.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    private String username;
    private String  email;

    private String password;

    private String profilePicUrl;


}
