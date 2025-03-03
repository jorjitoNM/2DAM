package org.springrest.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springrest.ui.model.TierraDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tierra {
    private String lat;
    private String lng;
    private double metros;
    private List<Cultivo> cultivos;

    public TierraDTO toTierraDTO () {
        return new TierraDTO(this.lat,this.lng,this.metros,this.cultivos.size());
    }
}
