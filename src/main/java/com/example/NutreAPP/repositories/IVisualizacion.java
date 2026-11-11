package com.example.NutreAPP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IVisualizacion {

    @Repository 
    public interface InnerIVisualizacion extends JpaRepository{
    
        
    }

}
