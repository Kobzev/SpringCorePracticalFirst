package ua.kobzev.theatre.converters;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import ua.kobzev.theatre.domain.Ticket;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kkobziev on 4/6/16.
 */
public class PdfMessageConverter implements HttpMessageConverter<Ticket> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        //if (aClass.equals(Ticket.class)) return true;
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(new MediaType[]{new MediaType("application","pdf")});
    }

    @Override
    public Ticket read(Class<? extends Ticket> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Ticket ticket, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, httpOutputMessage.getBody());
            document.open();

            Anchor anchorTarget = new Anchor("Ticket.");
            anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);

            paragraph1.add(anchorTarget);
            document.add(paragraph1);

            document.add(new Paragraph("Ticket #:" + ticket.getId(),
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));

            document.add(new Paragraph("Ticket user:" + ticket.getUser().toString(),
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));

            document.add(new Paragraph("Ticket event:" + ticket.getAssignedEvent().toString(),
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));

            document.add(new Paragraph("Ticket seat:" + ticket.getSeat(),
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));

            document.add(new Paragraph("Ticket price:" + ticket.getPrice(),
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
