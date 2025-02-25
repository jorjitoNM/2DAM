package org.examen.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactionsDocument {
    private ObjectId _id;
    private List<FactionMongo> factions;
}
