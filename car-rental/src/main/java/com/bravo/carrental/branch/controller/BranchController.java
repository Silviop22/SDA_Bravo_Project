package com.bravo.carrental.branch.controller;

import com.bravo.carrental.branch.model.BranchDto;
import com.bravo.carrental.branch.service.BranchService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getById(id));}

    @GetMapping
    public ResponseEntity<List<BranchDto>> getItems(@RequestParam("page") int page,
                                                 @RequestParam("size") int size) {
        Page<BranchDto> result = branchService.getList(page, size);
        return ResponseEntity.ok(result.getContent());}

    @PostMapping
    public ResponseEntity<Void> createBranch(@RequestBody BranchDto branchDto) {
        BranchDto result = branchService.create(branchDto);
        return ResponseEntity.created(URI.create("/branches/" + result.getId())).build();}

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBranch(@RequestBody BranchDto branchDto,
                                          @PathVariable Long id) {
        branchService.update(branchDto, id);
        return ResponseEntity.noContent().build();}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteById(id);
        return ResponseEntity.noContent().build();}
}