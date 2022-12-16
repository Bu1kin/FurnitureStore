package com.example.UP.Services;

import com.example.UP.Models.Supplier;
import com.example.UP.Securing.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SupplierService {
    MessageResponse createSupplier(Supplier supplierRequest);
    Optional<Supplier> updateSupplier(Long id, Supplier supplierRequest);
    void deleteSupplier(Long supplierRequestId);
    Supplier getASingleSupplier(Long supplierRequestId);
    List<Supplier> getAllSuppliers();
}
