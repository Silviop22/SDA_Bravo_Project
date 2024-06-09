package com.bravo.carrental.branch.mapper;
import com.bravo.carrental.branch.model.Branch;
import com.bravo.carrental.branch.model.BranchDto;
import com.bravo.carrental.util.ModelMapper;
import org.hibernate.annotations.Comment;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper implements ModelMapper<BranchDto, Branch> {
    @Override
    public Branch toEntity(BranchDto branchDto) {
        return Branch.builder().id(branchDto.getId())
                .branchName(branchDto.getBranchName())
                .branchCity(branchDto.getBranchCity())
                .listOfEmployees((List) branchDto.getListOfEmployees())
                .listOfCars((List) branchDto.getListOfCars())
                .build();}

    @Override
    public BranchDto toDto(Branch branch) {
        return BranchDto.builder().id(branch.getId())
                .branchName(branch.getBranchName())
                .branchCity(branch.getBranchCity())
                .listOfEmployees((java.util.List) branch.getListOfEmployees())
                .listOfCars((java.util.List) branch.getListOfCars())
                .build();}
}