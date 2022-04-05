package com.auction.controller.lot;

import com.auction.dto.LotDto;
import com.auction.service.lot.LotServiceProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Lot", tags = "REST APIs related to Lot")
@RestController
@RequestMapping("/api/v1/lots")
@RequiredArgsConstructor
public class LotController {
    private final LotServiceProvider lotService;

    @Operation(summary = "Create lot")
    @ApiResponse(code = 400, message = "Invalid request body")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public LotDto create(@RequestBody LotDto lotDto) {
        return lotService.createOne(lotDto);
    }

    @Operation(summary = "Delete lot")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{lotId}")
    public void delete(@PathVariable final Long lotId) {
        lotService.deleteLot(lotId);
    }

    @Operation(summary = "Get single lot")
    @GetMapping("/{lotId}")
    public LotDto getLot(@PathVariable Long lotId) {
        return lotService.getLot(lotId);
    }

    @Operation(summary = "Get all lots")
    @GetMapping("/allLots")
    public List<LotDto> getAll() {
        return lotService.getAllLots();
    }
}
