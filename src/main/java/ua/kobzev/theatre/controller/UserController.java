package ua.kobzev.theatre.controller;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.dto.TicketDto;
import ua.kobzev.theatre.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kkobziev on 3/19/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String testUser(){
        return "user";
    }

    @RequestMapping(value = "/gettickets", method = RequestMethod.GET, headers = "Accept=application/pdf")
    public String getTickectsByUserPDF(Model model){
        return "getTickets";
    }

    @RequestMapping(value = "/gettickets", method = RequestMethod.GET)
    public ModelAndView getTickectsByUser(Model model){
        User user = userService.getById(19);
        List<Ticket> tickets = userService.getBookedTickets(user);
        List<TicketDto> ticketDtoList = new ArrayList<>();
        tickets.stream().forEach(ticket -> ticketDtoList.add(new TicketDto(ticket)));
        JRDataSource datasource = new JRBeanCollectionDataSource(ticketDtoList);

        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();

        // We pass two datasources here:
        // "datasource" is used by the main report.
        // This is declared in the /WEB-INF/jasper-views.xml

        // "JasperCustomSubReportDatasource" is used by the sub-report
        // This is declared  in the master report named tree-template.jrxml
        // It is also declared in the /WEB-INF/jasper-views.xml

        // Both datasources use the same datasource
        // You can provide different datasources
        parameterMap.put("datasource", datasource);

        ModelAndView modelAndView = new ModelAndView("pdfReport", parameterMap);
        return modelAndView;
    }
}
