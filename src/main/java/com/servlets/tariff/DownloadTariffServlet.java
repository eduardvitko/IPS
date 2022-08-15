package com.servlets.tariff;

import com.model.Tariff;
import com.service.TariffService;
import com.service.TariffServiceImpl;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(urlPatterns = "/download/tariffs")
public class DownloadTariffServlet extends HttpServlet {
    TariffService tariffService = new TariffServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tariff> tariffs = tariffService.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Tariff tariff : tariffs) {
            stringBuilder.append(tariff);
            stringBuilder.append('\n');
        }
        FileWriter file = new FileWriter("C://Users/eduar/Downloads/ips.txt");
        file.write(String.valueOf(stringBuilder));
        file.flush();
        file.close();


        File newFile = new File("C://Users/eduar/Downloads/ips.txt");
        int length = 0;
        ServletOutputStream outStream = resp.getOutputStream();
        ServletContext context = getServletConfig().getServletContext();
        String mimetype = context.getMimeType(String.valueOf(newFile));


        if (mimetype == null) {
            mimetype = "application/octet-stream";

        }


    }
}
