package com.example.api_scotia.models.response.emails;

import com.example.api_scotia.service.EmailMessage;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanMessage implements EmailMessage {

    private String name;
    private String lastName;
    private BigDecimal amount;
    private Integer installments;

    @Override
    public String generateBody() {
        return "Estimado/a " + name+" "+lastName + ",\n\n" +
                "Nos complace informarle que su solicitud de préstamo ha sido aprobada exitosamente. A continuación, encontrará los detalles de su préstamo:\n\n" +
                "📄 **Detalles del Préstamo**:\n" +
                "• Monto del Préstamo: $" + amount + "\n" +
                "• Plazo de Pago: " + installments + " meses\n\n" +
                "Recuerde que el primer pago deberá realizarse en la fecha acordada y se generarán cuotas mensuales según el calendario de pagos.\n\n" +
                "Para cualquier consulta adicional, no dude en comunicarse con nosotros a través de nuestro equipo de atención al cliente.\n\n" +
                "Gracias por confiar en nosotros.\n\n" +
                "Atentamente,\n" +
                "Equipo de Rapimoney";
    }

}
