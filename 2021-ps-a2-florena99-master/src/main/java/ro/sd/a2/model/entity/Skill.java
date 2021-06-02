//package ro.sd.a2.model.entity;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Skill {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String skillName;
//
//    @Column
//    private String level;
//
//    @ManyToOne
//    @JoinColumn(name="id_CV")
//    private CV cv;
//}
