package com.example.api_scotia.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReportGeneratorService<E> {
    byte[] exportToXls(List<E> list) throws JRException, FileNotFoundException;
    JasperPrint getReport(List<E> list) throws FileNotFoundException, JRException;
}
