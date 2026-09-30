package seg3x02.calculator

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@Controller
class WebController {

    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("first", "")
        model.addAttribute("second", "")
        model.addAttribute("selectedOperation", "")
        model.addAttribute("result", "--")
    }

    @RequestMapping("/")
    fun home(): String {
        return "calculator"
    }

    @GetMapping("/select")
    fun selectOperation(
        @RequestParam(value = "first", defaultValue = "") first: String,
        @RequestParam(value = "second", defaultValue = "") second: String,
        @RequestParam(value = "operation", defaultValue = "") operation: String,
        model: Model
    ): String {
        model.addAttribute("first", first)
        model.addAttribute("second", second)
        model.addAttribute("selectedOperation", operation)
        return "calculator"
    }

    @GetMapping("/calculate")
    fun calculate(
        @RequestParam(value = "first", defaultValue = "") first: String,
        @RequestParam(value = "second", defaultValue = "") second: String,
        @RequestParam(value = "selectedOperation", defaultValue = "") selectedOperation: String,
        model: Model
    ): String {
        model.addAttribute("first", first)
        model.addAttribute("second", second)
        model.addAttribute("selectedOperation", selectedOperation)

        val firstNumber = first.toDoubleOrNull()
        val secondNumber = second.toDoubleOrNull()
        if (firstNumber == null || secondNumber == null) {
            return "calculator"
        }

        val result = when (selectedOperation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else -> return "calculator"
        }
        model.addAttribute("result", formatResult(result))
        return "calculator"
    }

    private fun formatResult(value: Double): String {
        return DecimalFormat("#,##0.##", DecimalFormatSymbols(Locale.US)).format(value)
    }
}
