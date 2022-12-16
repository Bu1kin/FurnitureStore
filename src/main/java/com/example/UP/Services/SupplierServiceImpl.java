package com.example.UP.Services;

import com.example.UP.APIRepos.APISupplierRepo;
import com.example.UP.Models.Supplier;
import com.example.UP.Securing.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    APISupplierRepo apiSupplierRepo;

    @Override
    public MessageResponse createSupplier(Supplier supplierRequest){
        Supplier supplier = new Supplier();
        supplier.setNameSupplier(supplierRequest.getNameSupplier());
        supplier.setAddress(supplierRequest.getAddress());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setEmail(supplierRequest.getEmail());

        apiSupplierRepo.save(supplier);
        return new MessageResponse("Поставщик успешно создан.");
    }

    @Override
    public Optional<Supplier> updateSupplier(Long id, Supplier supplierRequestRequest){
        Optional<Supplier> supplier = apiSupplierRepo.findById(id);
        if (supplier.isEmpty()){
            return null;
        } else {
            supplier.get().setNameSupplier(supplierRequestRequest.getNameSupplier());
            supplier.get().setAddress(supplierRequestRequest.getAddress());
            supplier.get().setPhone(supplierRequestRequest.getPhone());
            supplier.get().setEmail(supplierRequestRequest.getEmail());

            apiSupplierRepo.save(supplier.get());
            return supplier;
        }
    }

    @Override
    public void deleteSupplier(Long id){
        apiSupplierRepo.deleteById(id);
    }

    @Override
    public Supplier getASingleSupplier(Long id){
        return apiSupplierRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Supplier> getAllSuppliers(){
        return apiSupplierRepo.findAll();
    }
}
