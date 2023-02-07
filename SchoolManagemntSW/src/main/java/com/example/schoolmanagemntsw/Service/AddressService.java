package com.example.schoolmanagemntsw.Service;


import com.example.schoolmanagemntsw.Api.ApiException;
import com.example.schoolmanagemntsw.DTO.AddressDTO;
import com.example.schoolmanagemntsw.Model.Address;
import com.example.schoolmanagemntsw.Model.Teacher;
import com.example.schoolmanagemntsw.Repository.AddressRepository;
import com.example.schoolmanagemntsw.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;
    
     public List<Address> getAddress(){
        return addressRepository.findAll();
    }
    public void addAddress(AddressDTO addressDTO){
        Teacher teacher=teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if(teacher==null){
            throw new ApiException("Required Teacher Not Found");
        }
        Address address=new Address(null,addressDTO.getArea(),addressDTO.getStreet(),addressDTO.getBuildingNumber(),teacher);
        addressRepository.save(address);
    }
    public boolean updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());
        if (address == null) {
            return false;
        }
        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(address);
        return true;
    }
    public boolean deleteAddress(Integer id) {
        Address address = addressRepository.findAddressById(id);
        if (address == null) {
            return false;
        }
        addressRepository.delete(address);
        return true;
    }

}
