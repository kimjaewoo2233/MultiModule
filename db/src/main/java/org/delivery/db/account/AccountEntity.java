package org.delivery.db.account;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


@SuperBuilder //부모로부터 상속받은 필드도 Builder에 포함시킨다. 부모클래스도 SuperBuilder가 포함되어야함
@Data
@EqualsAndHashCode(callSuper = true) //부모에 있는 값까지 포함해서 객체를 비교한다. callSuper 옵션을 통해 설정함
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}
