package ua.kobzev.theatre.viewresolvers;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import ua.kobzev.theatre.domain.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Locale;
import java.util.Map;

/**
 * Created by kkobziev on 4/6/16.
 */
public class PdfViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        return new AbstractPdfView() {
            @Override
            protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
                Ticket ticket = (Ticket) map.get("ticket");
                if (ticket != null) {
                    PdfPTable table = new PdfPTable(3);
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.getDefaultCell().setBackgroundColor(Color.lightGray);

                    table.addCell("Name");
                    table.addCell("Value");

                    table.addCell("Ticket #:");
                    table.addCell(ticket.getId().toString());

                    table.addCell("Ticket user:");
                    table.addCell(ticket.getUser().toString());

                    table.addCell("Ticket event:");
                    table.addCell(ticket.getAssignedEvent().toString());

                    table.addCell("Ticket seat:");
                    table.addCell(ticket.getSeat().toString());

                    table.addCell("Ticket price:");
                    table.addCell(ticket.getPrice().toString());

                    table.addCell("");
                    table.addCell("");

                    document.add(table);
                }
            }
        };
    }
}
