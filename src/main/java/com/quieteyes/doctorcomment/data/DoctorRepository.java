package com.quieteyes.doctorcomment.data;

import org.springframework.data.repository.CrudRepository;

import com.quieteyes.doctorcomment.model.Doctor;

/** Magic Spring DAO. */
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
