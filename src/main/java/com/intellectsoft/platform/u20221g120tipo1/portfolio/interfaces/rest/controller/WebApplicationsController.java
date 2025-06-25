package com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.controller;


import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.services.WebApplicationCommandService;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.resources.CreateWebApplicationResource;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.resources.WebApplicationResource;
import com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.transform.CreateWebApplicationCommandFromResourceAssembler;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.interfaces.rest.transform.WebApplicationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Web Applications Controller
 *
 * @summary Controller for managing web applications operations
 * @author [Tu Nombre Completo]
 */
@RestController
@RequestMapping(value = "/api/v1/clients/{clientId}/web-applications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Web Applications", description = "Available Web Application Endpoints")
public class WebApplicationsController {

    private final WebApplicationCommandService webApplicationCommandService;

    public WebApplicationsController(WebApplicationCommandService webApplicationCommandService) {
        this.webApplicationCommandService = webApplicationCommandService;
   }

    /**
     * Create a new web application
     *
     * @param clientId The client ID from path parameter
     * @param resource The {@link CreateWebApplicationResource} instance
     * @return The {@link WebApplicationResource} resource for the created web application
     */
    @PostMapping
    @Operation(summary = "Create a new web application", description = "Create a new web application for a specific client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Web application created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    public ResponseEntity<WebApplicationResource> createWebApplication(
            @PathVariable("clientId") Long clientId,
            @RequestBody CreateWebApplicationResource resource) {

        var createWebApplicationCommand = CreateWebApplicationCommandFromResourceAssembler.toCommandFromResource(clientId,resource);
        var webApplication = webApplicationCommandService.handle(createWebApplicationCommand);
        if (webApplication.isEmpty()) return ResponseEntity.badRequest().build();

        var createdWebApplication = webApplication.get();
        var webApplicationResource = WebApplicationResourceFromEntityAssembler.toResourceFromEntity(createdWebApplication);
        return new ResponseEntity<>(webApplicationResource, HttpStatus.CREATED);
    }
}