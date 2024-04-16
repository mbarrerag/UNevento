package com.unevento.api.infra.email;


public class MesssageHTML {
    public static final String TEMPLATE_PRUEBA = "<!DOCTYPE html>\n" +
            "<html >\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>Correo de prueba con HTML</title>\n" +
            "</head>\n" +
            "<style>\n" +
            "    .bg{\n" +
            "        width: 100%; background: white;\n" +
            "    }\n" +
            "    .cont{\n" +
            "        width: 600px; margin: 0 auto; background: white;\n" +
            "    }\n" +
            "    .boxCont{\n" +
            "        width: 70%; margin: 0 auto;\n" +
            "    }\n" +
            "    .boxCont p{\n" +
            "        font-size: 10px; color: #231f20;\n" +
            "    }\n" +
            "    .boxCode{\n" +
            "          text-align: center; border-radius: 10px; background-color: #7788e7; font-size: 16px;\n" +
            "      }\n" +
            "    .boxCode p{\n" +
            "       width: 12%; height: 40px; font-size: 16px; display: inline-block; border-bottom: 1px #fff solid; border-radius: 5px; text-align: center; color: #fff\n" +
            "    }\n" +
            "    .boxCont .footer{\n" +
            "        font-size: 12px; color: #949595; text-align: center;\n" +
            "    }\n" +
            "    .cont .fo{\n" +
            "        width: 100%;\n" +
            "        height: 10px;\n" +
            "        margin: 20px 0 0 0;\n" +
            "        background: linear-gradient(90deg, #4155c4, #1b041);\n" +
            "    }\n" +
            "</style>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "<div class=\"bg\">\n" +
            "    <div class=\"cont\">\n" +
            "        <div class=\"boxCont\">\n" +
            "            <p> <strong>Estimado usuario</strong>,</p>\n" +
            "            <p>Su código de verificación para el inicio de sesión es:</p>\n" +
            "            <div class=\"boxCode\">\n" +
            "                <p>{0}</p>\n" +
            "                <p>{1}</p>\n" +
            "                <p>{2}</p>\n" +
            "                <p>{3}</p>\n" +
            "                <p>{4}</p>\n" +
            "                <p>{5}</p>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <p> Este código es de un solo uso.</p>\n" +
            "        <br/>\n" +
            "        <p class=\"footer\">Este mensaje de correo electrónico se ha enviado desde una dirección exclusiva para mensajes. Por favor no responda a este mensaje</p>\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";
}
