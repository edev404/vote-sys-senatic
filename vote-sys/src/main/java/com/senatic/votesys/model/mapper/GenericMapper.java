package com.senatic.votesys.model.mapper;

public interface GenericMapper <T, Z>{
    public Z map(T dto);
}
