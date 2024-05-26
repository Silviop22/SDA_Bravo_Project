package com.bravo.carrental.branch.service;

import com.bravo.carrental.branch.mapper.BranchMapper;
import com.bravo.carrental.branch.model.Branch;
import com.bravo.carrental.branch.model.BranchDto;
import com.bravo.carrental.branch.repository.BranchRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;}

    public BranchRepository getBranchRepository() {return branchRepository;}

    public BranchMapper getBranchMapper() {return branchMapper;}


    @Transactional
    public BranchDto getById(Long id) {
        return branchMapper.toDto(branchRepository.findById((id)).orElseThrow());}

    @Transactional
    public Page<BranchDto> getList(int page, int size) {
        Pageable pageble = PageRequest.of(page, size);
        return branchRepository.findAll(pageble)
                .map(branchMapper::toDto);}

    @Transactional
    public BranchDto create(BranchDto branchDto) {
        String branchName = branchDto.getBranchName();
        String branchCity = branchDto.getBranchCity();
        if (branchRepository.findByBranchName(branchName).isPresent()
                && branchRepository.findByCity(branchCity).isPresent()) {
            throw new IllegalArgumentException();}
        Branch branch = branchMapper.toEntity(branchDto);
        branch = branchRepository.save(branch);
        branchDto.setId(branch.getId());
        return branchDto;}

    @Transactional
    public void update(BranchDto branchDto, Long id) {
        Branch existing = getExistingEntity((id));
        Branch updateCandidate = branchMapper.toEntity(branchDto);
        ObjectPatcher.patchObject(updateCandidate, existing);
        branchRepository.save(existing);}

    @Transactional
    public void deleteById(Long id) {
        getExistingEntity((id));
        branchRepository.deleteById((id));}

    private Branch getExistingEntity(Long id) {
        return branchRepository.findById((id))
                .orElseThrow();}
}