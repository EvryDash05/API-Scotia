package com.example.api_scotia.models.response.emails;

import com.example.api_scotia.service.EmailMessage;
import lombok.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulateLoanMessage implements EmailMessage {

    private String name;
    private String lastName;
    private String dni;
    private BigDecimal amount;
    private Integer installments;
    private String email;
    private BigDecimal monthlyPayment;

    @Override
    public String generateBody() {
        // Formatear la cantidad en un formato de moneda
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));
        String formattedAmount = currencyFormatter.format(amount);
        String formattedMonthlyPayment = currencyFormatter.format(monthlyPayment);

        // Construir el cuerpo del mensaje
        return String.format(
                "Estimado/a %s %s,\n\n" +
                        "Gracias por confiar en nosotros para la simulación de su préstamo.\n\n" +
                        "Detalles de la simulación:\n" +
                        "- DNI: %s\n" +
                        "- Monto del préstamo: %s\n" +
                        "- Número de cuotas: %d\n" +
                        "- Monto de la cuota: %s\n\n" +
                        "Por favor, no dude en contactarnos si tiene alguna pregunta o si desea proceder con la solicitud.\n\n" +
                        "Saludos cordiales,\n" +
                        "Equipo de RapiMoney",
                name, lastName, dni, formattedAmount, installments, formattedMonthlyPayment
        );
    }

}
