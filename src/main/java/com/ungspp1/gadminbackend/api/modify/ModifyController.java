package com.ungspp1.gadminbackend.api.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ungspp1.gadminbackend.response.BaseBodyResponse;
import com.ungspp1.gadminbackend.response.ResponseHelper;


@RestController
@RequestMapping("/modify")
public class ModifyController 
{

    @Autowired
    private ModifyService modifyService;
    
    @PutMapping("/{id}")
    public ResponseEntity<BaseBodyResponse<?>> updateUser(@PathVariable Long id){//@RequestBody UsuarioDTO usuarioDTO 
        
        try{
            modifyService.updateUser(id);// cambiar despues
            return ResponseHelper.simpleResponse(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
