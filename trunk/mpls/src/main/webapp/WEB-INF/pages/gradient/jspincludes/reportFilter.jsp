<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="col-md-12">
    <label class="form-label">Filter</label>
    <div class="form-group">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="tableColumnsRadio" name="filterReport" class="custom-control-input" value="C" <c:if test="${reconReports.filterReport == 'C'}">checked</c:if> <c:if test="${empty(reconReports.filterReport)}">checked</c:if>>
            <label class="custom-control-label" for="tableColumnsRadio">Report Column</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="columnFilterRadio" name="filterReport" class="custom-control-input" value="F" <c:if test="${reconReports.filterReport == 'F'}">checked</c:if>>
            <label class="custom-control-label" for="columnFilterRadio">Column Filter</label>
        </div>
    </div>
</div>
<div class="col-md-12">
        <div class="form-group">
            <label class="form-label">Table Columns</label>
            <select class="form-control js-example-basic-multiple" name="tableColumns" id="tableColumns"  multiple="multiple">
                <c:forEach items="${reconSourceTemplateList}" var="template" varStatus="index">
                      <option value="${template.fieldName}">${template.description}</option>
                </c:forEach>
            </select>
        </div>
</div>
<c:set var="CValue" value="C"/>
<div class="col-md-12 column columnFilters <c:if test='${empty(reconReports.filterReport) || reconReports.filterReport == CValue}'>DispNone</c:if>" id="columnFilterView">
    <table class="table table-bordered table-hover" id="reportFilterTable">
        <thead>
            <tr >
                <th class="text-center width6">
                    #
                </th>
                <th class="text-center width42">
                    Column Name
                </th>
                <th class="text-center width42">
                    Column Value
                </th>
            </tr>
    </thead>
    <tbody>
        <c:if test="${empty(reconReports.reportFilterList)}">
            <input type="hidden" name="rowNumber" id="rowNumber" value="1">
            <tr id='filter0'>
                <td class="center width6">
                1
                </td>
                <td class="width42">
                    <div class="form-group">
                        <select name="fieldName0" id="fieldName0" class="form-control js-example-basic-single">
                              <option value="">--Select Table Columns--</option>
                        </select>
                    </div>
                </td>
                <td class="width42">
                    <input type="text" name='fieldValue0' id='fieldValue0' placeholder='Field Value' class="form-control"/>
                </tr>
                <tr id='filter1'></tr>
        </c:if>
        <c:if test="${!empty(reconReports.reportFilterList)}">
                <input type="hidden" name="rowNumber" id="rowNumber" value="${fn:length(reconReports.reportFilterList)}">
                <c:forEach items="${reconReports.reportFilterList}" var="reportFilter" varStatus="index">
                    <c:set var="count" value="${index.count - 1}"/>
                    <tr id='filter${count}'>
                        <td class="width6 center">
                           ${count + 1}
                        </td>
                        <td class="width42">
                           <select name="fieldName${count}" id="fieldName${count}" class="form-control js-example-basic-single">
                                <option value="">--Select Table Columns--</option>
                                <c:forEach items="${reconSourceTemplateList}" var="template" varStatus="index">
                                      <option value="${template.fieldName}" <c:if test="${template.fieldName == reportFilter.fieldName}">selected</c:if>>${template.description}</option>
                                </c:forEach>
                           </select>
                        </td>
                        <td class="width42">
                            <input type="text" name='fieldValue${count}' id='fieldValue${count}' placeholder='Field Value' class="form-control" value="${reportFilter.fieldValue}"/>
                        </td>
                    </tr>
                </c:forEach>
                <tr id='filter${fn:length(reconReports.reportFilterList)}'></tr>
         </c:if>
    </tbody>
</table>
    <div class="Bottom100">
            <button type="button" id="add_row" class="btn btn-default pull-left">Add Row</button><button type="button" id='delete_row' class="pull-right btn btn-default">Delete Row</a>
    </div>  
</div>

<div class="col-md-12">
        <div class="form-group">
            <label class="form-label">Report Format</label>
            <select class="form-control" name="reportFormat" id="reportFormat">
                <option value="">--Select Report Format--</option>
                <c:forEach items="${reportFormatList}" var="format" varStatus="index">
                      <option value="${format}" <c:if test="${format == reconReports.reportFormat}">selected</c:if>>${format}</option>
                </c:forEach>
            </select>
        </div>
</div>