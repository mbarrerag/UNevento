package com.unevento.api.infra.email;
public class MesssageHTML {
    public static final String TEMPLATE_PRUEBA = "<!DOCTYPE html>\n" +
            "<html >\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>Correo de autenticación para registro con HTML</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<div style=\"width: 100%; background: white; text-align: center;\">\n" +
            "    <div style=\"width: 600px; margin: 0 auto; background: white; text-align: center;\">\n" +
            "        <div style=\"width: 70%; margin: 0 auto; text-align: center;\">\n" +
            "            <p style=\"font-size: 14px; color: #231f20;\"><strong>Estimado usuario</strong>,</p>\n" +
            "            <p style=\"font-size: 14px; color: #231f20;\">Su código de verificación para el Registro es:</p>\n" +
            "            <div style=\"text-align: center; border-radius: 10px; background-color: #98d119; font-size: 16px;\">\n" +
            "                <p style=\"width: 12%; height: 40px; font-size: 16px; display: inline-block; border-bottom: 1px #fff solid; border-radius: 5px; text-align: center; color: #fff;\">{0}</p>\n" +
            "                <p style=\"width: 12%; height: 40px; font-size: 16px; display: inline-block; border-bottom: 1px #fff solid; border-radius: 5px; text-align: center; color: #fff;\">{1}</p>\n" +
            "                <p style=\"width: 12%; height: 40px; font-size: 16px; display: inline-block; border-bottom: 1px #fff solid; border-radius: 5px; text-align: center; color: #fff;\">{2}</p>\n" +
            "                <p style=\"width: 12%; height: 40px; font-size: 16px; display: inline-block; border-bottom: 1px #fff solid; border-radius: 5px; text-align: center; color: #fff;\">{3}</p>\n" +
            "                <p style=\"width: 12%; height: 40px; font-size: 16px; display: inline-block; border-bottom: 1px #fff solid; border-radius: 5px; text-align: center; color: #fff;\">{4}</p>\n" +
            "                <p style=\"width: 12%; height: 40px; font-size: 16px; display: inline-block; border-bottom: 1px #fff solid; border-radius: 5px; text-align: center; color: #fff;\">{5}</p>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <p style=\"font-size: 14px; color: #231f20; margin-top: 20px;\">Este código es de un solo uso y no debe ser compartido.</p>\n" +
            "        <p style=\"font-size: 12px; color: #949595;\">Este mensaje de correo electrónico se ha enviado desde una dirección exclusiva para mensajes. Por favor no responda a este mensaje</p>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";
}