package seg3x02.calculator

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class WebControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun request_to_home() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("calculator"))
    }

    @Test
    fun addition_2_plus_3() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "2")
                .param("second", "3")
                .param("selectedOperation", "+"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "5"))
            .andExpect(MockMvcResultMatchers.view().name("calculator"))
    }

    @Test
    fun subtraction_10_minus_4() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "10")
                .param("second", "4")
                .param("selectedOperation", "-"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "6"))
    }

    @Test
    fun multiplication_6_times_7() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "6")
                .param("second", "7")
                .param("selectedOperation", "*"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "42"))
    }

    @Test
    fun division_10_by_4() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "10")
                .param("second", "4")
                .param("selectedOperation", "/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "2.5"))
    }

    @Test
    fun selecting_operation_resets_result() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/select")
                .param("first", "1")
                .param("second", "1")
                .param("operation", "-"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("selectedOperation", "-"))
            .andExpect(MockMvcResultMatchers.model().attribute("result", "--"))
            .andExpect(MockMvcResultMatchers.model().attribute("first", "1"))
    }
}
