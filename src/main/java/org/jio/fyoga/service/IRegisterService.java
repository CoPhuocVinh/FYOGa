package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 11:37 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Register;

public interface IRegisterService {
    <S extends Register> S save(S entity);
}
