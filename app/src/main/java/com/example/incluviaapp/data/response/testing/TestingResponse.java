package com.example.incluviaapp.data.response.testing;

import java.util.List;

public record TestingResponse (
    String receiptTime,
    String reportTime,
    String metarType,
    String icaoId,
    String fltCat,
    String visib,
    String rawOb,
    String cover,
    String name,
    float presTend,
    float minT,
    float maxT,
    float temp,
    float dewp,
    float lat,
    float lon,
    double altim,
    double slp,
    int qcField,
    int obsTime,
    int wdir,
    int wspd,
    int elev,
    List<CloudResponseModel> clouds
){}

