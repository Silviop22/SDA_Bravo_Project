package com.bravo.carrental.branch.mapper;
import com.bravo.carrental.branch.model.Branch;
import com.bravo.carrental.branch.model.BranchDto;
import com.bravo.carrental.util.ModelMapper;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper implements ModelMapper<BranchDto, Branch> {
    @Override
    public Branch toEntity(BranchDto branchDto) {
        return Branch.builder().id(branchDto.getId())
                .branchName(branchDto.getBranchName())
                .branchCity(branchDto.getBranchCity())
                .listOfEmployees(branchDto.getListOfEmployees())
                .listOfCars(branchDto.getListOfCars())
                .build();}

    @Override
    public BranchDto toDto(Branch branch) {
        return BranchDto.builder().id(branch.getId())
                .branchName(branch.getBranchName())
                .branchCity(branch.getBranchCity())
                .listOfEmployees(branch.getListOfEmployees())
                .listOfCars(branch.getListOfCars())
                .build();}
}