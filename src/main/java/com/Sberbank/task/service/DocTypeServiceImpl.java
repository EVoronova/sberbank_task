package com.Sberbank.task.service;

import com.Sberbank.task.dao.DocTypeDAOImpl;
import com.Sberbank.task.entities.DocType;

public class DocTypeServiceImpl implements DocTypeService{
    public void createDocType(DocType docType) {
        new DocTypeDAOImpl().createDocType(docType);
    }
}
