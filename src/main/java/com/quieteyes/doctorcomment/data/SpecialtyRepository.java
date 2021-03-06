package com.quieteyes.doctorcomment.data;

import org.springframework.data.repository.CrudRepository;

import com.quieteyes.doctorcomment.model.Specialty;

/** Magic Spring DAO. */
interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
