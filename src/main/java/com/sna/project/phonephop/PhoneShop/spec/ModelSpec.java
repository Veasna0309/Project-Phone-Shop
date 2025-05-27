package com.sna.project.phonephop.PhoneShop.spec;

import com.sna.project.phonephop.PhoneShop.model.entity.Model;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModelSpec implements Specification<Model> {
    private final ModelFilter filter;
    List<Predicate> predicates=new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {
         if(filter.getName()!=null){
//               Predicate name=model.get("name").in(filter.getName());
//             predicates.add(name);
             predicates.add(cb.equal(model.get("name"), filter.getName()));

         }
        if(filter.getId()!=null){
            Predicate id=model.get("id").in(filter.getId());
            predicates.add(id);
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
