package com.osmy.wallet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.osmy.wallet.dtos.UserDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest extends MockMvcRequestBuilders {
    @Autowired
    private MockMvc mmvc;

    @Autowired
    Gson gson;

    @Test
    void getUsers() throws Exception {
        MvcResult mvcr = mmvc.perform(
            get("/api/v1/users")
        )
        .andExpect(status().isOk())
        .andReturn();

        System.out.println(mvcr.getResponse().getContentAsString());
    }

    @Test
    void getUser() throws Exception {
        Long id = (long) 2;

        MvcResult mvcr = mmvc.perform(
            get("/api/v1/users/" + id)
        )
        .andExpect(status().isOk())
        .andReturn();

        System.out.println(mvcr.getResponse().getContentAsString());
    }

    @Test
    void newUser() throws Exception {
        UserDTO dto = new UserDTO();
        dto.setEmail("em2@em.com");
        dto.setPassword("secret");
        dto.setNames("osmaldy2");

        String payload = gson.toJson(dto);

        MvcResult mvcr = mmvc.perform(
            post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload)
        )
        .andExpect(status().isOk())
        // .andDo(MockMvcResultHandlers.print())
        .andReturn();

        System.out.println(mvcr.getResponse().getContentAsString());
    }

    @Test
    void updateUser() throws Exception {
        Long id = (long) 2;
        UserDTO dto = new UserDTO();
        dto.setNames("osmaldy3");

        String payload = gson.toJson(dto);

        MvcResult mvcr = mmvc.perform(
            patch("/api/v1/users/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload)
        )
        .andExpect(status().isOk())
        // .andDo(MockMvcResultHandlers.print())
        .andReturn();

        System.out.println(mvcr.getResponse().getContentAsString());
    }

    @Test
    void updateUserNotFoundError() throws Exception {
        Long id = (long) 3;
        UserDTO dto = new UserDTO();
        dto.setNames("osmaldy3");

        String payload = gson.toJson(dto);

        MvcResult mvcr = mmvc.perform(
            patch("/api/v1/users/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload)
        )
        .andExpect(status().is(404))
        // .andDo(MockMvcResultHandlers.print())
        .andReturn();

        System.out.println(mvcr.getResponse().getContentAsString());
    }

    @Test
    void putUser() throws Exception {
        Long id = (long) 1;
        UserDTO dto = new UserDTO();
        dto.setNames("osmaldy1");

        String payload = gson.toJson(dto);

        MvcResult mvcr = mmvc.perform(
            put("/api/v1/users/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload)
        )
        .andExpect(status().isOk())
        // .andDo(MockMvcResultHandlers.print())
        .andReturn();

        System.out.println(mvcr.getResponse().getContentAsString());
    }

    @Test void deleteUser() throws Exception {
        Long id = (long) 3;

        MvcResult mvcr = mmvc.perform(
            delete("/api/v1/users/" + id)
        )
        .andExpect(status().isOk())
        // .andDo(MockMvcResultHandlers.print())
        .andReturn();

        System.out.println(mvcr.getResponse().getContentAsString());
    }

}